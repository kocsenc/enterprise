package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import utility.DBUtility;
import domain.Request;

public class RequestService{

  private Connection connection;

  public RequestService() {
    connection = DBUtility.getConnection();
  }

  public List<Request> getRequestsById(int id) {
    List<Request> requests = new ArrayList<Request>();
    try {
      Statement statement = connection.createStatement();
      String query = "select * from Request where sender = "+ id + " or receiver = "+ id + " and fulfilled = false";
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()){
        int rid = rs.getInt("rid");
        int sender = rs.getInt("sender");
        int reciever = rs.getInt("receiver");
        String description = rs.getString("description");
        Double amount = rs.getDouble("amount");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");
        Boolean fufilled = rs.getBoolean("fulfilled");
        Request request = new Request(rid,sender,reciever,description,amount,startDate,endDate,fufilled);
        requests.add(request);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return requests;
  }

  public List<Request> getPaymentsById(int id) {
    List<Request> requests = new ArrayList<Request>();
    try {
      Statement statement = connection.createStatement();
      String query = "select * from Request where sender = "+ id + " or receiver = "+ id + " and fulfilled = true";
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()){
        int rid = rs.getInt("rid");
        int sender = rs.getInt("sender");
        int reciever = rs.getInt("receiver");
        String description = rs.getString("description");
        Double amount = rs.getDouble("amount");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");
        Boolean fufilled = rs.getBoolean("fulfilled");
        Request request = new Request(rid,sender,reciever,description,amount,startDate,endDate,fufilled);
        requests.add(request);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return requests;
  }

  public ResponseEntity<String> postRequest(Request request) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement("insert into Request values(null,?,?,?,?,?,?,?)");
      preparedStatement.setInt(1,request.getSender());
      preparedStatement.setInt(2,request.getReciever());
      preparedStatement.setString(3,request.getDescription());
      preparedStatement.setDouble(4,request.getAmount());
      preparedStatement.setDate(5,request.getStartDate());
      preparedStatement.setDate(6,request.getEndDate());
      preparedStatement.setBoolean(7,request.getFulfilled());
      preparedStatement.executeUpdate();
      return new ResponseEntity<String>(HttpStatus.CREATED);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<String> fulfillRequest(int reqId, int userId) {
    Request request = getRequestById(reqId);
    if (request == null) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    boolean withdraw = withdrawMoney(request.getReciever(),request.getAmount());
    if (withdraw == false) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    boolean deposit = depositMoney(request.getSender(),request.getAmount());
    if (deposit == false) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    boolean markFulfilled = markRequestFulfilled(reqId);
    if (markFulfilled == false) {
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  public Request getRequestById(int requestId){
    PreparedStatement statement = null;
    Request request = null;
    try {
      statement = connection.prepareStatement("select * from Request where rid = ?");
      statement.setInt(1,requestId);
      ResultSet rs = statement.executeQuery();
      while(rs.next()){
        int rid = rs.getInt("rid");
        int sender = rs.getInt("sender");
        int reciever = rs.getInt("receiver");
        String description = rs.getString("description");
        Double amount = rs.getDouble("amount");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");
        Boolean fufilled = rs.getBoolean("fulfilled");
        request = new Request(rid,sender,reciever,description,amount,startDate,endDate,fufilled);
      }
    } catch (SQLException e){
      e.printStackTrace();
      request = null;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e){
        System.out.println("Error closing prepared statement. See stack trace below:");
        e.printStackTrace();
      }      
    }
    return request;
  }

  private boolean withdrawMoney(int userId,double amount){
    PreparedStatement statement = null;
    boolean result = false;
    try {
      statement = connection.prepareStatement("update User set wallet = (wallet - ?) where uid = ?");
      statement.setDouble(1,amount);
      statement.setInt(2,userId);
      statement.executeUpdate();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      result = false;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e){
        System.out.println("Error closing prepared statement. See stack trace below:");
        e.printStackTrace();
      }  
    }
    return result;
  }

  private boolean depositMoney(int userId,double amount){
    PreparedStatement statement = null;
    boolean result = false;
    try {
      statement = connection.prepareStatement("update User set wallet = (wallet + ?) where uid = ?");
      statement.setDouble(1,amount);
      statement.setInt(2,userId);
      statement.executeUpdate();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      result = false;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e){
        System.out.println("Error closing prepared statement. See stack trace below:");
        e.printStackTrace();
      }  
    }
    return result;
  }

  private boolean markRequestFulfilled(int reqId){
    PreparedStatement statement = null;
    boolean result = false;
    try {
      statement = connection.prepareStatement("update Request set fulfilled = 1 where rid = ?");
      statement.setInt(1,reqId);
      statement.executeUpdate();
      result = true;
    } catch (SQLException e) {
      e.printStackTrace();
      result = false;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e){
        System.out.println("Error closing prepared statement. See stack trace below:");
        e.printStackTrace();
      }  
    }
    return result;
  }


  
}