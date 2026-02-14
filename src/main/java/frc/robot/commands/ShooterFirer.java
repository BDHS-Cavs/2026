package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class ShooterFirer extends Command{

    double timer;
    double startTime;
    boolean timerSet;

    public ShooterFirer(){
        addRequirements(RobotContainer.m_shooter);
    }

    public void initialize() {
        timerSet = false;
        if(timerSet != true) { 
            startTime = System.currentTimeMillis();
            timerSet = true;
        }
    }

    public void execute() {
        timer = System.currentTimeMillis() - startTime;
        if(timer < 500) {
            RobotContainer.m_shooter.FireShooterOnly();
        }
        if(timer > 500) {
            RobotContainer.m_shooter.ShooterFirer();
        }
    }

    public void end(boolean interrupted) {
        RobotContainer.m_shooter.intakeAndShooterStop();
    }

}