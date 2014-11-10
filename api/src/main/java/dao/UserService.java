package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import utility.DBUtility;
import domain.User;
import domain.FriendRequest;

public class UserService {
 
 private Connection connection;

 public UserService() {
  connection = DBUtility.getConnection();
 }
  

 public List<User> getAllUsers() {
  List<User> users = new ArrayList<User>();
  try {
   Statement statement = connection.createStatement();
   ResultSet rs = statement.executeQuery("select * from User");
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

public User getUser(int id){
    User user = null;
    try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from User WHERE uid = " + id);
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String name = rs.getString("uname");
                Double wallet = rs.getDouble("wallet");
                String email = rs.getString("email");
                user = new User(uid, name, wallet, email);
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return user;
}

public List<User> getFriends(int id) {
    List<User> users = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Friends where friend1 = " + id + " or friend2 = " + id);
        while (rs.next()) {
            int uid1 = rs.getInt("friend1");
            int uid2 = rs.getInt("friend2");
            int friendID = 0;

            //check which user is not you
            if (uid1 == id) {
                friendID = uid2;
            } else {
                friendID = uid1;
            }

            //Get user object from database, put it in list
            Statement userStatement = connection.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from User where uid = " + friendID);
            while(userRS.next()) {
                String name = userRS.getString("uname");
                Double wallet = userRS.getDouble("wallet");
                String email = userRS.getString("email");
                User user = new User(friendID, name, wallet, email);
                if (users.contains(user) == false) {
                    users.add(user);
                }
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }

    return users;
}


public List<User> getTrustedFriends(int id) {
    List<User> users = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Friends where friend1 = " + id + "or friend2 = " + id + "and trust = 1");
        while (rs.next()) {
            int uid1 = rs.getInt("friend1");
            int uid2 = rs.getInt("friend2");
            int friendID = 0;

            //check which user is not you
            if (uid1 == id) {
                friendID = uid2;
            } else {
                friendID = uid1;
            }

            //Get user object from database, put it in list
            Statement userStatement = connection.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from User where uid = " + friendID);
            while(userRS.next()) {
                String name = userRS.getString("uname");
                Double wallet = userRS.getDouble("wallet");
                String email = userRS.getString("email");
                User user = new User(friendID, name, wallet, email);
                if (users.contains(user) == false) {
                    users.add(user);
                }
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }

    return users;
}

public ResponseEntity<String> addFriend(int id, int friend_id) throws SQLException{
    PreparedStatement addFriendStatement = null;
    try{
        //connection.setAutoCommit(false);
        String addFriendString = "INSERT INTO F_Req VALUES(NULL, ?, ?, false)";
        addFriendStatement = connection.prepareStatement(addFriendString);
        addFriendStatement.setInt(1, id);
        addFriendStatement.setInt(2, friend_id);
        addFriendStatement.executeUpdate();

    } catch (SQLException e){
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
        if (addFriendStatement != null) {
            addFriendStatement.close();
            //connection.setAutoCommit(true);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        //connection.setAutoCommit(true);
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


public ResponseEntity<String> trustFriend(int id, int friend_id) throws SQLException{
    PreparedStatement trustFriendStatement = null;
    try{
        //connection.setAutoCommit(false);
        String trustFriendString = "INSERT INTO F_Req VALUES(NULL, ?, ?, true)";
        trustFriendStatement = connection.prepareStatement(trustFriendString);
        trustFriendStatement.setInt(1, id);
        trustFriendStatement.setInt(2, friend_id);
        trustFriendStatement.executeUpdate();
    } catch (SQLException e){
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
        if (trustFriendStatement != null) {
            trustFriendStatement.close();
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        //connection.setAutoCommit(true);
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


public ResponseEntity<String> register(User user) throws SQLException{
    PreparedStatement registerStatement = null;
    System.out.println(user.getName());
    System.out.println(user.getEmail());
    try{
        Statement IDstatement = connection.createStatement();
        String idQuery = "select * from User where email = '" + user.getEmail()+"'";
        System.out.println(idQuery);
        ResultSet rs = IDstatement.executeQuery(idQuery);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("uid");
        }

        if(id != 0){
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        else{
            //connection.setAutoCommit(false);
            String registerString = "insert into User values(?,NULL,?,'test',0.00)";
            registerStatement = connection.prepareStatement(registerString);
            registerStatement.setString(1, user.getName());
            registerStatement.setString(2, user.getEmail());
            registerStatement.executeUpdate();
        }

    } catch (SQLException e){
        e.printStackTrace();
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
        if (registerStatement != null) {
            registerStatement.close();
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        //connection.setAutoCommit(true);
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }
}

public List<User> getFriendRequests(Integer id){
    List<User> fReqs = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from F_Req where receiver = " + id + " and trust = false");
        while (rs.next()) {
            Integer sender = rs.getInt("sender");

            Statement getUsers = connection.createStatement();
            ResultSet userRS = getUsers.executeQuery("select * from User where uid = " + sender);
            while(userRS.next()){
                int uid = userRS.getInt("uid");
                String name = userRS.getString("uname");
                Double wallet = userRS.getDouble("wallet");
                String email = userRS.getString("email");
                User user = new User(uid, name, wallet, email);
                if(fReqs.contains(user) == false){
                    fReqs.add(user);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return fReqs;

}


public List<User> getTrustRequests(int id){
    List<User> tReqs = new ArrayList<User>();
    try {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from F_Req where receiver = " + id + " and trust = true");
        while (rs.next()) {
            Integer sender = rs.getInt("sender");

            Statement getUsers = connection.createStatement();
            ResultSet userRS = getUsers.executeQuery("select * from User where uid = " + sender);
            while(userRS.next()){
                int uid = userRS.getInt("uid");
                String name = userRS.getString("uname");
                Double wallet = userRS.getDouble("wallet");
                String email = userRS.getString("email");
                User user = new User(uid, name, wallet, email);
                if(tReqs.contains(user) == false){
                    tReqs.add(user);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tReqs;
}

public ResponseEntity<String> acceptFriendRequest(int id, int friend_id) throws SQLException{
    PreparedStatement acceptStatement = null;
    try{
        Statement statement = connection.createStatement();
        statement.executeQuery("delete from F_Req where receiver = " + id + " and receiver = " + friend_id + " and trust = false");

        //connection.setAutoCommit(false);
        String acceptString = "insert into Friends values(?, ?, false);";

        acceptStatement = connection.prepareStatement(acceptString);
        acceptStatement.setInt(1, id);
        acceptStatement.setInt(2, friend_id);
        acceptStatement.executeUpdate();

    } catch (SQLException e){
        e.printStackTrace();
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
        if (acceptStatement != null) {
            acceptStatement.close();
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        //connection.setAutoCommit(true);
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }
}


public ResponseEntity<String> acceptTrustRequest(int id, int friend_id) throws SQLException{
    PreparedStatement acceptStatement = null;
    try{
        Statement statement = connection.createStatement();
        statement.executeQuery("delete from F_Req where receiver = " + id + " and receiver = " + friend_id + " and trust = true");

        //connection.setAutoCommit(false);
        String acceptString = "update Friends set trust = true where friend1 = ? and friend2 = ? or friend1 = ? and friend2 = ?";
        acceptStatement = connection.prepareStatement(acceptString);
        acceptStatement.setInt(1, id);
        acceptStatement.setInt(2, friend_id);
        acceptStatement.setInt(3, friend_id);
        acceptStatement.setInt(4, id);
        acceptStatement.executeUpdate();

    } catch (SQLException e){
        e.printStackTrace();
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
        if (acceptStatement != null) {
            acceptStatement.close();
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
        //connection.setAutoCommit(true);
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }
}

}