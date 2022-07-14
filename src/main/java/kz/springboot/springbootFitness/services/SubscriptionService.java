package kz.springboot.springbootFitness.services;

import kz.springboot.springbootFitness.entities.Prices;
import kz.springboot.springbootFitness.entities.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription saveSubscription(Subscription subscription);

    Subscription addSubscription(Subscription subscription);

    Subscription getSubscription(Long id);

    List<Subscription> getAllSubscriptions();

    List<Prices> getAllPrices();

    void deleteSubscription(Subscription subscription);

}
