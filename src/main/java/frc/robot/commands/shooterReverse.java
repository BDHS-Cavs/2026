package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class shooterReverse extends Command{
    public shooterReverse(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void execute() {
        RobotContainer.m_shooter.shooterReverse(); // Shoot Reversed
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.intakeAndShooterStop(); // Stop shooting
    }

}