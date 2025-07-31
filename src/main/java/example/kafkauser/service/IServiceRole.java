package example.kafkauser.service;

import example.kafkauser.model.Role;

public interface IServiceRole {
    Role findByRoleName(String name);
    Role save(Role role);
}
