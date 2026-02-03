package team5427.lib.tables;

import edu.wpi.first.math.interpolation.InterpolatingTreeMap;

public class LookUpTable {
  private InterpolatingTreeMap<Double, Double> pivotAngleLookup;
  private InterpolatingTreeMap<Double, Double> flywheelLookup;
  private InterpolatingTreeMap<Double, Double> timeOfFlightLookup;

  public LookUpTable() {
    pivotAngleLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
    flywheelLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
    timeOfFlightLookup =
        new InterpolatingTreeMap<>(new TableInverseInterpolator(), new TableInterpolator());
  }

  public void addpivotAngle(Double distance, Double pivotAngle) {
    pivotAngleLookup.put(distance, pivotAngle);
  }

  public void addflyWheelSpeed(Double distance, Double speed) {
    flywheelLookup.put(distance, speed);
  }

  public void addtimeOfFlight(Double distance, Double time) {
    timeOfFlightLookup.put(distance, time);
  }

  public Double getpivotAngle(Double distance) {
    return pivotAngleLookup.get(distance);
  }

  public Double getflyWheelSpeed(Double distance) {
    return flywheelLookup.get(distance);
  }

  public Double gettimeOfFlight(Double distance) {
    return timeOfFlightLookup.get(distance);
  }
}
