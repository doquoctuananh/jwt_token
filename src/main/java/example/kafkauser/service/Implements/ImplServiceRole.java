package example.kafkauser.service.Implements;

import example.kafkauser.model.Role;
import example.kafkauser.repository.IRepositoryRole;
import example.kafkauser.service.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplServiceRole implements IServiceRole {
    @Autowired
    private IRepositoryRole repositoryRole;
    @Override
    public Role findByRoleName(String name) {
        return repositoryRole.findByRoleName(name);
    }

    @Override
    public Role save(Role role) {
        return repositoryRole.save(role);
    }
}
