package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.List;
import dao.UserService;
import dao.RequestService;
import dao.PaymentTypeService;
import domain.User;
import domain.Request;
import domain.PaymentType;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService = new UserService();
    RequestService requestService = new RequestService();
    PaymentTypeService paymentTypeService = new PaymentTypeService();

    @RequestMapping("/all")
    public List<User> getAllUsers() {
      List<User> users=userService.getAllUsers();
      return users;
    }

    @RequestMapping("/{id}/getuser")
    public User getUser(@PathVariable int id){
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/{id}/requests")
    public List<Request> getRequestsById(@PathVariable int id){
      List<Request> requests = requestService.getRequestsById(id);
      return requests;
    }

    @RequestMapping("/{id}/payments")
    public List<Request> getPaymentsById(@PathVariable int id){
      List<Request> requests = requestService.getPaymentsById(id);
      return requests;
    }

    @RequestMapping("/{id}/pay_types")
    public PaymentType getPaymentTypeById(@PathVariable int id){
      PaymentType paymentType = paymentTypeService.getPaymentTypeById(id);
      return paymentType;
    }

    @RequestMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable int id){
        List<User> users=userService.getFriends(id);
        return users;
    }

    @RequestMapping("/{id}/trusted_friends")
    public List<User> getTrustedFriends(@PathVariable int id){
        List<User> users=userService.getTrustedFriends(id);
        return users;
    }

    @RequestMapping("/{id}/addfriend/{friend_id}")
    public ResponseEntity<String> addFriend(@PathVariable int id, @PathVariable int friend_id) throws SQLException{
        ResponseEntity<String> completed = userService.addFriend(id, friend_id);
        return completed;
    }

    @RequestMapping("/{id}/trustfriend/{friend_id}")
    public ResponseEntity<String> trustFriend(@PathVariable int id, @PathVariable int friend_id) throws SQLException{
        ResponseEntity<String> completed = userService.trustFriend(id, friend_id);
        return completed;
    }

    @RequestMapping(value = "/register", method =  RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody User user) throws SQLException{
        ResponseEntity<String> completed = userService.register(user);
        return completed;
    }

}