package team5427.frc.robot.subsystems.turret;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

public class TurretConstants {
  public static final SparkMax kTurretSparkMax = new SparkMax(0, null); // To-do
  public static final RelativeEncoder kTurretEncoder = kTurretSparkMax.getEncoder();
}
