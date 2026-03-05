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
    kShootingTable.addPivotAngle(3.785, 29.0); // 4.0m 3D
    kShootingTable.addPivotAngle(4.048, 29.0); // 4.25m 3D
    kShootingTable.addPivotAngle(4.310, 29.0); // 4.5m 3D
    kShootingTable.addPivotAngle(4.571, 29.0); // 4.75m 3D
    kShootingTable.addPivotAngle(4.830, 29.0); // 5.0m 3D

    // ---------------- Flywheel Speed ----------------
    kShootingTable.addFlyWheelSpeed(2.707, 2.85);
    kShootingTable.addFlyWheelSpeed(2.982, 2.95);
    kShootingTable.addFlyWheelSpeed(3.253, 3.05);
    kShootingTable.addFlyWheelSpeed(3.519, 3.1);
    kShootingTable.addFlyWheelSpeed(3.785, 3.25);
    kShootingTable.addFlyWheelSpeed(4.048, 3.45);
    kShootingTable.addFlyWheelSpeed(4.310, 3.65);
    kShootingTable.addFlyWheelSpeed(4.571, 3.7);
    kShootingTable.addFlyWheelSpeed(4.830, 3.95);

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
