package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int authUserId) {
        return repository.save(meal, authUserId);
    }

    public void delete(int id, int authUserId) {
        checkMealDoesNotBelongAuthUser(!repository.delete(id, authUserId), authUserId, id);
    }

    public Meal get(int id, int authUserId) {
        Meal meal = repository.get(id, authUserId);
        checkMealDoesNotBelongAuthUser(meal, authUserId, id);
        return meal;
    }

    public Collection<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId);
    }

    public void update(Meal meal, int authUserId) {
        checkMealDoesNotBelongAuthUser(repository.save(meal, authUserId), authUserId, meal.getId());
    }

    private void checkMealDoesNotBelongAuthUser(Meal meal, int authUserId, int mealId) {
        checkMealDoesNotBelongAuthUser(meal == null, authUserId, mealId);
    }

    private void checkMealDoesNotBelongAuthUser(boolean expression, int authUserId, int mealId) {
        if (expression) {
            throw new NotFoundException("User " + authUserId + " doesn't have meal with id = " + mealId);
        }
    }
}