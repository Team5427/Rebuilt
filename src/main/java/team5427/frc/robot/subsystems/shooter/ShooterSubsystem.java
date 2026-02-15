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
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.LinearVelocity;
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

  public static ShooterSubsystem m_instance;

  public static ShooterSubsystem getInstance(){
    if (m_instance == null){
      m_instance = new ShooterSubsystem();
    }
    return m_instance;
  }

  private ShooterSubsystem() {}

  @Override
  public void periodic() {}

  public void setShooterAngle(Rotation2d angle){

  }

  public void setShooterSpeed(LinearVelocity velocity){}
}
