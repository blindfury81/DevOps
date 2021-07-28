package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getOraclePasswordExpirationDate {
  public static void main(String args[]) throws Exception {
    String userName=args[0];
    String password=args[1];
    String connUrl=args[2];

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }

    Connection conn=DriverManager.getConnection(connUrl, userName, password);
    String query="SELECT NVL(TO_CHAR(EXPIRY_DATE, 'YYYY-MM-DD'), 'NA') FROM USER_USERS";

    PreparedStatement stmt=conn.prepareStatement(query);
    ResultSet rs=stmt.executeQuery();
    while(rs.next()) {
      String expirationDate = rs.getString(1);
      System.out.println("User=" + userName + ", URL=" + connUrl + ", Expiration=" + expirationDate);
    }
  }
}