// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drive;
import frc.robot.subsystems.shooter;

import frc.robot.RobotContainer;

public class Autos extends Command{

  double startTime = 0;
  boolean timerSet = false;

    public Autos(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void initialize() {}

    public void execute() {
        //RobotContainer.m_shooter.intakein();
        SmartDashboard.putNumber("StartTime", startTime);
        if(timerSet != true) {
          startTime = System.currentTimeMillis();
          timerSet = true;
        }
    }

    public void end(boolean interrupted) {
        //RobotContainer.m_shooter.intakeAndShooterStop();
    }

}
