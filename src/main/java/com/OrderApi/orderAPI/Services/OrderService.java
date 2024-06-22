package com.OrderApi.orderAPI.Services;

import com.OrderApi.orderAPI.Entities.Offer;
import com.OrderApi.orderAPI.Entities.Order;
import com.OrderApi.orderAPI.Entities.Product;
import com.OrderApi.orderAPI.Exceptions.*;
import com.OrderApi.orderAPI.Repositories.OfferRepository;
import com.OrderApi.orderAPI.Utilities.OfferStatus;
import com.OrderApi.orderAPI.Utilities.OrderStatus;
import com.OrderApi.orderAPI.Entities.User;
import com.OrderApi.orderAPI.Repositories.OrderRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OfferRepository offerRepository;


    public String createOrder(Order order) throws Exception {
        Optional<User>  userOptional = userRepository.findById(order.getUserId());
        Optional<Product> productOptional = productRepository.findById(order.getProductId());
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User does not Exists");
        }
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product does not Exists");
        }
        if(order.getDeliveryAddress()==null){
            order.setDeliveryAddress(userOptional.get().getUserAddress());
        }if(productOptional.get().getStockAvailable()<order.getProductQuantity()){
            throw new ProductNotAvailableException(productOptional.get().getProductName()+" Is Currently Out Of Stock");
        }
        if(Objects.equals(order.getCreatedTime().getDayOfWeek().toString(),"SUNDAY")){
                throw new HolidayException("Sorry! Order's Cannot Be Placed On Sunday");
        }
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomerName(userOptional.get().getUserName());
        order.setOrderValue(productOptional.get().getProductValue().multiply(BigDecimal.valueOf(order.getProductQuantity())));
        if(!(order.getPromoCode()==null)) {
            Optional<Offer> offer = offerRepository.findByPromoCode(order.getPromoCode().toString());
            Offer temp = offer.get();
            if (offer.isPresent()) {
                Offer offer1 = offer.get();
                if (offer1.getExpiryDate().isBefore(ChronoLocalDate.from(order.getCreatedTime()))) {
                    offer1.setOfferStatus(OfferStatus.EXPIRED);
                    offerRepository.save(offer1);
                    throw new PromoCodeExpiredException("Provided Promo Code is Expired");
                }
                BigDecimal discount = offer1.getDiscount();
                BigDecimal discountedPrice = order.getOrderValue().multiply(discount);
                BigDecimal finalOrderValue = order.getOrderValue().subtract(discountedPrice.divide(BigDecimal.valueOf(100)));
                order.setOrderValue(finalOrderValue);
                order.setPromoCode(offer1);
                order.setPromoCode(offer.get());
            } else {
                throw new InvalidPromoCodeException("Invalid Promo Code");
            }
            order.setPromoCode(temp);
            if(!offer.isPresent()){
                throw new InvalidPromoCodeException("Provided Promo Code is Invalid");
            }else{
                if(offer.get().getExpiryDate().isBefore(ChronoLocalDate.from(order.getCreatedTime()))){
                    offer.get().setOfferStatus(OfferStatus.EXPIRED);
                    throw new PromoCodeExpiredException("Provided Promo Code is Expired");
                }else{
                    BigDecimal discount = offer.get().getDiscount();
                    BigDecimal discountedPrice = order.getOrderValue().multiply(discount);
                    BigDecimal finalOrderValue = order.getOrderValue().subtract(discountedPrice.divide(BigDecimal.valueOf(100)));
                    order.setOrderValue(finalOrderValue);

                }
            }
        }else{
            order.setPromoCode(null);
        }
        if(order.getOrderValue().intValue()<99){
            throw new MinimumOrderValueException("Minimum order value Should More Than Rs 99");
        }
        if(order.getOrderValue().intValue()>=5000){
            throw new MaximumOrderValueException("Maximum order value Should be Less Than Rs 5000");
        }
        productOptional.get().setStockAvailable(productOptional.get().getStockAvailable()-order.getProductQuantity());
        productRepository.save(productOptional.get());
        orderRepository.save(order);
        return order.toString();
    }

    public String getOrderById(int orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()){
            return order.get().toString();
        }else{
            throw new OrderNotFoundException("Order Id doesn't Exists");
        }
    }

    public String updateOrderStatus(int orderID, String orderStatus) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderID);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            switch (orderStatus.toUpperCase()){
                case "ACCEPTED":
                    order.setOrderStatus(OrderStatus.ACCEPTED);
                    break;
                case "CANCELLED":
                    order.setOrderStatus(OrderStatus.CANCELLED);
                    break;
                case "DELIVERED":
                    order.setOrderStatus(OrderStatus.DELIVERED);
                    break;
                case "PENDING":
                    order.setOrderStatus(OrderStatus.PENDING);
                    break;
                default:
                    throw new InvalidOrderStatusException("Status Provided Not Valid");
            }
                return orderRepository.save(order).toString();

        }else{
            throw new OrderNotFoundException("Order not Found");
        }
    }
    public List<String> getAllOrders() throws Exception{
        List<Order> optionalOrders= orderRepository.findAll();
        List<String> allOrders = new ArrayList<>();
        for(Order order : optionalOrders){
                allOrders.add(order.toString());
        }
        if(allOrders.isEmpty()){
            throw new OrderNotFoundException("No Orders Placed");
        }
        return allOrders;
    }

}
