package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class intakein extends Command{
    public intakein(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void execute() {
        RobotContainer.m_shooter.intakein();
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.intakeAndShooterStop();
    }

}