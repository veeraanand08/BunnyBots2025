
package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule {

    private final SparkMax driveMotor;
    private final SparkMax turningMotor;

    private final SparkMaxConfig driveConfig;
    private final SparkMaxConfig turnConfig;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final PIDController turningPidController;
    //private final PIDController drivePidController;

    private final SimpleMotorFeedforward driveFF =
            new SimpleMotorFeedforward(
                    ModuleConstants.kSDrive,
                    ModuleConstants.kVDrive,
                    ModuleConstants.kADrive
            );

    public SwerveModule(int driveMotorId, int turningMotorId, boolean driveMotorReversed, boolean turningMotorReversed) {

        driveMotor = new SparkMax(driveMotorId, MotorType.kBrushless);
        turningMotor = new SparkMax(turningMotorId, MotorType.kBrushless);

        driveConfig = new SparkMaxConfig();
        turnConfig = new SparkMaxConfig();

        driveConfig.inverted(driveMotorReversed);
        turnConfig.inverted(turningMotorReversed);

        driveConfig.encoder.positionConversionFactor(ModuleConstants.kDriveEncoderRot2Meter);
        driveConfig.encoder.velocityConversionFactor(ModuleConstants.kDriveEncoderRPM2MeterPerSec);
        turnConfig.encoder.positionConversionFactor(ModuleConstants.kTurningEncoderRot2Rad);
        turnConfig.encoder.velocityConversionFactor(ModuleConstants.kTurningEncoderRPM2RadPerSec);

        driveMotor.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        turningMotor.configure(turnConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        driveEncoder = driveMotor.getEncoder();
        turningEncoder = turningMotor.getEncoder();

        turningPidController = new PIDController(ModuleConstants.kPTurning, 0, 0);
        turningPidController.enableContinuousInput(-Math.PI, Math.PI);

        //drivePidController = new PIDController(ModuleConstants.kPDrive, 0, 0);
        //drivePidController.enableContinuousInput(-Math.PI, Math.PI);

        resetEncoders();
    }

    private double metersPerSecondToRPM(double mps) {
        return mps / ModuleConstants.kWheelCircumferenceMeters * 60.0;
    }

    public double getDrivePosition() {
        return driveEncoder.getPosition();
    }

    public double getTurningPosition() {
        return turningEncoder.getPosition();
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getTurningVelocity() {
        return turningEncoder.getVelocity();
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getDrivePosition(), new Rotation2d(getTurningPosition()));
    }

    public void resetEncoders() {
        driveEncoder.setPosition(0);
        turningEncoder.setPosition(0);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        state.optimize(new Rotation2d(getTurningPosition()));

        double desiredRPM = metersPerSecondToRPM(state.speedMetersPerSecond);
        double ffVolts = driveFF.calculate(state.speedMetersPerSecond);

        driveMotor.getClosedLoopController().setReference(
            desiredRPM,
            SparkBase.ControlType.kVelocity,
            ClosedLoopSlot.kSlot0, // default PID slot
            ffVolts,
            SparkClosedLoopController.ArbFFUnits.kVoltage
        );

        double turningOutputVolts = turningPidController.calculate(
            getTurningPosition(),
            state.angle.getRadians()
        );
        turningMotor.set(turningOutputVolts / 12.0);
    }

    public void stop() {
        driveMotor.set(0);
        turningMotor.set(0);
    }
}
