// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class NoAuto extends Command{

  double startTime;

    public NoAuto(){
        //addRequirements(RobotContainer.m_shooter);
        //addRequirements(RobotContainer.m_drive);
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        double timer = System.currentTimeMillis() - startTime;
        //SmartDashboard.putNumber("StartTime", startTime);
        SmartDashboard.putNumber("Auto Timer", timer);
            
        // 0.5s to 1.5s
        //if(timer > 500 && timer < 1500) {
        //    RobotContainer.m_shooter.fireShooterOnly(); // Spin up shooter
        //}
        
        // 1.5s to 19s
        //if(timer > 1500 && timer < 19000) {
        //    RobotContainer.m_shooter.shooterFire(); // Shoot
        //}
    
        // 19s to 19.5s
        //if(timer > 19000 && timer < 19500) {
        //    RobotContainer.m_shooter.intakeAndShooterStop(); // Stop shooting
        //}
    }

    public void end(boolean interrupted) {
        //RobotContainer.m_shooter.intakeAndShooterStop(); // Stop shooting
        //RobotContainer.m_drive.driveStop(); // Stop driving
    }

}
