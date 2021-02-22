package com.stefan.trip;

import com.stefan.trip.objects.Tap;
import com.stefan.trip.objects.TapType;
import com.stefan.trip.objects.Trip;
import com.stefan.trip.objects.TripStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CostCalculator {

  public HashMap<String, HashMap<String, Double>> prices = new HashMap<>();

  public CostCalculator()
  {
    populatePrices();
  }

  //Hard code the test prices
  //Would be better to use a db table for this, since there would be a huge amount of stops
  //and storing this in memory would be inefficient
  private void populatePrices()
  {
    HashMap<String, Double> stop1 = new HashMap<>();
    HashMap<String, Double> stop2 = new HashMap<>();
    HashMap<String, Double> stop3 = new HashMap<>();

    stop1.put("Stop2", 3.25);
    stop1.put("Stop3", 7.30);
    stop1.put("MAX", 7.30);
    prices.put("Stop1", stop1);

    stop2.put("Stop1", 3.25);
    stop2.put("Stop3", 5.50);
    stop2.put("MAX", 5.50);
    prices.put("Stop2", stop2);

    stop3.put("Stop1", 7.30);
    stop3.put("Stop2", 5.50);
    stop3.put("MAX", 7.30);
    prices.put("Stop3", stop3);
  }

  //Given a list of taps calculate the trips and their costs for all users
  public List<Trip> calculateCosts(List<Tap> taps){
    Set<String> uniquePans = getPans(taps);
    List<Trip> trips = new ArrayList<>();

    for(String upan: uniquePans)
    {
      List<Tap> panTaps = taps
                            .stream()
                            .filter(tap -> tap.pan.equals(upan))
                            .collect(Collectors.toList());
      List<Trip> uPanTrips = calculateUserTripCosts(panTaps);
      trips.addAll(uPanTrips);
    }
    return trips;
  }

  //Filters to get all the unique pans
  private Set<String> getPans(List<Tap> taps)
  {
    Set<String> uniquePans = new HashSet<>();
    for(Tap tap: taps)
    {
      uniquePans.add(tap.pan);
    }
    return uniquePans;
  }

  // Calculate the trips for a given pans taps
  private List<Trip> calculateUserTripCosts(List<Tap> taps)
  {
    int tapOn = 0;
    int tapOff = 1;
    List<Trip> trips = new ArrayList<>();
    while(tapOn < taps.size()){

      TripStatus status;
      Double tripCost = 0.0;
      Tap tapOnTap = taps.get(tapOn);
      Tap tapOffTap = tapOff >= taps.size()? null : taps.get(tapOff);
      status = tripStatus(tapOnTap, tapOffTap);
      if(status == TripStatus.COMPLETED)
        tripCost = getPrice(tapOnTap.stopId, tapOffTap.stopId);
      else if (status == TripStatus.INCOMPLETE)
      {
        tripCost = getPrice(tapOnTap.stopId, "MAX");
        tapOffTap = tapOnTap;
      }

      trips.add(new Trip(tapOnTap, tapOffTap, tripCost, status));

      if(status == TripStatus.COMPLETED || status == TripStatus.CANCELLED)
      {
        tapOn = tapOff + 1;
        tapOff = tapOn + 1;
      } else {
        tapOn += 1;
        tapOff += 1;
      }
    }

    return trips;
  }

  // Figure out if a trip was completed/cancelled/incomplete
  private TripStatus tripStatus(Tap tapOn, Tap tapOff)
  {
    if(tapOff == null)
    {
      return TripStatus.INCOMPLETE;
    }
    else if ((tapOn.tapType == TapType.ON && tapOff.tapType == TapType.OFF) && !(tapOn.stopId.equals(tapOff.stopId)))
    {
      return TripStatus.COMPLETED;
    } else if (tapOn.tapType == TapType.ON && tapOff.tapType == TapType.OFF){
      return TripStatus.CANCELLED;
    }

    return TripStatus.INCOMPLETE;
  }

  //Get the price for a trip between two stops
  private Double getPrice(String tapOnStop, String tapOffStop)
  {
    return prices.get(tapOnStop).get(tapOffStop);
  }
}
