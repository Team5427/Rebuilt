package team5427.frc.robot.commands.shooting;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.math.geometry.Twist3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.FutureTrack;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;
import team5427.frc.robot.subsystems.shooter.AimingConstants;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;
import team5427.frc.robot.subsystems.vision.VisionSubsystem;
import team5427.lib.detection.tuples.Tuple2Plus;

public class MoveWhileShoot extends Command{
    private ShooterSubsystem shooter;
    private SwerveSubsystem swerve;
    private VisionSubsystem vision;
    private FutureTrack futureTrack;

    private Translation3d target = Translation3d.kZero;

    private Tuple2Plus<Pose2d, ChassisSpeeds> futureTrackResult;

    public MoveWhileShoot(String s){
        shooter = ShooterSubsystem.getInstance();
        swerve = SwerveSubsystem.getInstance();
        vision = VisionSubsystem.getInstance();
        futureTrack = FutureTrack.getInstance();
        futureTrackResult = futureTrack.getFutureTrackPose();

        addRequirements(shooter, swerve);
    }

    public MoveWhileShoot(){

    }

    @Override
    public void initialize(){
        futureTrackResult = futureTrack.getFutureTrackPose();
        target = DriverStation.getAlliance().isPresent() && DriverStation.getAlliance().get() == Alliance.Red ? FieldConstants.Hub.oppTopCenterPoint : FieldConstants.Hub.topCenterPoint;
    }

    @Override
    public void execute(){
        double distance = target.minus(ShooterConstants.kRobotToShooterTransform.getTranslation().plus(new Translation3d(futureTrackResult.r.getTranslation()) )).getNorm();
        Rotation2d shooterAngle = Rotation2d.fromDegrees(AimingConstants.kShootingTable.getPivotAngle(distance));
        LinearVelocity shooterVelocity = MetersPerSecond.of(AimingConstants.kShootingTable.getFlyWheelSpeed(distance));
        double timeOfFlight = AimingConstants.kShootingTable.getTimeOfFlight(distance);
    }

    public void setFutureTrackResult(Tuple2Plus<Pose2d, ChassisSpeeds> result){
this.futureTrackResult = result;
    }

    public Translation3d calculateImpartedVelocity(Rotation2d kShootingAngle, LinearVelocity shootingVelocity){
        Pose3d ballPose = new Pose3d(ShooterConstants.kRobotToShooterTransform.getTranslation().plus(new Translation3d(futureTrackResult.r.getTranslation()) ), new Rotation3d(futureTrackResult.r.getRotation()).plus(ShooterConstants.kRobotToShooterTransform.getRotation()));
        Translation3d impartedVelocity = new Translation3d(shootingVelocity.in(MetersPerSecond), ballPose.getRotation().plus(new Rotation3d(0, kShootingAngle.getRadians(), 0)));
        return impartedVelocity.plus(new Translation3d(futureTrackResult.t.omegaRadiansPerSecond * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm() + futureTrackResult.t.vxMetersPerSecond, futureTrackResult.t.omegaRadiansPerSecond * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm() + futureTrackResult.t.vyMetersPerSecond, 0));

    }


}
