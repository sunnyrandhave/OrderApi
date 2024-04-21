package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    Optional<Offer> findByofferCode(String offerCode);
}
