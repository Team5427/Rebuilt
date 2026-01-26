package team5427.frc.robot.subsystems.leds;

import com.ctre.phoenix6.configs.CANdleConfiguration;
import com.ctre.phoenix6.signals.StripTypeValue;

public class LedsConstants {
  public static final int kLightsChannel = 1;
  public static final int kLightsLength = 60;

  // public static final double kShotRed = -0.85;
  // public static final double kShotBlue = -0.83;
  // public static final double kShotWhite = -0.81;
  // public static final double kLarsonScannerRed = -0.35;
  // public static final double kLarsonScannerGray = -0.33;
  // public static final double kLightChaseRed = -0.31;
  // public static final double kLightChaseBlue = -0.29;
  // public static final double kLightChaseGray = -0.27;
  // public static final double kHeartbeatRed = -0.25;
  // public static final double kHeartbeatBlue = -0.23;
  // public static final double kHeartbeatWhite = -0.21;
  // public static final double kHeartbeatGray = -0.19;
  // public static final double kBreathRed = -0.17;
  // public static final double kBreathBlue = -0.15;
  // public static final double kBreathGray = -0.13;
  // public static final double kStrobeRed = -0.11;
  // public static final double kStrobeBlue = -0.09;
  // public static final double kStrobeGold = -0.07;
  // public static final double kStrobeWhite = -0.05;
  // public static final double kCp1EndToEndBlendToBlack = -0.03;
  // public static final double kCp1LarsonScanner = -0.01;
  // public static final double kCp1LightChase = 0.01;
  // public static final double kCp1HeartbeatSlow = 0.03;
  // public static final double kCp1HeartbeatMedium = 0.05;
  // public static final double kCp1HeartbeatFast = 0.07;
  // public static final double kCp1BreathSlow = 0.09;
  // public static final double kCp1BreathFast = 0.11;
  // public static final double kCp1Shot = 0.13;
  // public static final double kCp1Strobe = 0.15;
  // public static final double kCp2EndToEndBlendToBlack = 0.17;
  // public static final double kCp2LarsonScanner = 0.19;
  // public static final double kCp2LightChase = 0.21;
  // public static final double kCp2HeartbeatSlow = 0.23;
  // public static final double kCp2HeartbeatMedium = 0.25;
  // public static final double kCp2HeartbeatFast = 0.27;
  // public static final double kCp2BreathSlow = 0.29;
  // public static final double kCp2BreathFast = 0.31;
  // public static final double kCp2Shot = 0.33;
  // public static final double kCp2Strobe = 0.35;
  // public static final double kCp1_2Sparkle1On2 = 0.37;
  // public static final double kCp1_2Sparkle2On1 = 0.39;
  // public static final double kCp1_2ColorGradient = 0.41;
  // public static final double kCp1_2BeatsPerMinute = 0.43;
  // public static final double kCp1_2EndToEndBlend1To2 = 0.45;
  // public static final double kCp1_2EndToEndBlend = 0.47;
  // public static final double kCp1_2NoBlending = 0.49;
  // public static final double kCp1_2Twinkles = 0.51;
  // public static final double kCp1_2ColorWaves = 0.53;
  // public static final double kCp1_2Sinelon = 0.55;
  // public static final double kHotPink = 0.57;
  // public static final double kDarkRed = 0.59;
  // public static final double kRed = 0.61;
  // public static final double kRedOrange = 0.63;
  // public static final double kOrange = 0.65;
  // public static final double kGold = 0.67;
  // public static final double kYellow = 0.69;
  // public static final double kLawnGreen = 0.71;
  // public static final double kLime = 0.73;
  // public static final double kDarkGreen = 0.75;
  // public static final double kGreen = 0.77;
  // public static final double kBlueGreen = 0.79;
  // public static final double kAqua = 0.81;
  // public static final double kSkyBlue = 0.83;
  // public static final double kDarkBlue = 0.85;
  // public static final double kBlue = 0.87;
  // public static final double kBlueViolet = 0.89;
  // public static final double kViolet = 0.91;
  // public static final double kWhite = 0.93;
  // public static final double kGray = 0.95;
  // public static final double kDarkGray = 0.97;
  // public static final double kBlack = 0.99;

  // public static double kShot;
  // public static double kSolid;
  // public static double kBreath;
  // public static double kStrobe;
  // public static double kLightChase;
  // public static double kLarsonScanner;

  public static final CANdleConfiguration candleConfig = new CANdleConfiguration();

  static {
    candleConfig.LED.StripType = StripTypeValue.RGBW;

    // if (DriverStation.getAlliance().get() == Alliance.Red) {
    //   kShot = kShotRed;
    //   kSolid = kRed;
    //   kBreath = kBreathRed;
    //   kStrobe = kStrobeRed;
    //   kLightChase = kLightChaseRed;
    //   kLarsonScanner = kLarsonScannerRed;
    // } else {
    //   kShot = kShotBlue;
    //   kSolid = kBlue;
    //   kBreath = kBreathBlue;
    //   kStrobe = kStrobeBlue;
    //   kLightChase = kLightChaseBlue;
    //   kLarsonScanner = kLarsonScannerGray;
    // }
  }
}
