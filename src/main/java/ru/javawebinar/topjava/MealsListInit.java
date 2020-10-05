package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.storage.StorageList;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class MealsListInit {
    private static final int CALORIES_LIMIT = 2000;
    private static final Storage STORAGE = new StorageList();
    
    static {
        STORAGE.getAll().addAll(Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)));
    }
    
    public static Storage getStorage() {
        return STORAGE;
    }

    public static int getCaloriesLimit() {
        return CALORIES_LIMIT;
    }
}