package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class verifyTeradataConnection {
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
    String query="SELECT 'connect successful'";

    PreparedStatement stmt=conn.prepareStatement(query);
    ResultSet rs=stmt.executeQuery();
    while(rs.next()) {
      String result = rs.getString(1);
      System.out.println(result);
    }
  }
}