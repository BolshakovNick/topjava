package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

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
        ValidationUtil.checkNotFound(repository.delete(id, authUserId), "User " + authUserId + " doesn't have meal with id = " + authUserId);
    }

    public Meal get(int id, int authUserId) {
        Meal meal = repository.get(id, authUserId);
        ValidationUtil.checkNotFound(meal != null, "User " + authUserId + " doesn't have meal with id = " + authUserId);
        return meal;
    }
    public List<Meal> getAll(int authUserId) {
        return repository.getAll(authUserId);
    }

    public List<Meal> getAllFiltered(int authUserId, LocalDate startDate, LocalDate endDate) {
        return repository.getAllFiltered(authUserId, startDate, endDate);
    }

    public void update(Meal meal, int authUserId) {
        ValidationUtil.checkNotFound(repository.save(meal, authUserId) != null, "User " + authUserId + " doesn't have meal with id = " + authUserId);
    }
}