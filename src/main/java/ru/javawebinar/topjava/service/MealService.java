package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
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
        checkException(!repository.delete(id, authUserId), authUserId, id);
    }

    public Meal get(int id, int authUserId) {
        Meal meal = repository.get(id, authUserId);
        checkException(meal == null, authUserId, id);
        return meal;
    }

    public Collection<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId);
    }

    public void update(Meal meal, int authUserId) {
        checkException(repository.save(meal, authUserId) == null, authUserId, meal.getId());
    }

    private void checkException(boolean expression, int authUserId, int mealId) {
        if (expression) {
            throw new NotFoundException("User " + authUserId + " doesn't have meal with id = " + mealId);
        }
    }

    public Collection<Meal> getAllFiltered(int authUserId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return repository.getAllFiltered(authUserId, startDate, startTime, endDate, endTime);
    }
}