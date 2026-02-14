package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class ClimberUp extends Command{
    public ClimberUp(){
        addRequirements(RobotContainer.m_climber);
    }

    public void execute() {
        RobotContainer.m_climber.climberUp();
    }

    public void end(boolean interrupted) {
        RobotContainer.m_climber.climberStop();
    }

}