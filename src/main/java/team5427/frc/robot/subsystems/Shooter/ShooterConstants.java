package team5427.frc.robot.subsystems.Shooter;

import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;

import org.apache.commons.math3.ml.distance.CanberraDistance;

import edu.wpi.first.math.geometry.Rotation2d;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorUtil;
import team5427.lib.motors.SteelTalonFX;
import team5427.lib.motors.MotorConfiguration.IdleState;
import team5427.lib.motors.MotorConfiguration.MotorMode;

public class ShooterConstants {
    public static CANDeviceId shooterMotorCanDeviceId = new CANDeviceId(13);
    public static CANDeviceId rotationalMotorCanDeviceId = new CANDeviceId(12);
    public static CANDeviceId pivotMotorCanDeviceId = new CANDeviceId(11);
    public static final ComplexGearRatio kPivotGearRatio = new ComplexGearRatio();
    public static final ComplexGearRatio kRotationalGearRatio = new ComplexGearRatio();
    public static final ComplexGearRatio kShooterGearRatio = new ComplexGearRatio();
    public static MotorConfiguration kShooterMotorConfiguration = new MotorConfiguration();
    public static MotorConfiguration kRotationalMotorConfiguration = new MotorConfiguration();
    public static MotorConfiguration kPivotMotorConfiguration = new MotorConfiguration();
    public static Rotation2d kRotationMotorStartingPosition = Rotation2d.kZero;
    public static Rotation2d kPivotStartingPosition = Rotation2d.kZero;



    static{
        kShooterMotorConfiguration.gearRatio = kShooterGearRatio;
        kShooterMotorConfiguration.maxVelocity = kShooterMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
        kShooterMotorConfiguration.maxAcceleration = kShooterMotorConfiguration.maxVelocity*100;
        kShooterMotorConfiguration.gearRatio = new ComplexGearRatio(5, 20);
        kShooterMotorConfiguration.isArm = false;
        kShooterMotorConfiguration.idleState = IdleState.kBrake;
        kShooterMotorConfiguration.mode = MotorMode.kFlywheel;
        kShooterMotorConfiguration.withFOC = true;
        kShooterMotorConfiguration.currentLimit = 46;
        kShooterMotorConfiguration.kV = 0.7;
        kShooterMotorConfiguration.kA = 0.1;
        kShooterMotorConfiguration.kP = 0.5;
        kShooterMotorConfiguration.kFF = 1;






    }
    static{
        kRotationalMotorConfiguration.gearRatio = kRotationalGearRatio;
        
        kRotationalMotorConfiguration.maxVelocity = kShooterMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
        kRotationalMotorConfiguration.maxAcceleration = kShooterMotorConfiguration.maxVelocity*15;
        kRotationalMotorConfiguration.isArm = false;
        kRotationalMotorConfiguration.idleState = IdleState.kBrake;
        kRotationalMotorConfiguration.mode = MotorMode.kServo;
        kRotationalMotorConfiguration.withFOC = true;
        kRotationalMotorConfiguration.currentLimit = 25;
        kRotationalMotorConfiguration.kV = 0.4;
        kRotationalMotorConfiguration.kA = 0.1;
        kRotationalMotorConfiguration.kP = 0.5;
        kRotationalMotorConfiguration.kFF = 0.6;
    }
    static{
        kPivotMotorConfiguration.gearRatio = kPivotGearRatio;

        kPivotMotorConfiguration.maxVelocity = kShooterMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
        kPivotMotorConfiguration.maxAcceleration = kShooterMotorConfiguration.maxVelocity*7;
        kShooterMotorConfiguration.gearRatio = new ComplexGearRatio(3, 10);
        kPivotMotorConfiguration.isArm = false;
        kPivotMotorConfiguration.idleState = IdleState.kBrake;
        kPivotMotorConfiguration.mode = MotorMode.kServo;
        kPivotMotorConfiguration.withFOC = true;
        kPivotMotorConfiguration.currentLimit = 25;
        kPivotMotorConfiguration.kV = 0.2;
        kPivotMotorConfiguration.kA = 0.1;
        kPivotMotorConfiguration.kP = 0.5;
        kPivotMotorConfiguration.kFF = 0.5;
    }

    


}
