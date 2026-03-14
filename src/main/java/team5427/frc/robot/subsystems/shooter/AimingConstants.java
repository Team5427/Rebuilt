package team5427.frc.robot.subsystems.shooter;

import team5427.lib.tables.LookUpTable;

public final class AimingConstants {

  public static final LookUpTable kShootingTable = new LookUpTable();

  static {

    // Vertical offset:
    // hub height = 56.5 in
    // shooter height = 5.547244 in
    // y = 50.952756 in = 1.294 m
    // d2D = sqrt(d3D^2 - y^2)

    // ---------------- Pivot Angle (keys are 2D horizontal meters) ----------------
    kShootingTable.addPivotAngle(2.707, 29.0); // 3.0m 3D
    kShootingTable.addPivotAngle(2.982, 29.0); // 3.25m 3D
    kShootingTable.addPivotAngle(3.253, 29.0); // 3.5m 3D
    kShootingTable.addPivotAngle(3.519, 29.0); // 3.75m 3D
    kShootingTable.addPivotAngle(3.785, 29.5); // 4.0m 3D
    kShootingTable.addPivotAngle(4.048, 31.0); // 4.25m 3D
    kShootingTable.addPivotAngle(4.310, 32.0); // 4.5m 3D
    kShootingTable.addPivotAngle(4.571, 34.0); // 4.75m 3D
    kShootingTable.addPivotAngle(4.830, 35.0); // 5.0m 3D

    // ---------------- Flywheel Speed ----------------
    kShootingTable.addFlyWheelSpeed(2.707, 12.85);
    kShootingTable.addFlyWheelSpeed(2.982, 12.95);
    kShootingTable.addFlyWheelSpeed(3.253, 13.65);
    kShootingTable.addFlyWheelSpeed(3.519, 14.0);
    kShootingTable.addFlyWheelSpeed(3.785, 14.4);
    kShootingTable.addFlyWheelSpeed(4.048, 14.9);
    kShootingTable.addFlyWheelSpeed(4.310, 15.9);
    kShootingTable.addFlyWheelSpeed(4.571, 16.3);
    kShootingTable.addFlyWheelSpeed(4.800, 16.5);

    // ---------------- Time Of Flight ----------------
    kShootingTable.addTimeOfFlight(2.707, 0.85);
    kShootingTable.addTimeOfFlight(2.982, 0.9);
    kShootingTable.addTimeOfFlight(3.253, 0.95);
    kShootingTable.addTimeOfFlight(3.519, 1.0);
    kShootingTable.addTimeOfFlight(3.785, 1.05);
    kShootingTable.addTimeOfFlight(4.048, 1.1);
    kShootingTable.addTimeOfFlight(4.310, 1.15);
    kShootingTable.addTimeOfFlight(4.571, 1.2);
    kShootingTable.addTimeOfFlight(4.830, 1.225);
  }
}
