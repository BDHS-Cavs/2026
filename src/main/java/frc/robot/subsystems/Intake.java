// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Intake extends SubsystemBase {

    SparkMax m_intakeLowerMotor = new SparkMax(1, MotorType.kBrushless); //upper intake motor
    SparkMax m_intakeUpperMotor = new SparkMax(2, MotorType.kBrushless); //lower intake motor
  /** Creates a new ExampleSubsystem. */
  public Intake() {}

  public void intakein(){
        m_intakeUpperMotor.set(1.0); //motor intakeUpper spin
        m_intakeLowerMotor.set(1.0); //motor intakeLower spin
    }
  
  public void intakeout(){
        m_intakeUpperMotor.set(-1.0); //motor intakeUpper spin reversed
        m_intakeLowerMotor.set(-1.0); //motor intakeLower spin reversed
    }

  public void intakestop(){
      m_intakeUpperMotor.set(0); //motor intakeUpper spin stopper
      m_intakeLowerMotor.set(0); //motor intakeLower spin stopper
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
