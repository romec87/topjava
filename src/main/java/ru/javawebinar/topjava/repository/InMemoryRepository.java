package ru.javawebinar.topjava.repository;

import com.sun.javafx.collections.MappingChange;
import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by roman on 08.07.2016.
 */
public class InMemoryRepository implements UserMealRepository {
    private static final Logger LOG = getLogger(InMemoryRepository.class);



    private Map<Integer, UserMeal> storage = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew())
            userMeal.setId(counter.incrementAndGet());
        LOG.info("User mewl id {}",userMeal.getId());
        return storage.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return storage.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return storage.values();
    }
}
