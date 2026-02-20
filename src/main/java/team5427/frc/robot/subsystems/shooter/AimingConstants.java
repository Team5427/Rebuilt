package team5427.frc.robot.subsystems.shooter;

import team5427.lib.tables.LookUpTable;

public final class AimingConstants {
  public static final LookUpTable kShootingTable = new LookUpTable();

  static {
    kShootingTable.addPivotAngle(1.0, 17.0);
    kShootingTable.addPivotAngle(2.0, 17.0);
    kShootingTable.addPivotAngle(3.0, 17.0);
    kShootingTable.addPivotAngle(4.0, 17.0);
    kShootingTable.addPivotAngle(5.0, 17.0);
    // kShootingTable.addPivotAngle(6.0, 25.0);
    // kShootingTable.addPivotAngle(7.0, 25.0);
    // kShootingTable.addPivotAngle(8.0, 25.0);
    // kShootingTable.addPivotAngle(9.0, 25.0);
    // kShootingTable.addPivotAngle(10.0, 25.0);

    kShootingTable.addFlyWheelSpeed(1.0, 2.15);
    kShootingTable.addFlyWheelSpeed(2.0, 2.8);
    kShootingTable.addFlyWheelSpeed(3.0, 3.0);
    kShootingTable.addFlyWheelSpeed(3.5, 3.2);
    kShootingTable.addFlyWheelSpeed(4.0, 3.4);
    kShootingTable.addFlyWheelSpeed(4.5, 3.6);
    kShootingTable.addFlyWheelSpeed(5.0, 3.85);

    kShootingTable.addTimeOfFlight(1.0, 0.15);
    kShootingTable.addTimeOfFlight(2.0, 0.25);
    kShootingTable.addTimeOfFlight(3.0, 0.35);
    kShootingTable.addTimeOfFlight(4.0, 0.45);
    kShootingTable.addTimeOfFlight(5.0, 0.52);

    // kShootingTable.addTimeOfFlight(6.0, 0.70);
    // kShootingTable.addTimeOfFlight(7.0, 0.78);
    // kShootingTable.addTimeOfFlight(8.0, 0.85);
    // kShootingTable.addTimeOfFlight(9.0, 0.92);
    // kShootingTable.addTimeOfFlight(10.0, 1.00);
  }
}
