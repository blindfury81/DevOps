package org.openSource.devOps.accountTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class changeTeradataPassword {
  public static void main(String args[]) throws Exception {
    String userName = args[0];
    String oldPassword = args[1];
    String newPassword = args[2];
    String connUrl = "jdbc:teradata://"+args[3];

    try {
      Class.forName("com.teradata.jdbc.TeraDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }
    Connection conn=DriverManager.getConnection(connUrl, userName, oldPassword);
    String query="MODIFY USER \"" + userName + "\" AS PASSWORD = \"" + newPassword + "\"";

    PreparedStatement stmt=conn.prepareStatement(query);
    stmt.execute();
  }
}