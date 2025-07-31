package example.kafkauser.service.Implements;

import example.kafkauser.model.CustomUserDetail;
import example.kafkauser.model.User;
import example.kafkauser.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplCustomeUserDetail implements UserDetailsService {
    @Autowired
    private IRepositoryUser repoUser;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repoUser.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tim thay User name"));
        return new CustomUserDetail(user);
    }
}
