package example.kafkauser.service.Implements;

import example.kafkauser.model.User;
import example.kafkauser.repository.IRepositoryUser;
import example.kafkauser.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ImplServiceUser implements IServiceUser {
    @Autowired
    private IRepositoryUser repoUser;

    @Override
//    @KafkaListener(topics = "user",groupId = "user")
    public User findByUsername(String username) {
        return repoUser.findByUsername(username).orElse(null);
    }
}
