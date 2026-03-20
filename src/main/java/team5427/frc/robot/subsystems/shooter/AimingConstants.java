package team5427.frc.robot.subsystems.shooter;

import team5427.frc.robot.FieldConstants;
import team5427.lib.kinematics.shooter.ProjectileSimulator;
import team5427.lib.kinematics.shooter.ShotCalculator;

public final class AimingConstants {

  // need to tune from actual robot
  public static ProjectileSimulator.SimParameters params =
      new ProjectileSimulator.SimParameters(
          0.215, // ball mass kg
          0.1501, // ball diameter m
          0.47, // drag coeff (smooth sphere)
          0.2, // Magnus coeff
          1.225, // air density
          ShooterConstants.kRobotToShooterTransform.getZ(), // exit height (m), floor to where the ball leaves the shooter
          ShooterConstants
              .kBottomFlywheelRadiusMeters, // flywheel diameter (m), measure with calipers
          FieldConstants.Hub.topCenterPoint.getZ(), // target height (m), from game manual
          0.6, // slip factor (0=no grip, 1=perfect), tune this on the real robot
          29.0, // launch angle from horizontal, measure from CAD
          0.001, // sim timestep
          1500,
          6000,
          20,
          5.0 // RPM search range, iterations, max sim time
          );

  public static ProjectileSimulator sim = new ProjectileSimulator(params);
  public static ProjectileSimulator.GeneratedLUT lut = sim.generateLUT();

  // adjust for robot CAD
  public static ShotCalculator.Config config = new ShotCalculator.Config();

  static {
    config.launcherOffsetX = ShooterConstants.kRobotToShooterTransform.getX(); // how far forward the
    // launcher is from robot
    // center (m)
    config.launcherOffsetY = 0.0; // how far left, 0 if centered
    config.phaseDelayMs = 30.0; // your vision pipeline latency
    config.mechLatencyMs = 20.0; // how long the mechanism takes to respond
    config.maxTiltDeg = 5.0; // suppress firing when chassis tilts past this (bumps/ramps)
    config.headingSpeedScalar = 1.0; // heading tolerance tightens with robot speed (0 to disable)
    config.headingReferenceDistance = 2.5; // heading tolerance scales with distance from hub
  }

  public static ShotCalculator shotCalc = new ShotCalculator(config);

  static {
    // load the LUT you generated
    for (var entry : lut.entries()) {
      if (entry.reachable()) {
        shotCalc.loadLUTEntry(entry.distanceM(), entry.rpm(), entry.tof());
      }
    }
  }

  // public static final LookUpTable kShootingTable = new LookUpTable();

  // static {

  // // Vertical offset:
  // // hub height = 56.5 in
  // // shooter height = 5.547244 in
  // // y = 50.952756 in = 1.294 m
  // // d2D = sqrt(d3D^2 - y^2)

  // // ---------------- Pivot Angle (keys are 2D horizontal meters)
  // ----------------
  // kShootingTable.addPivotAngle(2.707, 29.0); // 3.0m 3D
  // kShootingTable.addPivotAngle(2.982, 29.0); // 3.25m 3D
  // kShootingTable.addPivotAngle(3.253, 29.0); // 3.5m 3D
  // kShootingTable.addPivotAngle(3.519, 29.0); // 3.75m 3D
  // kShootingTable.addPivotAngle(3.785, 29.5); // 4.0m 3D
  // kShootingTable.addPivotAngle(4.048, 31.0); // 4.25m 3D
  // kShootingTable.addPivotAngle(4.310, 32.0); // 4.5m 3D
  // kShootingTable.addPivotAngle(4.571, 34.0); // 4.75m 3D
  // kShootingTable.addPivotAngle(4.830, 35.0); // 5.0m 3D

  // // ---------------- Flywheel Speed ----------------
  // kShootingTable.addFlyWheelSpeed(2.707, 12.85);
  // kShootingTable.addFlyWheelSpeed(2.982, 13.05);
  // kShootingTable.addFlyWheelSpeed(3.253, 13.75);
  // kShootingTable.addFlyWheelSpeed(3.519, 14.1);
  // kShootingTable.addFlyWheelSpeed(3.785, 14.5);
  // kShootingTable.addFlyWheelSpeed(4.048, 15.0);
  // kShootingTable.addFlyWheelSpeed(4.310, 16.0);
  // kShootingTable.addFlyWheelSpeed(4.571, 16.4);
  // kShootingTable.addFlyWheelSpeed(4.800, 16.5);

  // // ---------------- Time Of Flight ----------------
  // kShootingTable.addTimeOfFlight(2.707, 0.85);
  // kShootingTable.addTimeOfFlight(2.982, 0.9);
  // kShootingTable.addTimeOfFlight(3.253, 0.95);
  // kShootingTable.addTimeOfFlight(3.519, 1.0);
  // kShootingTable.addTimeOfFlight(3.785, 1.05);
  // kShootingTable.addTimeOfFlight(4.048, 1.1);
  // kShootingTable.addTimeOfFlight(4.310, 1.15);
  // kShootingTable.addTimeOfFlight(4.571, 1.2);
  // kShootingTable.addTimeOfFlight(4.830, 1.225);
  // }
}
