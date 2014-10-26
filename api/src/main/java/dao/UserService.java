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
    User user = new User();
    user.setId(rs.getInt("uid"));
    user.setName(rs.getString("uname"));
    user.setEmail(rs.getString("email"));
    user.setWallet(rs.getDouble("wallet"));
    users.add(user);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return users;
 }

}