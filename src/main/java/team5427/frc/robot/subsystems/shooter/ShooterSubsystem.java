package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import java.util.Optional;
import java.util.function.Supplier;

import org.ironmaple.simulation.drivesims.SwerveDriveSimulation;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team5427.frc.robot.Constants;
import team5427.frc.robot.Constants.Mode;
import team5427.frc.robot.subsystems.intake.IntakeConstants;
import team5427.frc.robot.subsystems.intake.io.IntakeIOInputsAutoLogged;
import team5427.frc.robot.subsystems.intake.io.IntakeIOSim;
import team5427.frc.robot.subsystems.shooter.io.ShooterIO;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOInputsAutoLogged;
import team5427.frc.robot.subsystems.shooter.io.ShooterIOTalonFX;

public class ShooterSubsystem extends SubsystemBase {
  private LinearVelocity shootingSpeed;
  private Rotation2d shootingAngle;
  private ShooterIO io;
  private ShooterIOInputsAutoLogged inputsAutoLogged;

  public final Alert kShootingSpeedOutOfBounds = new Alert("OutOfBounds", "Shooting Speed Requested Out of Bounds", AlertType.kWarning);
  public final Alert kShootingRotationOutOfBounds = new Alert("OutOfBounds", "Shooting Speed Requested Out of Bounds", AlertType.kWarning);
  
  public static ShooterSubsystem m_instance;


  public static ShooterSubsystem getInstance(){
    if(m_instance==null){
      m_instance = new ShooterSubsystem(Optional.empty());

    }
    return m_instance;

  }

  private ShooterSubsystem(Optional<Supplier<SwerveDriveSimulation>> swerveDriveSimulationSupplier) {
    inputsAutoLogged = new ShooterIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new ShooterIOTalonFX();
        break;
      // case SIM:
      // if(swerveDriveSimulationSupplier.isEmpty()){
      //   DriverStation.reportWarning("Shooter Subsystem Simulation did not recieve a Swerve Drive Simulation Supplier", true);
      // }
      // io = new ShooterIOSim

      default:
        break;
    }
    shootingSpeed = MetersPerSecond.of(0.0);
    shootingAngle = Rotation2d.kZero;


  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);

    if (Math.abs(shootingSpeed.in(MetersPerSecond)) > 10.0) {
      kShootingSpeedOutOfBounds.set(true);
    } else {
      kShootingSpeedOutOfBounds.set(false);
      io.setRightFlywheelSpeed(shootingSpeed);
      io.setLeftFlywheelSpeed(shootingSpeed);
    }
    if (shootingAngle.getDegrees() > ShooterConstants.kPivotMaximumRotation.getDegrees()
        || shootingAngle.getDegrees() < ShooterConstants.kPivotMinimumRotation.getDegrees()) {
      kShootingRotationOutOfBounds.set(true);
    } else {
      kShootingRotationOutOfBounds.set(false);
      io.setLeftHoodAngle(shootingAngle.getRadians());
      io.setRightHoodAngle(shootingAngle.getRadians());
    }
    Logger.processInputs("Intake/Inputs", inputsAutoLogged);
    log();
  }
  public void simulateIntaking(boolean isIntaking) {
    if (Constants.currentMode.equals(Mode.SIM)) {
      IntakeIOSim ioSim = (IntakeIOSim) io;
      ioSim.setRunning(isIntaking);
    }
  }

  public void setShootingSpeed(LinearVelocity speed) {
    shootingSpeed = speed;
  }

  public void setShootingAngle(Rotation2d angle) {
    shootingAngle = angle;
  }

  // public void disableRollerMotor(boolean shouldDisable) {
  //   io.disableRollerMotor(shouldDisable);
  // }

  // public void disablePivotMotor(boolean shouldDisable) {
  //   io.disablePivotMotor(shouldDisable);
  // }

  // public boolean isRollerMotorDisabled() {
  //   return inputsAutoLogged.rollerMotorDisabled;
  // }

  // public boolean isPivotMotorDisabled() {
  //   return inputsAutoLogged.pivotMotorDisabled;
  // }

  public void log() {
    Logger.recordOutput("Shooter/ShootingSpeed", shootingSpeed.in(MetersPerSecond));
    Logger.recordOutput("Shooter/ShootingAngle", shootingAngle.getDegrees());
  }
}


