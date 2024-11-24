package springbootCrudOperation.springbootCrudOperation.Contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootCrudOperation.springbootCrudOperation.Model.GeneratedToken;
import springbootCrudOperation.springbootCrudOperation.Model.User;
import springbootCrudOperation.springbootCrudOperation.Service.userService;

@RestController
public class UserController {

    @Autowired
    private userService userservice;

    @PostMapping("/createuser")
    public ResponseEntity<Object> createUser(@RequestBody  User user){
       return (ResponseEntity<Object>) userservice.postUser(user);
    }
    @PostMapping("/login")
    public GeneratedToken login_user(@RequestBody User user){
        return userservice.verify(user);

    }


    @GetMapping("/findallusers")
    public Object findUsers()
    {
        return userservice.getUSer();
    }

    @GetMapping("/finddetailssofUser")
    public ResponseEntity<Object> getDetails(@RequestParam String username){
        return userservice.getthedetailsofUser(username);

    }
    @PatchMapping("/users/{id}")
    public  ResponseEntity<Object> updateUser(@PathVariable Long id,@RequestBody User user){
//        user.setId(id);
        return userservice.updateUser(id,user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id){

        return userservice.deleteUser(id);
    }





}
