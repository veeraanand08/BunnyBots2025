// package frc.robot.subsystems;

// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkBase.PersistMode;
// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// //import edu.wpi.first.wpilibj.AnalogInput;
// //import edu.wpi.first.wpilibj.RobotController;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.SwerveModulePosition;
// import edu.wpi.first.math.kinematics.SwerveModuleState;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// //import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Constants.DriveConstants;
// import frc.robot.Constants.ModuleConstants;


// public class BucketSubsystem extends SubsystemBase {
//     private final SparkMax bucketMotor = new SparkMax(7, MotorType.kBrushless); //constants __.bucketMotorPort

//     //private final SparkMaxConfig bucketMotorConfig = new SparkMaxConfig();

//     public void setMotorSpeed(double velocity) {
//         bucketMotor.set(velocity);
//     }

//     public void stopMotor() {
//         bucketMotor.set(0);
//     }
// }