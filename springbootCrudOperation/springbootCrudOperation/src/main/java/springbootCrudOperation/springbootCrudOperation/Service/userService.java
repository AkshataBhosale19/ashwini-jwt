package springbootCrudOperation.springbootCrudOperation.Service;

import org.springframework.http.ResponseEntity;
import springbootCrudOperation.springbootCrudOperation.Model.GeneratedToken;
import springbootCrudOperation.springbootCrudOperation.Model.User;

public interface userService {

    Object postUser(User user);

    Object getUSer();

    ResponseEntity<Object> getthedetailsofUser(String username);

    ResponseEntity<Object> updateUser(Long id,User user);

    ResponseEntity<Object> deleteUser(Long id);

    GeneratedToken verify(User user);
}
