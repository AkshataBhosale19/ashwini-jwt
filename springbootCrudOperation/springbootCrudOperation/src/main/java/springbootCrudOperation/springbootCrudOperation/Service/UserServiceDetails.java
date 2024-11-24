package springbootCrudOperation.springbootCrudOperation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springbootCrudOperation.springbootCrudOperation.Model.User;
import springbootCrudOperation.springbootCrudOperation.Model.UserPrincipal;
import springbootCrudOperation.springbootCrudOperation.Repository.JPARepositery;

@Service
public class UserServiceDetails implements UserDetailsService {
    @Autowired
    private JPARepositery jpaRepositery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User name= jpaRepositery.findByUsername(username);
        if(name==null){
            System.out.print("User not exist");
            throw new  UsernameNotFoundException("User not exist");
        }
        return new UserPrincipal(name) {
        };
    }
}
