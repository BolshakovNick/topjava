package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealStorageMap implements MealStorage {
    private static final List<Meal> testData = Arrays.asList(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));

    private final AtomicInteger currentId = new AtomicInteger(0);
    private final Map<Integer, Meal> mealStorage;

    public MealStorageMap() {
        mealStorage = new ConcurrentHashMap<>();
        for (Meal meal : testData) {
            int id = currentId.getAndIncrement();
            meal.setId(id);
            mealStorage.put(id, meal);
        }
    }

    @Override
    public Meal create(Meal meal) {
        int id = currentId.getAndIncrement();
        meal.setId(id);
        mealStorage.put(id, meal);
        return meal;
    }

    @Override
    public Meal delete(int id) {
        return mealStorage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealStorage.values());
    }

    @Override
    public Meal update(Meal meal) {
        int id = meal.getId();
        mealStorage.remove(id);
        return mealStorage.put(id, meal);
    }

    @Override
    public Meal get(int id) {
        return mealStorage.get(id);
    }
}