package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class intakeOut extends Command{
    public intakeOut(){
        addRequirements(RobotContainer.m_intake);
    }

    public void execute() {
        RobotContainer.m_intake.intakeout();
    }

    public void end(boolean interrupted) {
        RobotContainer.m_intake.intakestop();
    }

}