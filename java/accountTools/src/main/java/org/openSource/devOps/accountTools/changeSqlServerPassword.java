package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class changeSqlServerPassword {
  public static void main(String args[]) throws Exception {
    String userName = args[0];
    String oldPassword = args[1];
    String newPassword = args[2];
    String connUrl = args[3];   
    
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }

    Connection conn=DriverManager.getConnection(connUrl, userName, oldPassword);
    String query = "ALTER LOGIN " + userName + " WITH PASSWORD = '" + newPassword + "' OLD_PASSWORD = '" + oldPassword + "';";
    
    PreparedStatement stmt=conn.prepareStatement(query);
    stmt.execute();
  }
}