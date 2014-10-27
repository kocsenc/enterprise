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

}