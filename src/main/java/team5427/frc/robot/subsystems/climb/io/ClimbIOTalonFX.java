package team5427.frc.robot.subsystems.climb.io;

import team5427.frc.robot.subsystems.climb.ClimbConstants;
import team5427.lib.motors.SteelTalonFX;

public class ClimbIOTalonFX implements ClimbIO {
  private SteelTalonFX climbMotor = new SteelTalonFX(ClimbConstants.kClimbMotorDeviceId);
}
