import static edu.wpi.first.units.Units.MetersPerSecond;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.LinearVelocity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team5427.frc.robot.commands.shooting.MoveWhileShoot;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.lib.detection.tuples.Tuple2Plus;

public class ImpartedVelocityTest {

  private static final double kEpsilon = 1e-6;
  private MoveWhileShoot shoot;

  @BeforeEach
  public void setup() {
    shoot = new MoveWhileShoot();
  }

  @Test
  public void stationaryRobotZeroPivotPreservesMagnitude() {
    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    LinearVelocity exitSpeed = MetersPerSecond.of(10.0);
    Translation3d result = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    assertEquals(
        exitSpeed.in(MetersPerSecond),
        result.getNorm(),
        1e-3,
        "Stationary robot: imparted velocity magnitude should equal exit speed");
  }

  @Test
  public void stationaryRobotMagnitudePreservedAtVariousPivots() {
    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    LinearVelocity exitSpeed = MetersPerSecond.of(8.0);

    for (int deg = 0; deg <= 90; deg += 15) {
      Translation3d result =
          shoot.calculateImpartedVelocity(Rotation2d.fromDegrees(deg), exitSpeed);
      assertEquals(
          exitSpeed.in(MetersPerSecond),
          result.getNorm(),
          1e-3,
          "Magnitude should be preserved at pivot " + deg + "°");
    }
  }

