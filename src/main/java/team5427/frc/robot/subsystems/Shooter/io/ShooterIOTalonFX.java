package team5427.frc.robot.subsystems.Shooter.io;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;
import static edu.wpi.first.units.Units.Rotation;

import java.io.ObjectInputFilter.Status;
import java.lang.invoke.VolatileCallSite;
import java.time.temporal.Temporal;
import java.util.Currency;

import org.apache.commons.math3.analysis.function.Constant;
import org.checkerframework.checker.units.qual.K;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;
import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.Shooter.ShooterConstants;
import team5427.lib.motors.SteelTalonFX;

public class ShooterIOTalonFX implements ShooterIO{
    private SteelTalonFX rotationalMotor;
    private SteelTalonFX pivotMotor;
    private SteelTalonFX shooterMotor;
    private StatusSignal<Angle> kRotationalMotorPosition;
    private StatusSignal<Angle> kPivotMotorPosition;
    private StatusSignal<Voltage> kRotationalVoltage;
    private StatusSignal<Voltage> kPivotMotorVoltage;
    private StatusSignal<Voltage> kShooterMotorVoltage;
    private StatusSignal<AngularVelocity> kRotationalMotorVelocity;
    private StatusSignal<AngularVelocity> kPivotMotorVelocity;
    private StatusSignal<AngularVelocity> kShooterMotorVelocity;
    private StatusSignal<AngularAcceleration> kRotationalMotorAcceleration;
    private StatusSignal<AngularAcceleration> kPivotMotorAceeleration;
    private StatusSignal<AngularAcceleration> kShooterMotorAcceleration;
    private StatusSignal<Current> kShooterMotorCurrent;
    private StatusSignal<Current> kPivotMotorCurrent;
    private StatusSignal<Current> kRotationalMotorCurrent;
    private StatusSignal<Temperature> kShooterMotorTemp;
    private StatusSignal<Temperature> kPivotMotorTemp;
    private StatusSignal<Temperature> kRotationalMotorTemp;
    public static boolean isIntakedProjectile = true;
    public ShooterIOTalonFX(){
        rotationalMotor = new SteelTalonFX(ShooterConstants.rotationalMotorCanDeviceId);
        pivotMotor = new SteelTalonFX(ShooterConstants.pivotMotorCanDeviceId);
        shooterMotor = new SteelTalonFX(ShooterConstants.shooterMotorCanDeviceId);
        rotationalMotor.apply(ShooterConstants.kRotationalMotorConfiguration);
        shooterMotor.apply(ShooterConstants.kShooterMotorConfiguration);
        pivotMotor.apply(ShooterConstants.kPivotMotorConfiguration);
        rotationalMotor.setEncoderPosition(ShooterConstants.kRotationMotorStartingPosition);
        pivotMotor.setEncoderPosition(ShooterConstants.kPivotStartingPosition);
        
        kRotationalMotorPosition = rotationalMotor.getTalonFX().getPosition();
        kPivotMotorPosition = pivotMotor.getTalonFX().getPosition();
        kPivotMotorCurrent = pivotMotor.getTalonFX().getStatorCurrent();
        kPivotMotorTemp = pivotMotor.getTalonFX().getDeviceTemp();
        kPivotMotorVoltage = pivotMotor.getTalonFX().getMotorVoltage();
        kPivotMotorVelocity = pivotMotor.getTalonFX().getVelocity();
        kPivotMotorAceeleration = pivotMotor.getTalonFX().getAcceleration();
        
        kShooterMotorTemp = shooterMotor.getTalonFX().getDeviceTemp();
        kShooterMotorAcceleration = shooterMotor.getTalonFX().getAcceleration();
        kShooterMotorVelocity = shooterMotor.getTalonFX().getVelocity();
        kShooterMotorCurrent = shooterMotor.getTalonFX().getStatorCurrent();
        kShooterMotorVoltage = shooterMotor.getTalonFX().getMotorVoltage();

        kRotationalMotorAcceleration = rotationalMotor.getTalonFX().getAcceleration();
        kRotationalMotorTemp = rotationalMotor.getTalonFX().getDeviceTemp();
        kRotationalMotorCurrent = rotationalMotor.getTalonFX().getStatorCurrent();
        kRotationalMotorVelocity = rotationalMotor.getTalonFX().getVelocity();
        kRotationalVoltage = rotationalMotor.getTalonFX().getMotorVoltage();

        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kHighPriorityUpdateFrequency, kPivotMotorPosition, kRotationalMotorPosition, kShooterMotorVelocity);
        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kMediumPriorityUpdateFrequency, kPivotMotorCurrent, kRotationalMotorCurrent, kShooterMotorCurrent, kPivotMotorAceeleration, kRotationalMotorAcceleration, kShooterMotorAcceleration, kRotationalMotorVelocity, kPivotMotorVelocity);
        BaseStatusSignal.setUpdateFrequencyForAll(Constants.kLowPriorityUpdateFrequency, kRotationalMotorTemp, kPivotMotorTemp, kShooterMotorTemp, kRotationalMotorVelocity, kPivotMotorVelocity, kShooterMotorVelocity);
    

        

        
    }
    public void updateInputs(ShooterIOInputs inputs ){
        inputs.rotationalMotorConnected = rotationalMotor.getTalonFX().isConnected();
        inputs.pivotMotorConnected = pivotMotor.getTalonFX().isConnected();
        inputs.shooterMotorConnected = pivotMotor.getTalonFX().isConnected();

        BaseStatusSignal.refreshAll(kPivotMotorPosition, kShooterMotorVelocity, kRotationalMotorPosition);
        BaseStatusSignal.refreshAll(kRotationalMotorCurrent, kPivotMotorCurrent, kShooterMotorCurrent, kShooterMotorAcceleration, kRotationalMotorAcceleration, kPivotMotorAceeleration, kRotationalMotorVelocity, kPivotMotorVelocity);
        BaseStatusSignal.refreshAll(kPivotMotorVoltage, kRotationalVoltage, kShooterMotorVoltage, kPivotMotorTemp, kRotationalMotorTemp, kShooterMotorTemp);

        inputs.kPivotMotorPosition = Rotation2d.fromRotations(kPivotMotorPosition.getValue().in(Rotation));
        inputs.kPivotMotorAcceleration = kPivotMotorAceeleration.getValue();
        inputs.kPivotMotorCurrent = kPivotMotorCurrent.getValue();
        inputs.kPivotMotorVelocity = kPivotMotorVelocity.getValue();
        inputs.kPivotMotorVoltage = kPivotMotorVoltage.getValue();
        inputs.pivotMotorTemperature = kPivotMotorTemp.getValue();
        

        inputs.kRotationalMotorPosition = Rotation2d.fromRotations(kRotationalMotorPosition.getValue().in(Rotation));
        inputs.kRotationalMotorAcceleration = kRotationalMotorAcceleration.getValue();
        inputs.kRotationalMotorCurrent = kRotationalMotorCurrent.getValue();
        inputs.kRotationalMotorVelocity = kRotationalMotorVelocity.getValue();
        inputs.kRotationalMotorVoltage = kRotationalVoltage.getValue();
        inputs.rotationalMotorTemperature = kRotationalMotorTemp.getValue();

        inputs.kShooterMotorAngularAcceleration = kShooterMotorAcceleration.getValue();
        inputs.kShooterMotorCurrent = kShooterMotorCurrent.getValue();
        inputs.kShooterMotorAngularVelocity = kShooterMotorVelocity.getValue();
        inputs.kShooterMotorVoltage = kShooterMotorVoltage.getValue();
        inputs.shooterMotorTemperature = kShooterMotorTemp.getValue();
        inputs.kShooterMotorVelocity = MetersPerSecond.of(shooterMotor.getEncoderVelocity(kShooterMotorVelocity));
        inputs.kShooterMotorAcceleration = MetersPerSecondPerSecond.of(shooterMotor.getEncoderAcceleration(kShooterMotorAcceleration));







    }
    
    public boolean getIntakedProjectile(){
        return true;
    }
    public void setRotationalMotorRotation(Rotation2d rotation){
        rotationalMotor.setSetpoint(rotation);
        
    }
    public void setPivotMotorRotation(Rotation2d rotation){
        pivotMotor.setSetpoint(rotation);
    }
    public void setShooterMotorVelocity(LinearVelocity velocity){
        shooterMotor.setSetpoint(velocity);
    }
    
    
}
