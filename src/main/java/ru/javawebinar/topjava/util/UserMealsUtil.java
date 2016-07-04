package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();

        Map<LocalDate,Integer>meals =  mealsInDays(mealList);
        for (UserMeal userMeal : mealList)
        {

            if(TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime))
            {
                Integer calories = meals.get(userMeal.getDateTime().toLocalDate());
                boolean isExced = calories > caloriesPerDay ? true : false;

                if(isExced)
                    mealWithExceeds.add(new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),isExced));
            }
        }

        return mealWithExceeds;
    }

    public static Map<LocalDate,Integer> mealsInDays(List<UserMeal> mealList)
    {
        Map<LocalDate,Integer> meals = new HashMap<>();
        for (UserMeal userMeal:mealList)
        {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            if(meals.containsKey(localDate))
            {
                meals.put(localDate,meals.get(localDate)+userMeal.getCalories());
            }
            else
            {
                meals.put(localDate,userMeal.getCalories());
            }
        }
        return meals;
    }


}
