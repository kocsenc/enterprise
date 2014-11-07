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


public List<user> getFriends() {
    List<User> users = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Friends where friend1 = " + id + "or friend2 = " + id);
        while (rs.next()) {
            int uid1 = rs.getInt("friend1");
            int uid2 = rs.getInt("friend2");
            int friendID = null;

            //check which user is not you
            if (uid1 = id) {
                friendID = uid2;
            } else {
                friendID = uid2;
            }

            //Get user object from database, put it in list
            Statement userStatement = connection.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from User where uid = " + friendID);
            String name = rs.getString("uname");
            Double wallet = rs.getDouble("wallet");
            String email = rs.getString("email");
            User user = new User(uid, name, wallet, email);
            if (users.contains(user) == false) {
                users.add(user);
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }

    return users;
}


public List<user> getTrustedFriends() {
    List<User> users = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Friends where friend1 = " + id + "or friend2 = " + id + "and trust = 1");
        while (rs.next()) {
            int uid1 = rs.getInt("friend1");
            int uid2 = rs.getInt("friend2");
            int friendID = null;

            //check which user is not you
            if (uid1 = id) {
                friendID = uid2;
            } else {
                friendID = uid2;
            }

            //Get user object from database, put it in list
            Statement userStatement = connection.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from User where uid = " + friendID);
            String name = rs.getString("uname");
            Double wallet = rs.getDouble("wallet");
            String email = rs.getString("email");
            User user = new User(uid, name, wallet, email);
            if (users.contains(user) == false) {
                users.add(user);
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }

    return users;
}

}