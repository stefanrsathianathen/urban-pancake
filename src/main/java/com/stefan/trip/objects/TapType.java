package com.stefan.trip.objects;

public enum TapType {

  ON("ON"), OFF("OFF");

  public String type;

  TapType(String tapeType) {
    type = tapeType;
  }

  @Override
  public String toString()
  {
    return "TapType{" +
            "type='" + type + '\'' +
            '}';
  }
}
