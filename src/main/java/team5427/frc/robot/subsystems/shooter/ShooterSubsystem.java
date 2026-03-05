package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.shooter.io.ShooterIO;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOInputsAutoLogged;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOTalonFX;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem m_instance;
  private ShooterIO io;
  private ShooterIOInputsAutoLogged inputsAutoLogged;

  @Getter private LinearVelocity rightShooterVelocity = MetersPerSecond.of(0);
  @Getter private LinearVelocity leftShooterVelocity = MetersPerSecond.of(0);

  @Getter private Rotation2d rightShooterAngle;
  @Getter private Rotation2d leftShooterAngle;

  public static ShooterSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new ShooterSubsystem();
    }
    return m_instance;
  }

  private ShooterSubsystem() {
    inputsAutoLogged = new ShooterIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new ShooterIOTalonFX();
      case SIM:
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
    Logger.recordOutput(
        "Shooter/RightShooterSpeedSetpoint", rightShooterVelocity.in(MetersPerSecond));
    Logger.recordOutput(
        "Shooter/LeftShooterSpeedSetpoint", leftShooterVelocity.in(MetersPerSecond));

    Logger.recordOutput("Shooter/RightHoodAngleSetpoint", rightShooterAngle);
    Logger.recordOutput("Shooter/LeftHoodAngleSetpoint", leftShooterAngle);

    Logger.recordOutput("Shooter/RightShooterSpeed", getRightShooterVelocity());
    Logger.recordOutput("Shooter/LeftShooterSpeed", getLeftShooterVelocity());

    Logger.recordOutput("Shooter/RightHoodAngle", getRightShooterAngle());
    Logger.recordOutput("Shooter/LeftHoodAngle", getLeftShooterAngle());
  }
}
