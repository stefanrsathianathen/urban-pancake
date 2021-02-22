package com.stefan.trip;

import com.stefan.trip.Csv.CsvReader;
import com.stefan.trip.Csv.CsvWriter;
import com.stefan.trip.objects.Tap;
import com.stefan.trip.objects.Trip;

import java.util.List;

public class Main
{

  public static String filename = "taps.csv";

  public static void main(String[] args)
  {
    //Only use the user supplied filename if there is only 1 arg
    if(args.length == 1)
      filename = args[0];

    List<Tap> taps = CsvReader.parse(filename);

    CostCalculator calculator = new CostCalculator();
    List<Trip> trips = calculator.calculateCosts(taps);

    CsvWriter.write(trips);
  }
}
