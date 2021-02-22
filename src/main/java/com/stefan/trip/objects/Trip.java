package com.stefan.trip.objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {

  public static final SimpleDateFormat stringDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  public Date started;
  public Date finished;
  public long durationSecs;
  public String fromStopId;
  public String toStopId;
  public Double chargeAmount;
  public String companyId;
  public String busId;
  public String pan;
  public TripStatus status;

  public Trip(Date started, Date finished, String fromStopId, String toStopId, Double chargeAmount, String companyId, String busId, String pan, TripStatus status)
  {
    this.started = started;
    this.finished = finished;
    this.fromStopId = fromStopId;
    this.toStopId = toStopId;
    this.chargeAmount = chargeAmount;
    this.companyId = companyId;
    this.busId = busId;
    this.pan = pan;
    this.status = status;
    this.durationSecs =  (finished.getTime() - started.getTime())/1000;
  }

  public Trip(Tap tapOn, Tap tapOff, Double chargeAmount, TripStatus status)
  {
    this(tapOn.date, tapOff.date, tapOn.stopId, tapOff.stopId, chargeAmount, tapOn.companyId, tapOff.busId, tapOff.pan, status);
  }

  //write object into csv string
  public String toCsvRow()
  {
    return String.format("%s, %s, %s, %s, %s, $%.2f, %s, %s, %s, %s",
            stringDateFormat.format(this.started),
            stringDateFormat.format(this.finished),
            this.durationSecs,
            this.fromStopId,
            this.toStopId,
            this.chargeAmount,
            this.companyId,
            this.busId,
            this.pan,
            this.status);
  }

  @Override
  public String toString()
  {
    return "Trip{" +
            "started=" + started +
            ", finished=" + finished +
            ", durationSecs=" + durationSecs +
            ", fromStopId='" + fromStopId + '\'' +
            ", toStopId='" + toStopId + '\'' +
            ", chargeAmount=" + chargeAmount +
            ", companyId='" + companyId + '\'' +
            ", busId='" + busId + '\'' +
            ", pan='" + pan + '\'' +
            ", status=" + status +
            '}';
  }
}
