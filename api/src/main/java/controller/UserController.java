package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import dao.UserService;
import dao.RequestService;
import dao.PaymentTypeService;
import domain.User;
import domain.Request;
import domain.PaymentType;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService = new UserService();
    RequestService requestService = new RequestService();
    PaymentTypeService paymentTypeService = new PaymentTypeService();

    @RequestMapping("/all")
    public List<User> getAllUsers() {
      List<User> users=userService.getAllUsers();
      return users;
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
        return paymentType;
    }

    @RequestMapping("/{id}/trusted_friends")
    public List<User> getFriends(@PathVariable int id){
        List<User> users=userService.getTrustedFriends(id);
        return paymentType;
    }

    @RequestMapping("/api/user/{id}/friend/{friend_email}")
    public bool addFriend(@Pathbariable int id, @Pathvariable String friend_email){
        bool completed = userService.addFriend(id, friend_email);
        return completed;
    }

    @RequestMapping("/api/user/{id}/friend/{friend_email}")
    public bool trustFriend(@Pathbariable int id, @Pathvariable String friend_email){
        bool completed = userService.trustFriend(id, friend_email);
        return completed;
    }

    //Not sure how I'm getting stuff from the view??
    // 0 - Email already registered
    // 1 - Completed
    // 2 - other error??
    @RequestMapping("/api/user/register")
    public int register(){
        int completed = userService.register();
        return completed;
    }

}