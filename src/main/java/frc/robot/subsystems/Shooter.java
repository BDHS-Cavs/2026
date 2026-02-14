// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class shooter extends SubsystemBase {

    SparkMax m_shooterMotor = new SparkMax(4, MotorType.kBrushed); //upper shooter motor
    SparkMax m_intakeMotor = new SparkMax(3, MotorType.kBrushed); // intake motor

    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    SparkMaxConfig shooterConfig = new SparkMaxConfig();
  /** Creates a new ExampleSubsystem. */
  public shooter() {
    intakeConfig.inverted(true);
    m_intakeMotor.configure(intakeConfig, com.revrobotics.ResetMode.kNoResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
    
    shooterConfig.inverted(true);
    m_shooterMotor.configure(shooterConfig, com.revrobotics.ResetMode.kNoResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
  }

  public void ShooterFirer(){
        m_intakeMotor.set(-1);
        m_shooterMotor.set(1); //motor shooter spin
    }

  public void FireShooterOnly() {
    m_shooterMotor.set(1);
  }

  public void ShooterReverse(){
        m_intakeMotor.set(0.5);
        m_shooterMotor.set(-0.5); //motor shooter spin reversed
    }
  
   public void intakein(){
      m_intakeMotor.set(0.6); //motor intake spin
      m_shooterMotor.set(0.6);
  }
  
  public void intakeout(){
        m_intakeMotor.set(-0.6); //motor intake spin reversed
        m_shooterMotor.set(-0.6);
    }

  public void intakeAndShooterStop() {
    m_intakeMotor.set(0);
    m_shooterMotor.set(0);
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
