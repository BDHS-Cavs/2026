package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystems.drive;

public class move extends Command {
    private final drive m_drive;
    private final CommandPS5Controller driverController;

    public move(drive m_drive, CommandPS5Controller driverController) {
        this.m_drive = m_drive;
        this.driverController = driverController;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_drive);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_drive.move(driverController.getLeftX(), driverController.getLeftY());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drive.move(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}