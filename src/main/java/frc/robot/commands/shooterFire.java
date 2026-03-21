package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;

public class shooterFire extends Command{

    private final shooter m_shooter;

    double timer;
    double startTime;
    boolean timerSet;

    public shooterFire(shooter m_shooter){
        this.m_shooter = m_shooter;

        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        timer = System.currentTimeMillis() - startTime;
        //SmartDashboard.putNumber("Shooter StartTime", startTime);
        SmartDashboard.putNumber("Shooter Timer", timer);

        // If under 1 sec
        if(timer < 1000) {
            m_shooter.fireShooterOnly(); // Spin up shooter
        }

        // If over 1 sec
        if(timer > 1000) {
            m_shooter.shooterFire(); // Shoot
        }
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