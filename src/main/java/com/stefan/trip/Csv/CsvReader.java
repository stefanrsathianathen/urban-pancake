package com.stefan.trip.Csv;

import com.stefan.trip.objects.Tap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

  // Given a file name find it in resources and get it mapped into the list of taps
  public static List<Tap> parse(String filename)
  {
    File inputFile = getFile(filename);
    if (inputFile == null)
    {
      System.out.println("Error: file doesn't exist");
      System.exit(1);
    }
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
      String line;
      //burn the header
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        records.add(Arrays.asList(values));
      }
    } catch (Exception e){
      System.out.println("Error");
      e.printStackTrace();
      System.exit(1);
    }

    return getTaps(records);
  }

  //convert list of list of strings into tap objects
  private static List<Tap> getTaps(List<List<String>> records)
  {
    List<Tap> taps = new ArrayList<>();

    for(List<String> line: records)
    {
      taps.add(new Tap(line.get(0),line.get(1),line.get(2),line.get(3),line.get(4),line.get(5),line.get(6)));
    }

    return taps;
  }

  //get the file
  private static File getFile(String fileName)
  {
    ClassLoader classLoader = CsvReader.class.getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      System.out.println("Resource not found");
      return null;
    } else {
      try
      {
        return new File(resource.getFile());
      } catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return null;
  }
}
