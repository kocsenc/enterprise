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

import utility.DBUtility;
import domain.PaymentType;
import domain.CreditCard;
import domain.BankAccount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class PaymentTypeService {

  private Connection connection;

  public PaymentTypeService() {
    connection = DBUtility.getConnection();
  }

  public PaymentType getPaymentTypeById(int id){
    PaymentType paymentType = new PaymentType();
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("select * from Payment_Type where uid = " + id);
      while (rs.next()){
        int uid = rs.getInt("uid");
        int ccid = rs.getInt("creditcard");
        int baid = rs.getInt("bankaccount");

        CreditCard creditCard = null;
        Statement ccStatement = connection.createStatement();
        ResultSet ccRS = ccStatement.executeQuery("select * from Credit_Card where ccid = " + ccid);
        while (ccRS.next()) {
          Long ccnum = ccRS.getLong("ccnum");
          Date expirationDate = ccRS.getDate("experationdate");
          int ccv = ccRS.getInt("ccv");
          creditCard = new CreditCard(ccnum,expirationDate,ccv);
        }

        BankAccount bankAccount = null;
        Statement baStatement = connection.createStatement();
        ResultSet baRS = baStatement.executeQuery("select * from Bank_Account where baid = " + baid);
        while (baRS.next()){
          int routingNum = baRS.getInt("routingnum");
          Long accountNum = baRS.getLong("accountnum");
          bankAccount = new BankAccount(routingNum,accountNum);
        }

        paymentType = new PaymentType(creditCard,bankAccount);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return paymentType;
  }

  public ResponseEntity<String> addCreditCard(CreditCard cc, int userId){
    PreparedStatement preparedStatement = null;
    PreparedStatement ccIdStatement = null;
    PreparedStatement paymentTypeStatement = null;
    ResponseEntity response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    try {
      preparedStatement = connection.prepareStatement("insert into Credit_Card value(null,?,?,?)");
      preparedStatement.setLong(1,cc.getCcnum());
      preparedStatement.setDate(2,cc.getExpirationDate());
      preparedStatement.setInt(3,cc.getCCV());
      preparedStatement.executeUpdate();

      ccIdStatement = connection.prepareStatement("select ccid from Credit_Card where ccnum = ? and ccv = ?");
      ccIdStatement.setLong(1,cc.getCcnum());
      ccIdStatement.setInt(2,cc.getCCV());
      ResultSet rs = ccIdStatement.executeQuery();
      
      Integer ccid = null;
      while(rs.next()){
        ccid = rs.getInt("ccid");
      }

      paymentTypeStatement = connection.prepareStatement("update Payment_Type set creditcard = ? where uid = ?");
      paymentTypeStatement.setInt(1,ccid);
      paymentTypeStatement.setInt(2,userId);
      paymentTypeStatement.executeUpdate();
      response = new ResponseEntity<String>(HttpStatus.CREATED);
    } catch (SQLException e) {
      e.printStackTrace();
      response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
        if (ccIdStatement != null) {
          ccIdStatement.close();
        }
        if (paymentTypeStatement != null) {
          paymentTypeStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
    }
    return response;
  }

}