package com.stefan.trip.Csv;

import com.stefan.trip.objects.Trip;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

  public static final String HEADER = "Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status\n";
  public static final String CSV_FILE_NAME = "trips.csv";

  //write trips to csv
  public static void write(List<Trip> trips)
  {
    File csvOutputFile = new File(CSV_FILE_NAME);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      pw.write(HEADER);
      trips.stream()
              .map(Trip::toCsvRow)
              .forEach(pw::println);
    } catch (Exception e)
    {
      System.out.println("Error writing to csv");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
