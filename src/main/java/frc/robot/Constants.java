package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

    public static final class SwerveConstants {
        public static final int kFrontLeftDriveMotorId = 4;
        public static final boolean kFrontLeftDriveMotorReversed = false;

        public static final int kFrontLeftTurnMotorId = 5;
        public static final boolean kFrontLeftTurnMotorReversed = false;
        public static final boolean kFrontLeftTurnEncoderReversed = false;

        public static final int kFrontRightDriveMotorId = 7;
        public static final boolean kFrontRightDriveMotorReversed = false;

        public static final int kFrontRightTurnMotorId = 8;
        public static final boolean kFrontRightTurnMotorReversed = false;
        public static final boolean kFrontRightTurnEncoderReversed = false;

        public static final int kBackLeftDriveMotorId = 17;
        public static final boolean kBackLeftDriveMotorReversed = false;

        public static final int kBackLeftTurnMotorId = 18;
        public static final boolean kBackLeftTurnMotorReversed = false;
        public static final boolean kBackLeftTurnEncoderReversed = false;

        public static final int kBackRightDriveMotorId = 13;
        public static final boolean kBackRightDriveMotorReversed = false;

        public static final int kBackRightTurnMotorId = 14;
        public static final boolean kBackRightTurnMotorReversed = false;
        public static final boolean kBackRightTurnEncoderReversed = false;

        public static final double kTrackWidth = Units.inchesToMeters(22.75);
        public static final double kWheelBase = Units.inchesToMeters(22.75);

        public static final double kDriveBaseRadius =
        Math.hypot(kTrackWidth / 2.0, kWheelBase / 2.0);

        public static final double kTurnGearRatio = 150.0 / 7.0; //idk this ask nick    


    }

    public static final class IOConstants {
        public static final double kDeadband = 0.1;
        public static final int kDriverControllerPort = 0;
    }

    public static final class DriveConstants { //basically change this entire thing to be safe  
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3.0; 
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = kTeleDriveMaxAccelerationUnitsPerSecond / SwerveConstants.kDriveBaseRadius;
        public static final double kTeleDriveMaxSpeedMS = 4.5;
        public static final double kTeleTurnMaxSpeedRadS = Math.PI; 
    }
}
