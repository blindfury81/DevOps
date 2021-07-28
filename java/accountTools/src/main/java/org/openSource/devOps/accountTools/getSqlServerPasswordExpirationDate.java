package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getSqlServerPasswordExpirationDate {
  public static void main(String args[]) throws Exception {
	String userName=args[0];
    String password=args[1];
    String connUrl=args[2];
    
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }

    Connection conn=DriverManager.getConnection(connUrl, userName, password);
    String query="select FORMAT(CAST(LOGINPROPERTY('"+ userName + "', 'PasswordLastSetTime') AS DATE), 'dd-MMM-yyyy')\r\n" + 
    		"  ,FORMAT(DATEADD(dd, CAST(ISNULL(NULLIF(LOGINPROPERTY('"+  userName +"', 'DaysUntilExpiration'), 0), 180) AS NUMERIC), CAST(LOGINPROPERTY('" + userName + "', 'PasswordLastSetTime') AS DATE)), 'dd-MMM-yyyy');";

    PreparedStatement stmt=conn.prepareStatement(query);
    ResultSet rs=stmt.executeQuery();
    while(rs.next()) {
      String changeDate = rs.getString(1);
      String expirationDate = rs.getString(2);
      System.out.println("User=" + userName + ", URL=" + connUrl + ", Expiration=" + expirationDate + ", Changed=" + changeDate);
    }
  }
}