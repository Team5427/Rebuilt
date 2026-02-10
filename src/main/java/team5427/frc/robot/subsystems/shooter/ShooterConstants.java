package team5427.frc.robot.subsystems.shooter;

import team5427.lib.tables.LookUpTable;

public final class ShooterConstants {
  public static LookUpTable flightTimes = new LookUpTable();

  static {
    flightTimes.addtimeOfFlight(0.0, 3.5);
    flightTimes.addtimeOfFlight(1.0, 4.0);
    flightTimes.addtimeOfFlight(2.0, 4.5);
    flightTimes.addtimeOfFlight(3.0, 5.0);
    flightTimes.addtimeOfFlight(4.0, 5.5);
  }
}