  @Test
  public void translatingRobotAddsVelocityToXComponent() {
    double robotVx = 2.0;

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    LinearVelocity exitSpeed = MetersPerSecond.of(5.0);
    Translation3d stationary = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(robotVx, 0, 0)));

    Translation3d moving = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    assertEquals(
        stationary.getX() + robotVx,
        moving.getX(),
        kEpsilon,
        "Translating robot should add vx to the X component");
    assertEquals(
        stationary.getZ(),
        moving.getZ(),
        kEpsilon,
        "Translating robot in X should not affect Z component");
  }

  @Test
  public void translatingRobotAddsVelocityToYComponent() {
    double robotVy = 3.0;

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    LinearVelocity exitSpeed = MetersPerSecond.of(5.0);
    Translation3d stationary = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, robotVy, 0)));

    Translation3d moving = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    assertEquals(
        stationary.getY() + robotVy,
        moving.getY(),
        kEpsilon,
        "Translating robot should add vy to the Y component");
  }

  @Test
  public void symmetryOfOppositeTranslations() {
    double vx = 3.0;
    LinearVelocity exitSpeed = MetersPerSecond.of(7.0);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(vx, 0, 0)));
    Translation3d forward = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(-vx, 0, 0)));
    Translation3d backward = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    assertEquals(
        2 * vx,
        forward.getX() - backward.getX(),
        kEpsilon,
        "Opposite translations should differ by exactly 2 times vx in X");
    assertEquals(
        forward.getY(),
        backward.getY(),
        kEpsilon,
        "Y should be unaffected by X translation direction");
    assertEquals(
        forward.getZ(),
        backward.getZ(),
        kEpsilon,
        "Z should be unaffected by X translation direction");
  }

  @Test
  public void rotatingRobotAddsTangentialVelocity() {
    double omega = 1.0;

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    LinearVelocity exitSpeed = MetersPerSecond.of(5.0);
    Translation3d stationary = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, omega)));

    Translation3d rotating = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    double expectedTangential =
        omega * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm();

    assertEquals(
        stationary.getX() + expectedTangential,
        rotating.getX(),
        kEpsilon,
        "Rotation should add tangential velocity to X component");
    assertEquals(
        stationary.getY() + expectedTangential,
        rotating.getY(),
        kEpsilon,
        "Rotation should add tangential velocity to Y component");
    assertEquals(
        stationary.getZ(), rotating.getZ(), kEpsilon, "Rotation should not affect Z component");
  }

  @Test
  public void zeroExitSpeedReturnsOnlyRobotContribution() {
    double vx = 1.0;
    double vy = 2.0;
    double omega = 0.5;

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(vx, vy, omega)));

    Translation3d result =
        shoot.calculateImpartedVelocity(Rotation2d.kZero, MetersPerSecond.of(0.0));

    double expectedTangential =
        omega * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm();

    assertEquals(
        expectedTangential + vx,
        result.getX(),
        kEpsilon,
        "X should be tangential + vx when exit speed is zero");
    assertEquals(
        expectedTangential + vy,
        result.getY(),
        kEpsilon,
        "Y should be tangential + vy when exit speed is zero");
    assertEquals(
        0.0,
        result.getZ(),
        kEpsilon,
        "Z should be zero when exit speed is zero (robot motion is planar)");
  }

  @Test
  public void zComponentIndependentOfRobotMotion() {
    LinearVelocity exitSpeed = MetersPerSecond.of(8.0);
    Rotation2d pivot = Rotation2d.fromDegrees(30);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));
    double zStationary = shoot.calculateImpartedVelocity(pivot, exitSpeed).getZ();

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(4.0, 3.0, 2.0)));
    double zMoving = shoot.calculateImpartedVelocity(pivot, exitSpeed).getZ();

    assertEquals(
        zStationary,
        zMoving,
        kEpsilon,
        "Z component should be the same regardless of robot XY/omega motion");
  }

  @Test
  public void combinedTranslationAndRotationStack() {
    double vx = 1.5;
    double vy = -2.0;
    double omega = 0.8;

    LinearVelocity exitSpeed = MetersPerSecond.of(6.0);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));
    Translation3d stationary = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(vx, vy, omega)));
    Translation3d combined = shoot.calculateImpartedVelocity(Rotation2d.kZero, exitSpeed);

    double expectedTangential =
        omega * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm();

    assertEquals(
        stationary.getX() + expectedTangential + vx,
        combined.getX(),
        kEpsilon,
        "X should be flywheel + tangential + vx");
    assertEquals(
        stationary.getY() + expectedTangential + vy,
        combined.getY(),
        kEpsilon,
        "Y should be flywheel + tangential + vy");
    assertEquals(
        stationary.getZ(),
        combined.getZ(),
        kEpsilon,
        "Z should be unaffected by robot planar motion");
  }

  @Test
  public void stationaryExitDirectionMatchesShooterYaw() {
    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));

    Translation3d result =
        shoot.calculateImpartedVelocity(Rotation2d.kZero, MetersPerSecond.of(1.0));

    assertEquals(0.0, result.getX(), kEpsilon, "X should be ~0 due to -π/2 yaw");
    assertEquals(-1.0, result.getY(), kEpsilon, "Y should be -1.0 (forward mapped by yaw)");
    assertEquals(0.0, result.getZ(), kEpsilon, "Z should be 0 at zero pivot");
  }

  @Test
  public void printDiagnostics() {
    System.out.println("=== Stationary robot, varying pivot angles ===");
    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(0, 0, 0)));
    for (int deg = 0; deg <= 90; deg += 15) {
      Translation3d result =
          shoot.calculateImpartedVelocity(Rotation2d.fromDegrees(deg), MetersPerSecond.of(10.0));
      System.out.printf(
          "  Pivot %3d°: (X=%.4f, Y=%.4f, Z=%.4f) |v|=%.4f%n",
          deg, result.getX(), result.getY(), result.getZ(), result.getNorm());
    }

    System.out.println("=== Moving robot (vx=2, vy=1, omega=0.5), 0° pivot ===");
    shoot.setFutureTrackResult(new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds(2.0, 1.0, 0.5)));
    Translation3d result =
        shoot.calculateImpartedVelocity(Rotation2d.kZero, MetersPerSecond.of(10.0));
    System.out.printf(
        "  Result: (X=%.4f, Y=%.4f, Z=%.4f) |v|=%.4f%n",
        result.getX(), result.getY(), result.getZ(), result.getNorm());
  }
}
