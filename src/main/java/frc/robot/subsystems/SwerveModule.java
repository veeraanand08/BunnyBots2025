
package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SwerveConstants;
import edu.wpi.first.math.MathUtil;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

public class SwerveModule {

    private final TalonFX driveMotor;

    private final SparkMax turnMotor;

    private final SparkMaxConfig turnConfig;

    private final RelativeEncoder turnEncoder;

    private DoubleLogEntry errorLog;
    private DoubleLogEntry setAngleLog;
    private DoubleLogEntry curAngleLog;

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed, String name) {
        driveMotor = new TalonFX(driveMotorId);


        TalonFXConfiguration driveConfig = new TalonFXConfiguration();

        driveConfig.MotorOutput.Inverted =
        driveMotorReversed
            ? InvertedValue.Clockwise_Positive
            : InvertedValue.CounterClockwise_Positive;

            driveMotor.getConfigurator().apply(driveConfig);
            driveMotor.getConfigurator().setPosition(0.0);
        
        
        turnMotor = new SparkMax(turningMotorId, MotorType.kBrushless);
        turnConfig = new SparkMaxConfig();
        turnConfig.inverted(turningMotorReversed);
        turnConfig.idleMode(IdleMode.kBrake);
        turnConfig.encoder.positionConversionFactor(2.0 * Math.PI / SwerveConstants.kTurnGearRatio);

        turnConfig.closedLoop.pid(0.25, 0, 0);
        turnConfig.closedLoop.positionWrappingEnabled(true);
        turnConfig.closedLoop.positionWrappingInputRange(-Math.PI, Math.PI);

        turnMotor.configure(turnConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        turnEncoder = turnMotor.getEncoder();

        DataLog log = DataLogManager.getLog();

        errorLog = new DoubleLogEntry(log, "/SwerveModual" + name + "/error");
        setAngleLog = new DoubleLogEntry(log, "/SwerveModual" + name + "/setAngle");
        curAngleLog = new DoubleLogEntry(log, "/SwerveModual" + name + "/curAngle");
        
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

    public Rotation2d getTurningRotation2d(){
        return new Rotation2d(getTurningAngle());
    }

    public void stop(){
        setDriveMotor(0);
        setTurnMotor(0);
    }



    public void drive(SwerveModuleState state){
        state.optimize(getTurningRotation2d());
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kTeleDriveMaxSpeedMS);
        turnMotor.getClosedLoopController().setReference(
            state.angle.getRadians(), 
            SparkMax.ControlType.kPosition
        );

        //logging
        errorLog.append(MathUtil.angleModulus(state.angle.getRadians()-getTurningAngle() ));
        setAngleLog.append(state.angle.getRadians());
        curAngleLog.append(getTurningAngle());

    }
    
}
