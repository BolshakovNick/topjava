package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    int getCurrentID();

    void clear();

    /**
     * @return ID of saved meal
     */
    int save(Meal meal);

    /**
     * @param id - index of this meal in some storage.
     * @return true if object with this id existed in storage and removal was successful.
     */
    boolean delete(int id);

    Meal get(int id);

    List<Meal> getAll();

    int size();

    /**
     * @return true if object with this id existed in storage and updating was successful.
     */
    boolean update(Meal meal);
}
