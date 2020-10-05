package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class StorageList implements Storage{
    private static final AtomicInteger currentID = new AtomicInteger(0);
    private final List<Meal> meals;

    public StorageList() {
        meals = new CopyOnWriteArrayList<>();
    }

    public StorageList(List<Meal> storage) {
        this.meals = storage;
    }

    @Override
    public int getCurrentID() {
        return currentID.get();
    }

    @Override
    public void clear() {
        meals.clear();
    }

    @Override
    public int save(Meal meal) {
        meal.setId(currentID.get());
        meals.add(meal);
        return currentID.getAndIncrement();
    }

    @Override
    public boolean delete(int id) {
        for (Meal meal : meals) {
            if (id == meal.getId()) {
                meals.remove(meal);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public int size() {
        return meals.size();
    }

    @Override
    public boolean update(Meal meal) {
        int index = 0;
        for (Meal m : meals) {
            if (meal.getId() == m.getId()) {
                meals.set(index, meal);
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        for (Meal meal : meals) {
            if (id == meal.getId()) {
                return meal;
            }
        }
        return null;
    }
}
