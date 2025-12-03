package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ModuleConstants;
import frc.robot.LimelightHelpers;

public class LimeLight extends Command {


    private final Supplier<Boolean> YKey;

    public LimeLight(Supplier<Boolean> YKey){
        this.YKey = YKey;
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        if (YKey.get()) {
            System.out.println("IM GOING CRAZY: " + LimelightHelpers.getTargetPose3d_RobotSpace("limelight-left"));
        }
        
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
