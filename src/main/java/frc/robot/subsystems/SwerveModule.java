
package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import frc.robot.Constants.SwerveConstants;
import edu.wpi.first.math.MathUtil;

public class SwerveModule {

    private final SparkMax driveMotor;
    private final SparkMax turnMotor;

    private final SparkMaxConfig driveConfig;
    private final SparkMaxConfig turnConfig;

    private final RelativeEncoder turnEncoder;

    private PIDController pidController;

    private DoubleLogEntry errorLog;

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed, String name) {
        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turnMotor = new SparkMax(turningMotorId, MotorType.kBrushless);

        turnConfig = new SparkMaxConfig();
        driveConfig = new SparkMaxConfig();

        turnConfig.inverted(turningMotorReversed);
        driveConfig.inverted(driveMotorReversed);

        turnConfig.idleMode(IdleMode.kBrake);
        driveConfig.idleMode(IdleMode.kBrake);

        turnConfig.encoder.positionConversionFactor(2.0 * Math.PI / SwerveConstants.kTurnGearRatio);

        driveMotor.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        turnMotor.configure(turnConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        turnEncoder = turnMotor.getEncoder();

        pidController = new PIDController(0.25, 0, 0);
        pidController.enableContinuousInput(-Math.PI, Math.PI);


        DataLogManager.start();

        DataLog log = DataLogManager.getLog();

        errorLog = new DoubleLogEntry(log, "/SwerveModual" + name + "/error");
        
    }

    public void setDriveMotor(double speed){
        driveMotor.set(speed);
    }

    public void setTurnMotor(double speed){
        turnMotor.set(speed);
    }

    public double getTurningAngle(){
        return MathUtil.angleModulus(turnEncoder.getPosition());
    }

    public void logError(){
        errorLog.append(pidController.getError());
    }

    public void stop(){
        setDriveMotor(0);
        setTurnMotor(0);
    }



    public void drive(double speed, double angle){
        driveMotor.set(speed);
        turnMotor.set(pidController.calculate(getTurningAngle(),angle));
    }
    
}
