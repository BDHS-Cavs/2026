// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class drive extends SubsystemBase {

  SparkMax m_backLeftMotor = new SparkMax(DriveConstants.backLeftMotorID, Constants.motorType); // Back Left (5)
  SparkMax m_backRightMotor = new SparkMax(DriveConstants.backRightMotorID, Constants.motorType); // Back Right (1)
  SparkMax m_frontLeftMotor = new SparkMax(DriveConstants.frontLeftMotorID, Constants.motorType); // Front Left (6)
  SparkMax m_frontRightMotor = new SparkMax(DriveConstants.frontRightMotorID, Constants.motorType); // Front Right (2)

  AHRS m_gyro = new AHRS(NavXComType.kMXP_SPI); // NavX2 MXP Gyro (MXP SPI port)
  
  // Configs for setting follow and inverted
  SparkMaxConfig backLeftConfig = new SparkMaxConfig();
  SparkMaxConfig backRightConfig = new SparkMaxConfig();
  SparkMaxConfig frontLeftConfig = new SparkMaxConfig();
  SparkMaxConfig frontRightConfig = new SparkMaxConfig();

  DifferentialDrive drivetrain = new DifferentialDrive(m_backLeftMotor, m_backRightMotor);

  public drive() {

    backLeftConfig.inverted(true);
    m_backLeftMotor.configure(backLeftConfig, Constants.resetMode, Constants.persistMode);

    backRightConfig.inverted(true);
    m_backRightMotor.configure(backRightConfig, Constants.resetMode, Constants.persistMode);

    frontLeftConfig.follow(m_backLeftMotor, false);
    m_frontLeftMotor.configure(frontLeftConfig, Constants.resetMode, Constants.persistMode);

    frontRightConfig.follow(m_backRightMotor, false);
    m_frontRightMotor.configure(frontRightConfig, Constants.resetMode, Constants.persistMode);
  }

  public void move(double x, double y) {
    double xspeed = x*0.75; // Limit turning speed to 75%
    drivetrain.arcadeDrive(xspeed, y); // Drive
  }

  public void driveStop() {
    drivetrain.arcadeDrive(0, 0); // Stop Drive
  }

  public void gyroZero() {
    m_gyro.reset(); // Zero Gyro
  }

  public double getGyro() {
    return m_gyro.getAngle(); // Get Gyro Angle
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
