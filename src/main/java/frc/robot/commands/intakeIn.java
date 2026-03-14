package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class intakeIn extends Command{
    public intakeIn(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void execute() {
        RobotContainer.m_shooter.intakeIn(); // Intake In
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.intakeAndShooterStop(); // Stop Intaking
    }

}