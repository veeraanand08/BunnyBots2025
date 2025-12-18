package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;



public class DriveTrain extends SubsystemBase {
    public static final double L = SwerveConstants.kTrackWidth;
    public static final double W = SwerveConstants.kWheelBase;

    private SwerveModule backRightModule;
    private SwerveModule backLeftModule;
    private SwerveModule frontRightModule;
    private SwerveModule frontLeftModule;

    public DriveTrain(SwerveModule backRightModule, SwerveModule backLeftModule, SwerveModule frontRightModule, SwerveModule frontLeftModule){
        this.backRightModule = backRightModule;
        this.backLeftModule = backLeftModule;
        this.frontRightModule = frontRightModule;
        this.frontLeftModule = frontLeftModule;
    }

    public void drive(double x1, double y1, double x2){
        double r = Math.sqrt((L*L) + (W*W));
        y1 *= -1;
        double a = x1-x2 * (L/r);
        double b = x1+x2 * (L/r);
        double c = y1 - x2 * (W/r);
        double d = y1+x2 * (W/r);

        //Speed fed to each wheel 
        double backRightSpeed = Math.sqrt((a*a) + (d*d));
        double backLeftSpeed = Math.sqrt((a*a) + (c*c));
        double frontRightSpeed = Math.sqrt((b*b) + (d*d));
        double frontLeftSpeed = Math.sqrt((b*b) + (c*c));

        //Angle fed to each wheel in radians -pi to pi
        double backRightAngle = Math.atan2(a, d); 
        double backLeftAngle = Math.atan2(a, c);
        double frontRightAngle = Math.atan2(b, d);
        double frontLeftAngle = Math.atan2(b, c);

        backRightModule.drive(backRightSpeed, backRightAngle);
        backLeftModule.drive(backLeftSpeed, backLeftAngle);
        frontRightModule.drive(frontRightSpeed, frontRightAngle);
        frontLeftModule.drive(frontLeftSpeed, frontLeftAngle);
    }


    public void stopModules(){
        backRightModule.stop();
        backLeftModule.stop();
        frontRightModule.stop();
        frontLeftModule.stop();
    }

    public void logError(){
        backRightModule.logError();
        backLeftModule.logError();
        frontRightModule.logError();
        frontLeftModule.logError();
    }
    
}




