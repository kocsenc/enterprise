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
}