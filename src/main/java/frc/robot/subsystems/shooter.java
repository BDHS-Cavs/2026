// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

public class shooter extends SubsystemBase {

  SparkMax m_shooterMotor = new SparkMax(ShooterConstants.shooterMotorID, Constants.motorType); // shooter motor
  SparkMax m_intakeMotor = new SparkMax(ShooterConstants.intakeMotorID, Constants.motorType); // intake motor

  SparkMaxConfig intakeConfig = new SparkMaxConfig();
  SparkMaxConfig shooterConfig = new SparkMaxConfig();

  public shooter() {
    intakeConfig.inverted(true);
    m_intakeMotor.configure(intakeConfig, Constants.resetMode, Constants.persistMode);
    
    shooterConfig.inverted(true);
    m_shooterMotor.configure(shooterConfig, Constants.resetMode, Constants.persistMode);
  }

  public void shooterFire(){
    m_intakeMotor.set(-1); // Run Intake (Reversed) (-1 speed)
    m_shooterMotor.set(1); // Run Shooter (1 speed)
  }

  public void fireShooterOnly() {
    m_shooterMotor.set(1); // Run Shooter (1 speed)
  }

  public void shooterReverse(){
    m_intakeMotor.set(1); // Run Intake (1 speed)
    m_shooterMotor.set(-1); // Run Shooter (Reversed) (-1 speed)
  }
  
  public void intakeIn(){
    m_intakeMotor.set(1); // Run Intake (1 speed)
    m_shooterMotor.set(0.5); // Run Shooter (0.5 speed)
  }
  
  public void intakeOut(){
    m_intakeMotor.set(-1); // Run Intake (Reversed) (-1 speed)
    m_shooterMotor.set(-0.6); // Run Shooter (Reversed) (-0.6 speed)
  }

  public void intakeAndShooterStop() {
    m_intakeMotor.set(0); // Stop Intake
    m_shooterMotor.set(0); // Stop Shooter
  }

  public void intakeStop() {
    m_intakeMotor.set(0); // Stop Intake
  }

  public void shooterStop() {
    m_shooterMotor.set(0); // Stop Shooter
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
