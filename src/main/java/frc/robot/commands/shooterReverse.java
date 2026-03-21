package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;

public class shooterReverse extends Command{

    private final shooter m_shooter;

    public shooterReverse(shooter m_shooter){
        this.m_shooter = m_shooter;

        addRequirements(m_shooter);
    }

    @Override
    public void execute() {
        m_shooter.shooterReverse(); // Shoot Reversed
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.intakeAndShooterStop(); // Stop shooting
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}