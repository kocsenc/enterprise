package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import utility.DBUtility;
import domain.User;

public class UserService {
 
 private Connection connection;

 public UserService() {
  connection = DBUtility.getConnection();
 }
  

 public List<User> getAllUsers() {
  List<User> users = new ArrayList<User>();
  try {
   Statement statement = connection.createStatement();
   ResultSet rs = statement.executeQuery("select * from User limit 15");
   while (rs.next()) {
    int uid = rs.getInt("uid");
    String name = rs.getString("uname");
    Double wallet = rs.getDouble("wallet");
    String email = rs.getString("email");    
    User user = new User(uid,name,wallet,email);
    users.add(user);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return users;
 }

}