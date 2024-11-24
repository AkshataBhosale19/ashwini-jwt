package springbootCrudOperation.springbootCrudOperation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootCrudOperation.springbootCrudOperation.Model.User;

import java.util.List;

@Repository
public interface JPARepositery extends JpaRepository<User,Long> {


    boolean existsByEmailid(String emailid);

    //List<User> findByUsername(String username);

   User findByUsername(String username);

    List<User> findByEmailid(String emailid);
}
