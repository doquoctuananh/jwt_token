package example.kafkauser.configuration.security;


import example.kafkauser.model.Role;
import example.kafkauser.model.User;
import example.kafkauser.service.Implements.ImplServiceRole;
import example.kafkauser.service.Implements.ImplServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ImplServiceUser serviceUser;
    @Autowired
    private ImplServiceRole serviceRole;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(serviceRole.findByRoleName("ADMIN") == null) {
            Role role = new Role();
            role.setRoleName("ADMIN");
            serviceRole.save(role);
        }
        if(serviceRole.findByRoleName("USER") == null) {
            Role role = new Role();
            role.setRoleName("USER");
            serviceRole.save(role);
        }
        if(serviceUser.findByUsername("adminadmin") == null) {
            User user = new User();
            user.setUsername("adminadmin");
            user.setPassword(bCryptPasswordEncoder.encode("123456789"));
            user.setEmail("admin@gmail.com");
            user.setGender(true);
            user.setAddress("Nghe an");
            user.setPhone("0968555555");
            Role role = serviceRole.findByRoleName("ADMIN");
            user.setRole(role);
            serviceUser.save(user);
        }
        if(serviceUser.findByUsername("username123") == null) {
            User user = new User();
            user.setUsername("username123");
            user.setPassword(bCryptPasswordEncoder.encode("987654321"));
            user.setEmail("user@gmail.com");
            user.setGender(true);
            user.setAddress("Ha noi");
            user.setPhone("0968777777");
            Role role = serviceRole.findByRoleName("USER");
            user.setRole(role);
            serviceUser.save(user);
        }
    }
}