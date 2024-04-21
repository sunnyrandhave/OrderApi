package com.OrderApi.orderAPI.Entities;

import com.OrderApi.orderAPI.Utilities.OfferStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "offer_tb")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;
    private String offerCode;
    private BigDecimal discount;
    private LocalDate expiryDate;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    public Offer() {
        offerStatus = OfferStatus.ACTIVE;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerCode='" + offerCode + '\'' +
                '}';
    }
}
