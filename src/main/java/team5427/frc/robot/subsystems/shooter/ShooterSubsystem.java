package team5427.frc.robot.subsystems.shooter;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  public static ShooterSubsystem m_instance;

  public static ShooterSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new ShooterSubsystem();
    }
    return m_instance;
  }

  private ShooterSubsystem() {}

  @Override
  public void periodic() {}

  public void setShooterAngle(Rotation2d angle) {}

  public void setShooterSpeed(LinearVelocity velocity) {}
}
