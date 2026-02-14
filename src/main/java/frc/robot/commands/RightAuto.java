// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class RightAuto extends Command{

  double startTime;
  boolean timerSet;

    public RightAuto(){
        addRequirements(RobotContainer.m_shooter);
        addRequirements(RobotContainer.m_climber);
        addRequirements(RobotContainer.m_drive);
    }

    public void initialize() {
        startTime = 0;
        timerSet = false;
        //RobotContainer.m_drive.gyroZero();
    }

    public void execute() {
        SmartDashboard.putNumber("StartTime", startTime);
        double timer = System.currentTimeMillis() - startTime;
        SmartDashboard.putNumber("Auto Timer", timer);
        if(timerSet != true) {
          startTime = System.currentTimeMillis();
          timerSet = true;
        }
        
        //Move forwards for 0.5 seconds
        if(timer > 0 && timer < 500) { //if under 0.5 sec
            RobotContainer.m_drive.move(0, 0.5); // move forwards to get away from the thing
        }

        //Shoot for 4.5 seconds
        if(timer > 500 && timer < 5000) {// if over 0.5 sec and under 5 sec
            RobotContainer.m_shooter.ShooterFirer(); // shoot
        }
        
        //Stop shooting and move forwards for 2 seconds
        if(timer > 5000 && timer < 7000) { // if over 5 sec and under 7 sec
            RobotContainer.m_shooter.intakeAndShooterStop(); // stop shooting
            RobotContainer.m_drive.move(0, 1); // move forwards
        }

        //Stop moving for 0.05 seconds
        if(timer > 7000 && timer < 7050) { // if over 7 sec and under 7.05 sec
            RobotContainer.m_drive.move(0, 0); // stop moving
        }
        
        //SPIN 180 DEGREES FOR MAX 2 SECONDS
        //if(timer > 7000 && timer < 9000) { // if over 7 sec and under 9 sec
        //    if(RobotContainer.m_drive.getGyro() < 180)
        //    {
        //        RobotContainer.m_drive.move(1, 0);
        //    }
        //}

        //STOP SPINNING FOR 1 SECOND
        //if(timer > 9000 && timer < 10000) {
        //    RobotContainer.m_drive.move(0, 0);
        //}
    }

    public void end(boolean interrupted) {
        //RobotContainer.m_shooter.intakeAndShooterStop();
    }

}
