package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {

    Meal create(Meal meal);

    Meal delete(int id);

    Meal get(int id);

    List<Meal> getAll();

    Meal update(Meal meal);
}
