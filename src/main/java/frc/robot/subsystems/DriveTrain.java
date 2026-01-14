package frc.robot.subsystems;


import com.studica.frc.AHRS;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SwerveConstants;



public class DriveTrain extends SubsystemBase {
    public static final double L = SwerveConstants.kTrackWidth;
    public static final double W = SwerveConstants.kWheelBase;

    SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
    new Translation2d(W/2, L/2),  // Front Left
    new Translation2d(W/2, -L/2), // Front Right
    new Translation2d(-W/2, L/2), // Back Left
    new Translation2d(-W/2, -L/2)
    );

    private SwerveModule backRightModule;
    private SwerveModule backLeftModule;
    private SwerveModule frontRightModule;
    private SwerveModule frontLeftModule;
    
    private ChassisSpeeds chassisSpeed;

    private com.studica.frc.AHRS ahrs;

    public DriveTrain(SwerveModule frontLeftModule,  SwerveModule frontRightModule, SwerveModule backLeftModule, SwerveModule backRightModule){
        this.frontLeftModule = frontLeftModule;
        this.frontRightModule = frontRightModule;
        this.backLeftModule = backLeftModule;
        this.backRightModule = backRightModule;
        
        this.ahrs = new AHRS(AHRS.NavXComType.kUSB1, AHRS.NavXUpdateRate.k200Hz);

        new Thread(
            () -> {
              try {
                Thread.sleep(1000);
                ahrs.reset();

              } catch (Exception e) {
              }
            })
        .start();
    }


    public void drive(double x1, double y1, double x2, boolean fieldOriented){
        double xSpeed = x1 * DriveConstants.kTeleDriveMaxSpeedMS;
        double ySpeed = y1 * DriveConstants.kTeleDriveMaxSpeedMS;
        double turnSpeed = x2 * DriveConstants.kTeleTurnMaxSpeedRadS;
        if (fieldOriented){
            chassisSpeed = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, turnSpeed, ahrs.getRotation2d()); //gyro stuff
        } else {
            chassisSpeed = new ChassisSpeeds(xSpeed, ySpeed, turnSpeed);
        }
        

        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(chassisSpeed);

        SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates, DriveConstants.kTeleDriveMaxSpeedMS);

        setModuleStates(moduleStates);


    }

    public void setModuleStates(SwerveModuleState[] states){
        frontLeftModule.drive(states[0]);
        frontRightModule.drive(states[1]);
        backLeftModule.drive(states[2]);
        backRightModule.drive(states[3]);

    }



    public void stopModules(){
        backRightModule.stop();
        backLeftModule.stop();
        frontRightModule.stop();
        frontLeftModule.stop();
    }
    
}




