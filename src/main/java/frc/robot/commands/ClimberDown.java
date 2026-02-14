package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ClimberDown extends Command{
    public ClimberDown(){
        addRequirements(RobotContainer.m_climber);
    }

    public void execute() {
        RobotContainer.m_climber.climberDown();
    }

    public void end(boolean interrupted) {
        RobotContainer.m_climber.climberStop();
    }

}