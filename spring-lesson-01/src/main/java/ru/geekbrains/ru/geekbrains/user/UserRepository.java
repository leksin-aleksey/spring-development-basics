package ru.geekbrains.ru.geekbrains.user;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {
    private Map<Long, User> userRepository = new ConcurrentHashMap<>();

    private AtomicLong idCounter = new AtomicLong(0);

    public List<User> findAll(){
        return new LinkedList<>(userRepository.values());
    }

    public User findById(long id){
        return userRepository.get(id);
    }

    public void add(String username){
        long id = idCounter.getAndIncrement();
        userRepository.put(id, new User(id, username));
    }

    public User update(User user, String username){
        User newUser = userRepository.get(user.getId());
        if (newUser != null){
            newUser.setUsername(username);
        }
        return newUser;
    }

    public void delete(User user){
        userRepository.remove(user.getId());
    }
}
