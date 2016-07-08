package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * Created by roman on 08.07.2016.
 */
public interface UserMealRepository {

public UserMeal save(UserMeal userMeal);
public void delete(int id);
public     UserMeal get(int id);
public Collection<UserMeal> getAll();

}
