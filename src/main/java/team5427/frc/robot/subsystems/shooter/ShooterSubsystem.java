package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.shooter.io.ShooterIO;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOInputsAutoLogged;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOSim;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOTalonFX;
import team5427.lib.kinematics.shooter.FuelPhysicsSim;
import team5427.lib.kinematics.shooter.ShotCalculator;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem m_instance;
  private ShooterIO io;
  private ShooterIOInputsAutoLogged inputsAutoLogged;
  private ShotCalculator shotCalculator = new ShotCalculator();

  @Getter private LinearVelocity rightShooterVelocity = MetersPerSecond.of(0);
  @Getter private LinearVelocity leftShooterVelocity = MetersPerSecond.of(0);

  @Getter private Rotation2d rightShooterAngle = Rotation2d.kZero;
  @Getter private Rotation2d leftShooterAngle = Rotation2d.kZero;

  public static ShooterSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new ShooterSubsystem();
    }
    return m_instance;
  }

  private ShooterSubsystem() {
    ShotCalculator.Config config = new ShotCalculator.Config();
    config.launcherOffsetX = 0.0; // NEEDS TO TUNE
    config.launcherOffsetY = 0.0; // NEEDS TO TUNE
    shotCalculator = new ShotCalculator(config);
    inputsAutoLogged = new ShooterIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new ShooterIOTalonFX();
        break;
      case SIM:
        io = new ShooterIOSim();
        // m_instance.fireSimBall(Robot.ballSim);
        break;
      case REPLAY:
        break;
    }
    rightShooterAngle = ShooterConstants.kHoodHardstopPosition;
    leftShooterAngle = ShooterConstants.kHoodHardstopPosition;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);

    io.setRightFlywheelSpeed(rightShooterVelocity);
    io.setLeftFlywheelSpeed(leftShooterVelocity);

    io.setRightHoodAngle(rightShooterAngle);
    io.setLeftHoodAngle(leftShooterAngle);

    Logger.processInputs("Shooter/Inputs", inputsAutoLogged);
    log();
  }

  public void setRightShooterAngle(Rotation2d angle) {
    rightShooterAngle = angle;
  }

  public void setLeftShooterAngle(Rotation2d angle) {
    leftShooterAngle = angle;
  }

  public void setRightShooterSpeed(LinearVelocity velocity) {
    rightShooterVelocity = velocity;
  }

  public void setLeftShooterSpeed(LinearVelocity velocity) {
    leftShooterVelocity = velocity;
  }

  public LinearVelocity getCurrentLeftShooterSpeed() {
    return inputsAutoLogged.leftFlywheelMotorLinearVelocity;
  }

  public LinearVelocity getCurrentRightShooterSpeed() {
    return inputsAutoLogged.rightFlywheelMotorLinearVelocity;
  }

  public Rotation2d getCurrentLeftShooterAngle() {
    return Rotation2d.fromRadians(inputsAutoLogged.leftHoodMotorPositionRadians);
  }

  public Rotation2d getCurrentRightShooterAngle() {
    return Rotation2d.fromRadians(inputsAutoLogged.rightHoodMotorPositionRadians);
  }

  public void log() {
    Logger.recordOutput("Shooter/RightShooterSpeedSetpoint", rightShooterVelocity);
    Logger.recordOutput("Shooter/LeftShooterSpeedSetpoint", leftShooterVelocity);

    Logger.recordOutput("Shooter/RightHoodAngleSetpoint", rightShooterAngle);
    Logger.recordOutput("Shooter/LeftHoodAngleSetpoint", leftShooterAngle);

    Logger.recordOutput("Shooter/RightShooterSpeed", getRightShooterVelocity());
    Logger.recordOutput("Shooter/LeftShooterSpeed", getLeftShooterVelocity());

    Logger.recordOutput("Shooter/RightHoodAngle", getRightShooterAngle());
    Logger.recordOutput("Shooter/LeftHoodAngle", getLeftShooterAngle());
  }

  public void fireSimBall(FuelPhysicsSim sim) {

    Translation3d launcherPosition =
        new Translation3d(
            0.5, // forward from robot center
            0.0, // sideways
            0.43 // shooter height
            );

    Translation3d launchVelocity =
        new Translation3d(
            8.0, // forward velocity
            0.0, 3.0);

    double spinRPM = 3000;

    sim.launchBall(launcherPosition, launchVelocity, spinRPM);
  }
}
