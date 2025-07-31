package example.kafkauser.service;

import example.kafkauser.model.User;

public interface IServiceUser {
    User findByUsername(String username);
    User save(User user);
}
