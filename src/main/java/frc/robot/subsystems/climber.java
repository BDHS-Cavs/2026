// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class climber extends SubsystemBase {

    SparkMax m_climberMotor1 = new SparkMax(9, MotorType.kBrushless); //upper shooter motor
    SparkMax m_climberMotor2 = new SparkMax(10, MotorType.kBrushless); // intake motor

    RelativeEncoder m_climberRelativeEncoder = m_climberMotor1.getEncoder();//elevator motor encoder

    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    SparkMaxConfig shooterConfig = new SparkMaxConfig();
  /** Creates a new ExampleSubsystem. */
  public climber() {
  }

  public void climberUp() {
    if(m_climberRelativeEncoder.getPosition() > -275) {
      m_climberMotor1.set(-1);
      m_climberMotor2.set(-1);
    }
    else {
      m_climberMotor1.set(0);
      m_climberMotor2.set(0);
    }
  }
  
  public void climberDown() {
    if(m_climberRelativeEncoder.getPosition() < 0) {
      m_climberMotor1.set(0.7);
      m_climberMotor2.set(0.7);
    }
    else {
      m_climberMotor1.set(0);
      m_climberMotor2.set(0);
    }
  }

  public void climberStop() {
    m_climberMotor1.set(0);
    m_climberMotor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Climber Encoder", m_climberRelativeEncoder.getPosition());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
