// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.drive;

public class CenterAuto extends Command{

    private final shooter m_shooter;
    private final drive m_drive;

    double startTime;

    public CenterAuto(shooter m_shooter, drive m_drive){
        this.m_shooter = m_shooter;
        this.m_drive = m_drive;

        addRequirements(m_shooter);
        addRequirements(m_drive);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        double timer = System.currentTimeMillis() - startTime;
        //SmartDashboard.putNumber("StartTime", startTime);
        SmartDashboard.putNumber("Auto Timer", timer);
        
        // 0.5s to 1.5s
        if(timer > 500 && timer < 1500) {
            m_drive.move(0, 0.5); // Move forwards to get away from the wall // TODO switch to the command so it doesnt complain about motor safety not updated enough
        }

        // 1.5s to 2.5s
        if(timer > 1500 && timer < 2500) {
            m_drive.driveStop(); // Stop driving
            m_shooter.fireShooterOnly(); // Spin up shooter
        }
    
        // 2.5s to 19s
        if(timer > 2500 && timer < 19000) {
            m_shooter.shooterFire(); // Shoot
        }

        // 19s to 19.5s
        if(timer > 19000 && timer < 19500) {
            m_shooter.intakeAndShooterStop(); // Stop shooting
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.intakeAndShooterStop(); // Stop shooting
        m_drive.driveStop(); // Stop driving
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
