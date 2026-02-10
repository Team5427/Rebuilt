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

  /**
   * @param distance - in Meters
   * @param pivotAngle - in Degrees
   */
  public void addPivotAngle(Double distance, Double pivotAngle) {
    pivotAngleLookup.put(distance, pivotAngle);
  }

  /**
   * @param distance - in Meters
   * @param speed - in Meters per Second
   */
  public void addFlyWheelSpeed(Double distance, Double speed) {
    flywheelLookup.put(distance, speed);
  }

  /**
   * @param distance - in Meters
   * @param time - in Seconds
   */
  public void addTimeOfFlight(Double distance, Double time) {
    timeOfFlightLookup.put(distance, time);
  }

  public Double getPivotAngle(Double distance) {
    return pivotAngleLookup.get(distance);
  }

  public Double getFlyWheelSpeed(Double distance) {
    return flywheelLookup.get(distance);
  }

  public Double getTimeOfFlight(Double distance) {
    return timeOfFlightLookup.get(distance);
  }
}
