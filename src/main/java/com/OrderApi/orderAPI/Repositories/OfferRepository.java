package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.Offer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {


//    Optional<Offer> findby(String offerCode);

    Optional<Offer> findBypromoCode(String promoCode);
}
