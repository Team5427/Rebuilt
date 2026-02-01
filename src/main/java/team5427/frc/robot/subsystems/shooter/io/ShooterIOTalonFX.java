package team5427.frc.robot.subsystems.shooter.io;

import team5427.lib.motors.SteelTalonFX;

public class ShooterIOTalonFX implements ShooterIO {

  // Shooter Left
  private SteelTalonFX leftHoodMotor;
  private SteelTalonFX leftFlywheelLeaderMotor;
  private SteelTalonFX leftFlywheelFollowerMotor;

  // Shooter Right
  private SteelTalonFX rightHoodMotor;
  private SteelTalonFX rightFlywheelLeaderMotor;
  private SteelTalonFX rightFlywheelFollowerMotor;

  public ShooterIOTalonFX() {}

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateInputs'");
  }
}
