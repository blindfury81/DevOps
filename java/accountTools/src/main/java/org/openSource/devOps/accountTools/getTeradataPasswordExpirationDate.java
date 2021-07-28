package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getTeradataPasswordExpirationDate {
  public static void main(String args[]) throws Exception {
    String userName=args[0];
    String password=args[1];
    String teraUrl=args[2];

    String connUrl="jdbc:teradata://" + teraUrl;

    try {
      Class.forName("com.teradata.jdbc.TeraDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }
    Connection conn=DriverManager.getConnection(connUrl, userName, password);
    String query="SELECT a.PasswordChgDate (FORMAT 'DD-MMM-YYYY') (CHAR(11), UC), a.PasswordChgDate + b.ExpirePassword (FORMAT 'DD-MMM-YYYY') (CHAR(11), UC) FROM dbc.Users a INNER JOIN  ProfileInfoV b ON a.ProfileName = b.ProfileName";

    PreparedStatement stmt=conn.prepareStatement(query);
    ResultSet rs=stmt.executeQuery();
    while(rs.next()) {
      String lastChangeDate = rs.getString(1);
      String expirationDate = rs.getString(2);
      System.out.println("User=" + userName + ", URL=" + teraUrl + ", Changed=" + lastChangeDate + ", Expiration=" + expirationDate);
    }
  }
}