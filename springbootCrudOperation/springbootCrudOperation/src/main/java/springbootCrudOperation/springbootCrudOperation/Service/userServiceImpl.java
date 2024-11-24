package springbootCrudOperation.springbootCrudOperation.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springbootCrudOperation.springbootCrudOperation.Model.GeneratedToken;
import springbootCrudOperation.springbootCrudOperation.Model.User;
import springbootCrudOperation.springbootCrudOperation.Repository.JPARepositery;

import java.util.List;
import java.util.Optional;


@Service
public class userServiceImpl implements userService {
    public userServiceImpl() {
    }

    @Autowired
    JPARepositery jpaRepositery;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encode=new BCryptPasswordEncoder(12);

    public Object postUser(User user) {
        if (jpaRepositery.existsByEmailid(user.getEmailid())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER ALREADY EXISTS");
        }

      //  User user1 = new User(user.getEmailid(), user.getUsername(), user.getLastname(), user.getId());
      // user.setLastname(encode.encode(user.getLastname()));
        User users= jpaRepositery.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @Override
    public Object getUSer() {
        List<User> users=jpaRepositery.findAll();
      if(users.isEmpty()){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO user data is available");
      }else
          return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Override
    public ResponseEntity<Object> getthedetailsofUser(String username) {
        System.out.print(username);

        User userdetails=jpaRepositery.findByUsername(username);
        if(userdetails==null)
        {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user data is available with this usernamee");
        }else
            return ResponseEntity.status(HttpStatus.OK).body(userdetails);
    }


    @Override
    public ResponseEntity<Object> updateUser(Long id, User user) {
       Optional<User> existing_user=jpaRepositery.findById(id);
       if(existing_user.isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       else {
           User updateUser = existing_user.get();
           System.out.println(updateUser);
           System.out.println(user);
           updateUser.setEmailid(user.getEmailid());
           updateUser.setLastname((user.getLastname()));
           updateUser.setUsername(user.getUsername());
           User updateduser = jpaRepositery.save(updateUser);
           System.out.println(updateUser);
           return ResponseEntity.status(HttpStatus.OK).body(updateduser);
       }

    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        if(jpaRepositery.existsById(id))
        {
            jpaRepositery.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public GeneratedToken verify(User user) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getLastname()));
       if(authentication.isAuthenticated())
       {
           String Token= jwtService.generateToke(user.getUsername());
           return new GeneratedToken(Token);
       }else
        return new GeneratedToken("fail");
    }
    }

