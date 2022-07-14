package kz.springboot.springbootFitness.services.impl;

import kz.springboot.springbootFitness.entities.Prices;
import kz.springboot.springbootFitness.entities.Subscription;
import kz.springboot.springbootFitness.repositories.SubscriptionRepository;
import kz.springboot.springbootFitness.services.SubscriptionService;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.getById(id);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void deleteSubscription(Subscription subscription) {
        subscriptionRepository.delete(subscription);
    }

    @Override
    public List<Prices> getAllPrices() {
        return null;
    }
}
