package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearAcceleration;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import team5427.lib.motors.SteelTalonFX;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;

public class ShooterIOTalonFX implements ShooterIO {

  private SteelTalonFX rollerMotor1;
  private SteelTalonFX rollerMotor2;

  private StatusSignal<Angle> rollerMotor1Angle;
  private StatusSignal<AngularVelocity> rollerMotor1AngularVelocity;
  private StatusSignal<AngularAcceleration> rollerMotor1AngularAcceleration;
  private LinearVelocity rollerMotor1LinearVelocity;
  private LinearAcceleration rollerMotor1LinearAcceleration;
  private StatusSignal<Temperature> rollerMotor1Temperature;
  private StatusSignal<Current> rollerMotor1Current;
  private StatusSignal<Voltage> rollerMotor1Voltage;

  private boolean rollerMotor1IsConnected = false;

  private StatusSignal<Angle> rollerMotor2Angle;
  private StatusSignal<AngularVelocity> rollerMotor2AngularVelocity;
  private StatusSignal<AngularAcceleration> rollerMotor2AngularAcceleration;
  private LinearVelocity rollerMotor2LinearVelocity;
  private LinearAcceleration rollerMotor2LinearAcceleration;
  private StatusSignal<Temperature> rollerMotor2Temperature;
  private StatusSignal<Current> rollerMotor2Current;
  private StatusSignal<Voltage> rollerMotor2Voltage;

  private boolean rollerMotor2IsConnected = false;

  public ShooterIOTalonFX() {
    rollerMotor1 = new SteelTalonFX(null);
    rollerMotor2 = new SteelTalonFX(null);

    rollerMotor1Angle = rollerMotor1.getTalonFX().getPosition();
    rollerMotor1AngularVelocity = rollerMotor1.getTalonFX().getVelocity();
    rollerMotor1AngularAcceleration = rollerMotor1.getTalonFX().getAcceleration();

    Distance circumference = Inches.of(ShooterConstants.flywheelRadius.magnitude() * 2 * Math.PI);

    rollerMotor1LinearVelocity =
        MetersPerSecond.of(
            circumference.magnitude() * rollerMotor1AngularVelocity.getValue().magnitude());

    rollerMotor1LinearAcceleration =
        MetersPerSecondPerSecond.of(
            circumference.magnitude() * rollerMotor1AngularAcceleration.getValue().magnitude());
    rollerMotor1Temperature = rollerMotor1.getTalonFX().getDeviceTemp();
    rollerMotor1Current = rollerMotor1.getTalonFX().getStatorCurrent();
    rollerMotor1Voltage = rollerMotor1.getTalonFX().getMotorVoltage();
    rollerMotor1IsConnected = rollerMotor1.getTalonFX().isConnected();

    rollerMotor2Angle = rollerMotor2.getTalonFX().getPosition();
    rollerMotor2AngularVelocity = rollerMotor2.getTalonFX().getVelocity();
    rollerMotor2AngularAcceleration = rollerMotor2.getTalonFX().getAcceleration();

    rollerMotor2LinearVelocity =
        MetersPerSecond.of(
            circumference.magnitude() * rollerMotor2AngularVelocity.getValue().magnitude());
    rollerMotor2LinearAcceleration =
        MetersPerSecondPerSecond.of(
            circumference.magnitude() * rollerMotor2AngularAcceleration.getValue().magnitude());
    rollerMotor2Temperature = rollerMotor2.getTalonFX().getDeviceTemp();
    rollerMotor2Current = rollerMotor2.getTalonFX().getStatorCurrent();
    rollerMotor2Voltage = rollerMotor2.getTalonFX().getMotorVoltage();
    rollerMotor2IsConnected = rollerMotor2.getTalonFX().isConnected();
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    inputs.rollerMotor1IsConnected = rollerMotor1.getTalonFX().isConnected();
    inputs.rollerMotor2IsConnected = rollerMotor2.getTalonFX().isConnected();

    BaseStatusSignal.refreshAll(
        rollerMotor1Angle,
        rollerMotor1AngularVelocity,
        rollerMotor1AngularAcceleration,
        rollerMotor1Temperature,
        rollerMotor1Voltage,
        rollerMotor1Current);

    BaseStatusSignal.refreshAll(
        rollerMotor2Angle,
        rollerMotor2AngularVelocity,
        rollerMotor2AngularAcceleration,
        rollerMotor2Temperature,
        rollerMotor2Voltage,
        rollerMotor2Current);

    inputs.rollerMotor1Angle =
        Rotation2d.fromRadians(rollerMotor1.getTalonFX().getPosition().getValue().magnitude());
    inputs.rollerMotor1AngularVelocity = rollerMotor1.getTalonFX().getVelocity().getValue();
    inputs.rollerMotor1AngularAcceleration = rollerMotor1.getTalonFX().getAcceleration().getValue();
    inputs.rollerMotor1LinearVelocity = rollerMotor1LinearVelocity;
    inputs.rollerMotor1LinearAcceleration = rollerMotor1LinearAcceleration;
    inputs.rollerMotor1Temperature = rollerMotor1.getTalonFX().getDeviceTemp().getValue();
    inputs.rollerMotor1Current = rollerMotor1.getTalonFX().getStatorCurrent().getValue();
    inputs.rollerMotor1Voltage = rollerMotor1.getTalonFX().getMotorVoltage().getValue();

    inputs.rollerMotor2Angle =
        Rotation2d.fromRadians(rollerMotor2.getTalonFX().getPosition().getValue().magnitude());
    inputs.rollerMotor2AngularVelocity = rollerMotor2.getTalonFX().getVelocity().getValue();
    inputs.rollerMotor2AngularAcceleration = rollerMotor2.getTalonFX().getAcceleration().getValue();
    inputs.rollerMotor2LinearVelocity = rollerMotor2LinearVelocity;
    inputs.rollerMotor2LinearAcceleration = rollerMotor2LinearAcceleration;
    inputs.rollerMotor2Temperature = rollerMotor2.getTalonFX().getDeviceTemp().getValue();
    inputs.rollerMotor2Current = rollerMotor2.getTalonFX().getStatorCurrent().getValue();
    inputs.rollerMotor2Voltage = rollerMotor2.getTalonFX().getMotorVoltage().getValue();

    inputs.rollerMotor2AngularVelocity = rollerMotor1AngularVelocity.getValue();
  }

  @Override
  public void setRoller1Rotation(Rotation2d rotation) {
    rollerMotor1.setSetpoint(rotation);
  }

  @Override
  public void setRoller1Speed(AngularVelocity velocity) {
    rollerMotor1.setSetpoint(velocity);
  }

  @Override
  public void setRoller1Speed(LinearVelocity velocity) {
    rollerMotor1.setSetpoint(velocity);
  }

  @Override
  public void resetRoller1(Rotation2d resetAngle) {
    rollerMotor1.setSetpoint(resetAngle);
  }

  @Override
  public void disableRoller1() {
    rollerMotor1.getTalonFX().disable();
  }

  @Override
  public void setRoller2Rotation(Rotation2d rotation) {
    rollerMotor2.setSetpoint(rotation);
  }

  @Override
  public void setRoller2Speed(AngularVelocity velocity) {
    rollerMotor2.setSetpoint(velocity);
  }

  @Override
  public void setRoller2Speed(LinearVelocity velocity) {
    rollerMotor2.setSetpoint(velocity);
  }

  @Override
  public void resetRoller2(Rotation2d resetAngle) {
    rollerMotor2.setSetpoint(resetAngle);
  }

  @Override
  public void disableRoller2() {
    rollerMotor2.getTalonFX().disable();
  }
}
