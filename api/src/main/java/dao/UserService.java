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
                friendID = uid1;
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
                friendID = uid1;
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

public bool addFriend(){
    bool completed = false;
    try{
        Statement IDstatement = connection.createStatement();
        ResultSet rs = IDstatement.executeQuery("select * from User where email = " + friend_email);
        int fid = null;
        while (rs.next()) {
            fid = rs.getInt("uid");
        }

        if(fid = null){
            return completed;
        }
        else{
            PreparedStatement addFriendStatement = null;
            connection.setAutoCommit(false);
            String addFriendString = ("INSERT INTO F_Req VALUES NULL " + ? + ? + " false");
            addFriendStatement = connection.prepareStatement(addFriendStatement);
            addFriendStatement.setInt(1, id);
            addFriendStatement.setInt(2, fid);
            connection.commit();
        }

    } catch (SQLException e){
        return completed;
    } finally {
        if (addFriendStatement != null) {
            addFriendStatement.close();
            completed = true;
        }
        con.setAutoCommit(true);
        return completed;
    }
}


public bool trustFriend(){
    bool completed = false;
    try{
        Statement IDstatement = connection.createStatement();
        ResultSet rs = IDstatement.executeQuery("select * from User where email = " + friend_email);
        int fid = null;
        while (rs.next()) {
            fid = rs.getInt("uid");
        }

        if(fid = null){
            return completed;
        }
        else{
            PreparedStatement trustFriendStatement = null;
            connection.setAutoCommit(false);
            String addFriendString = ("INSERT INTO F_Req VALUES NULL " + ? + ? + " true");
            trustFriendStatement = connection.prepareStatement(addFriendStatement);
            trustFriendStatement.setInt(1, id);
            trustFriendStatement.setInt(2, fid);
            connection.commit();
        }

    } catch (SQLException e){
        return completed;
    } finally {
        if (trustFriendStatement != null) {
            trustFriendStatement.close();
            completed = true;
        }
        con.setAutoCommit(true);
        return completed;
    }
}


public int register(){
    try{
        Statement IDstatement = connection.createStatement();
        ResultSet rs = IDstatement.executeQuery("select * from User where email = " + email);
        int id = null;
        while (rs.next()) {
            id = rs.getInt("uid");
        }

        if(fid != null){
            return 0;
        }
        else{
            PreparedStatement registerStatement = null;
            connection.setAutoCommit(false);
            String addFriendString = ("INSERT INTO User VALUES " + ? + " NULL " + ? + ? + " 0.00");
            registerStatement = connection.prepareStatement(addFriendStatement);
            registerStatement.setString(1, name);
            registerStatement.setString(2, email);
            registerStatement.setString(3, pass);
            connection.commit();
        }

    } catch (SQLException e){
        return completed;
    } finally {
        if (registerStatement != null) {
            registerStatement.close();
            completed = true;
        }
        con.setAutoCommit(true);
        return completed;
    }
}

}