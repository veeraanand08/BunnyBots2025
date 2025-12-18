package frc.robot;



public final class Constants {

    public static final class SwerveConstants {
        public static final int kFrontLeftDriveMotorId = 0;
        public static final boolean kFrontLeftDriveMotorReversed = false;

        public static final int kFrontLeftTurnMotorId = 1;
        public static final boolean kFrontLeftTurnMotorReversed = false;
        public static final boolean kFrontLeftTurnEncoderReversed = false;

        public static final int kFrontRightDriveMotorId = 2;
        public static final boolean kFrontRightDriveMotorReversed = false;

        public static final int kFrontRightTurnMotorId = 3;
        public static final boolean kFrontRightTurnMotorReversed = false;
        public static final boolean kFrontRightTurnEncoderReversed = false;

        public static final int kBackLeftDriveMotorId = 4;
        public static final boolean kBackLeftDriveMotorReversed = false;

        public static final int kBackLeftTurnMotorId = 5;
        public static final boolean kBackLeftTurnMotorReversed = false;
        public static final boolean kBackLeftTurnEncoderReversed = false;

        public static final int kBackRightDriveMotorId = 6;
        public static final boolean kBackRightDriveMotorReversed = false;

        public static final int kBackRightTurnMotorId = 7;
        public static final boolean kBackRightTurnMotorReversed = false;
        public static final boolean kBackRightTurnEncoderReversed = false;

        public static final double kTrackWidth = 20.0;
        public static final double kWheelBase = 20.0;

        public static final double kTurnGearRatio = 12.8; //idk this ask nick


    }

    public static final class IOConstants {
        public static final double kDeadband = 0.1;
        public static final int kDriverControllerPort = 0;
    }

    public static final class DriveConstants { //basically change this entire thing to be safe  
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 0.1; 
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 0.1;
    }
}
