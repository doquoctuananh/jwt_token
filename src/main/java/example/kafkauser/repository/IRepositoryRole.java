package example.kafkauser.repository;

import example.kafkauser.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryRole extends JpaRepository<Role,Integer> {

    Role findByRoleName(String name);
    Role save(Role role);
}
