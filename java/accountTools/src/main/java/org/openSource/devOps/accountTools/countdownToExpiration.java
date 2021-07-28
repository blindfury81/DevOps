package org.openSource.devOps.accountTools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class countdownToExpiration {
  public static void main(String args[]) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    Date compareDate = sdf.parse(args[0]);
    Date currentDate = new Date();

    DateTime dt1 = new DateTime(currentDate);
    DateTime dt2 = new DateTime(compareDate);

    Integer expiresInDays = Days.daysBetween(dt1, dt2).getDays();

    System.out.print(expiresInDays);
  }
}
