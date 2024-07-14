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
import java.time.LocalDate;
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
        Optional<User> userOptional = userRepository.findById(order.getUserId());
        Optional<Product> productOptional = productRepository.findById(order.getProductId());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User does not Exists");
        }
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product does not Exists");
        }
        if (order.getDeliveryAddress() == null) {
            order.setDeliveryAddress(userOptional.get().getUserAddress());
        }
        if (productOptional.get().getStockAvailable() < order.getProductQuantity()) {
            throw new ProductNotAvailableException(productOptional.get().getProductName() + " Is Currently Out Of Stock");
        }
//        if (Objects.equals(order.getCreatedTime().getDayOfWeek().toString(), "SUNDAY")) {
//            throw new HolidayException("Sorry! Order's Cannot Be Placed On Sunday");
//        }
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCustomerName(userOptional.get().getUserName());
        order.setOrderValue(productOptional.get().getProductValue().multiply(BigDecimal.valueOf(order.getProductQuantity())));
        if (order.getOffer() != null && order.getOffer().getPromoCode() != null) {
            String promoCode = order.getOffer().getPromoCode();
            Optional<Offer> offerOptional = offerRepository.findByPromoCode(promoCode);
            if (offerOptional.isEmpty()) {
                throw new InvalidPromoCodeException("Provided promo code is invalid");
            }

            Offer offer = offerOptional.get();
            if (offer.getExpiryDate().isBefore(LocalDate.now())) {
                offer.setOfferStatus(OfferStatus.EXPIRED);
                offerRepository.saveAndFlush(offer);
                throw new PromoCodeExpiredException("Provided promo code is expired");
            }

            BigDecimal discount = offer.getDiscount();
            BigDecimal discountedPrice = discount.divide(BigDecimal.valueOf(100)).multiply(order.getOrderValue());
            BigDecimal finalValue = order.getOrderValue().subtract(discountedPrice);
            order.setOrderValue(finalValue);
            order.setOffer(offer);
        }else{
            order.setOffer(null);
        }

        if (order.getOrderValue().intValue() < 99) {
            throw new MinimumOrderValueException("Minimum order value Should More Than Rs 99");
        }
        if (order.getOrderValue().intValue() >= 5000) {
            throw new MaximumOrderValueException("Maximum order value Should be Less Than Rs 5000");
        }

        productOptional.get().setStockAvailable(productOptional.get().getStockAvailable() - order.getProductQuantity());
        productRepository.save(productOptional.get());

        orderRepository.save(order);
        return order.toString();
    }
    public String getOrderById ( int orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get().toString();
        } else {
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
