package team5427.frc.robot.subsystems.indexer.io;

import static edu.wpi.first.units.Units.MetersPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.SteelTalonFX;

public class IndexerIOTalonFX implements IndexerIO {
  private SteelTalonFX indexerMotorLeader;
  private SteelTalonFX indexerMotorFollower;

  private StatusSignal<AngularVelocity> indexerMotorAngularVelocity;

  private StatusSignal<Current> indexerMotorCurrent;

  private boolean indexerMotorLeaderConnected = false;
  private boolean indexerMotorFollowerConnected = false;

  public IndexerIOTalonFX() {
    indexerMotorLeader = new SteelTalonFX(IndexerConstants.kIndexerMasterMotorCanId);
    indexerMotorFollower = new SteelTalonFX(IndexerConstants.kIndexerSlaveMotorCanId);

    indexerMotorLeader
        .getTalonFX()
        .setControl(
            new Follower(
                indexerMotorFollower.getTalonFX().getDeviceID(), MotorAlignmentValue.Aligned));

    indexerMotorLeader.apply(IndexerConstants.kIndexerMotorConfiguration);
    indexerMotorFollower.apply(new MotorConfiguration(IndexerConstants.kIndexerMotorConfiguration));

    indexerMotorLeader.setEncoderPosition(IndexerConstants.kIndexerStartingRotation);
    indexerMotorFollower.setEncoderPosition(IndexerConstants.kIndexerStartingRotation);

    indexerMotorAngularVelocity = indexerMotorLeader.getTalonFX().getVelocity();

    indexerMotorCurrent = indexerMotorLeader.getTalonFX().getStatorCurrent();

    BaseStatusSignal.setUpdateFrequencyForAll(
        Constants.kMediumPriorityUpdateFrequency, indexerMotorCurrent, indexerMotorAngularVelocity);

    indexerMotorLeaderConnected = indexerMotorLeader.getTalonFX().isConnected();
    indexerMotorFollowerConnected = indexerMotorFollower.getTalonFX().isConnected();
  }

  public void updateInputs(IndexerIOInputsAutoLogged inputs) {
    BaseStatusSignal.refreshAll(indexerMotorCurrent, indexerMotorAngularVelocity);
    inputs.indexerMotorAngularVelocity = indexerMotorAngularVelocity.getValue();
    inputs.indexerMotorCurrent = indexerMotorCurrent.getValue();
    inputs.indexerFlywheelLinearVelocity =
        MetersPerSecond.of(indexerMotorAngularVelocity.getValueAsDouble() / 39.37);

    inputs.indexerMotorLeaderConnected = indexerMotorLeader.getTalonFX().isConnected();
    inputs.indexerMotorFollowerConnected = indexerMotorFollower.getTalonFX().isConnected();
  }

  public void setIndexerMotorVelocity(AngularVelocity velocity) {
    indexerMotorLeader.setSetpoint(velocity);
  }

  public void disableIndexerMotor(boolean shouldDisable) {
    if (shouldDisable) {
      indexerMotorLeader.getTalonFX().disable();
      indexerMotorFollower.getTalonFX().disable();
    }
  }
}
