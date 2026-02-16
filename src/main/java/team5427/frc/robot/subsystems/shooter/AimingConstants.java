package team5427.frc.robot.subsystems.shooter;

import team5427.lib.tables.LookUpTable;

public final class AimingConstants {
  public static final LookUpTable kShootingTable = new LookUpTable();

  static {
    kShootingTable.addPivotAngle(1.0, 68.0);
    kShootingTable.addPivotAngle(2.0, 58.0);
    kShootingTable.addPivotAngle(3.0, 50.0);
    kShootingTable.addPivotAngle(4.0, 44.0);
    kShootingTable.addPivotAngle(5.0, 39.0);
    kShootingTable.addPivotAngle(6.0, 35.0);
    kShootingTable.addPivotAngle(7.0, 32.0);
    kShootingTable.addPivotAngle(8.0, 29.0);
    kShootingTable.addPivotAngle(9.0, 27.0);
    kShootingTable.addPivotAngle(10.0, 25.0);

    kShootingTable.addFlyWheelSpeed(1.0, 8.0);
    kShootingTable.addFlyWheelSpeed(2.0, 9.5);
    kShootingTable.addFlyWheelSpeed(3.0, 10.5);
    kShootingTable.addFlyWheelSpeed(4.0, 11.5);
    kShootingTable.addFlyWheelSpeed(5.0, 12.5);
    kShootingTable.addFlyWheelSpeed(6.0, 13.5);
    kShootingTable.addFlyWheelSpeed(7.0, 14.5);
    kShootingTable.addFlyWheelSpeed(8.0, 15.5);
    kShootingTable.addFlyWheelSpeed(9.0, 16.5);
    kShootingTable.addFlyWheelSpeed(10.0, 17.5);

    kShootingTable.addTimeOfFlight(1.0, 0.25);
    kShootingTable.addTimeOfFlight(2.0, 0.35);
    kShootingTable.addTimeOfFlight(3.0, 0.45);
    kShootingTable.addTimeOfFlight(4.0, 0.55);
    kShootingTable.addTimeOfFlight(5.0, 0.62);
    kShootingTable.addTimeOfFlight(6.0, 0.70);
    kShootingTable.addTimeOfFlight(7.0, 0.78);
    kShootingTable.addTimeOfFlight(8.0, 0.85);
    kShootingTable.addTimeOfFlight(9.0, 0.92);
    kShootingTable.addTimeOfFlight(10.0, 1.00);
  }
}
