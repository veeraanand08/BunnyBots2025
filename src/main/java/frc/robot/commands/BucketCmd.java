package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ModuleConstants;
import frc.robot.subsystems.BucketSubsystem;

public class BucketCmd extends Command {

    private final BucketSubsystem bucketSubsystem;
    private final Supplier<Boolean> leftBumper, rightBumper;

    public BucketCmd(BucketSubsystem bucketSubsystem,
            Supplier<Boolean> leftBumper, Supplier<Boolean> rightBumper) {
        this.bucketSubsystem = bucketSubsystem;
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        addRequirements(bucketSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (leftBumper.get()) {
            bucketSubsystem.setMotorAngle(0);
        }
        else if (rightBumper.get()) {
            bucketSubsystem.setMotorAngle(ModuleConstants.kBucketEngagedAngle);
        }
        else {
            bucketSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        bucketSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
