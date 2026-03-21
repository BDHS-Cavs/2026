package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;

public class intakeIn extends Command{

    private final shooter m_shooter;

    public intakeIn(shooter m_shooter){
        this.m_shooter = m_shooter;

        addRequirements(m_shooter);
    }

    @Override
    public void execute() {
        m_shooter.intakeIn(); // Intake In
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.intakeAndShooterStop(); // Stop Intaking
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}