// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

public class drive extends SubsystemBase {

    SparkMax m_leftBackMotor = new SparkMax(5, MotorType.kBrushed); //upper intake motor
    SparkMax m_rightBackMotor = new SparkMax(1, MotorType.kBrushed); //lower intake motor
    SparkMax m_leftFrontMotor = new SparkMax(6, MotorType.kBrushed); //upper intake motor
    SparkMax m_rightFrontMotor = new SparkMax(2, MotorType.kBrushed); //lower intake motor

    AHRS m_gyro = new AHRS(NavXComType.kMXP_SPI);
    
    SparkMaxConfig leftFrontConfig = new SparkMaxConfig();
    SparkMaxConfig rightFrontConfig = new SparkMaxConfig();

    DifferentialDrive drivetrain = new DifferentialDrive(m_leftBackMotor, m_rightBackMotor);
  /** Creates a new ExampleSubsystem. */
  public drive() {
    leftFrontConfig.follow(m_leftBackMotor);
    m_leftFrontMotor.configure(leftFrontConfig, com.revrobotics.ResetMode.kNoResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

    rightFrontConfig.follow(m_rightBackMotor);
    m_rightFrontMotor.configure(rightFrontConfig, com.revrobotics.ResetMode.kNoResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);
  }
public void move(double x, double y) {
  drivetrain.arcadeDrive(x, y);
}

public void driveStop() {
  drivetrain.arcadeDrive(0, 0);
}

public void gyroZero() {
  m_gyro.reset();
}

public double getGyro() {
  return m_gyro.getAngle();
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
