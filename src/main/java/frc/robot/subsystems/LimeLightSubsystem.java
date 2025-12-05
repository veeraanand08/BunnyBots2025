package frc.robot.subsystems;


import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.LimelightHelpers;

public class LimeLightSubsystem extends SubsystemBase {

    private final SlewRateLimiter turnLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);
    
    public LimeLightSubsystem() {
        
    }

    public double limelightAngle(){


        double kP = -0.035;

        double wantedAngle = 0;

        System.out.println("IM GOING CRAZY: " + LimelightHelpers.getTX("limelight-left"));
        
        double targetAngVelo = (wantedAngle- LimelightHelpers.getTX("limelight-left")) * kP;

        targetAngVelo = turnLimiter.calculate(targetAngVelo)*DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;

        return targetAngVelo;

    }

    public double limelightRange(){
        double kP = 0.1;

        double wantedRange = 10;

        System.out.println("IM GOING EVEN CRAZIER: " + LimelightHelpers.getTY("limelight-left"));

        double targetRangeVelo = LimelightHelpers.getTY("limelight-left") * kP;

        return 0.2;

        //return targetRangeVelo;

    }

    public void stop() {
        
    }
}