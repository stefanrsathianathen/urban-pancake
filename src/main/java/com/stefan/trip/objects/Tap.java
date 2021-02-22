package com.stefan.trip.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tap
{
  public Integer id;
  public Date date;
  public TapType tapType;
  public String stopId;
  public String companyId;
  public String busId;
  public String pan;

  public static final DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  public Tap(String id, String date, String tapType, String stopId, String companyId, String busId, String pan)
  {
    this.id = Integer.parseInt(id);
    this.date = createDate(date);
    this.tapType = tapType.trim().equals("ON") ? TapType.ON : TapType.OFF;
    this.stopId = stopId.trim();
    this.companyId = companyId.trim();
    this.busId = busId.trim();
    this.pan = pan.trim();
  }

  //convert string date into date object
  public Date createDate(String date)
  {
    try{
      return format.parse(date);
    } catch (Exception e){
      System.out.println("error creating date");
      e.printStackTrace();
      System.exit(1);
    }
    return null;
  }

  @Override
  public String toString()
  {
    return "Tap{" +
            "id=" + id +
            ", date=" + date +
            ", tapType=" + tapType +
            ", stopId='" + stopId + '\'' +
            ", companyId='" + companyId + '\'' +
            ", busId='" + busId + '\'' +
            ", pan='" + pan + '\'' +
            '}';
  }
}
