package team5427.frc.robot.subsystems.shooter.io;

import edu.wpi.first.math.geometry.Pose2d;

public class ShooterIOSim implements ShooterIO {
  private Pose2d ballLandSimHubHeight; //
  private double airresForce; // Air resistance = 1/2 (pv^2)Cd*A (run periodically)
  private double magnusForce; // calculate magnus forces

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'updateInputs'");
  }
}
