package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class shooterFire extends Command{

    double timer;
    double startTime;
    boolean timerSet;

    public shooterFire(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        timer = System.currentTimeMillis() - startTime;
        //SmartDashboard.putNumber("Shooter StartTime", startTime);
        SmartDashboard.putNumber("Shooter Timer", timer);

        // If under 1 sec
        if(timer < 1000) {
            RobotContainer.m_shooter.fireShooterOnly(); // Spin up shooter
        }

        // If over 1 sec
        if(timer > 1000) {
            RobotContainer.m_shooter.shooterFire(); // Shoot
        }
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.intakeAndShooterStop(); // Stop shooting
    }

}