package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ShooterFirer extends Command{
    public ShooterFirer(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void execute() {
        RobotContainer.m_shooter.ShooterFirer();
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.ShooterStop();
    }

}