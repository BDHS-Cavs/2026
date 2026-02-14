// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.intakeOut;
import frc.robot.commands.intakein;
import frc.robot.commands.ShooterFirer;
import frc.robot.commands.ShooterReverse;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.drive;
import frc.robot.subsystems.climber;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CenterAuto;
import frc.robot.commands.LeftAuto;
import frc.robot.commands.RightAuto;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final shooter m_shooter = new shooter();
  public static final drive m_drive = new drive();
  public static final climber m_climber = new climber();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController controller = new CommandXboxController(1); //binds controller
  final CommandXboxController driverController = new CommandXboxController(0); //driving controller

  public LeftAuto leftAuto =  new LeftAuto();
  public CenterAuto centerAuto =  new CenterAuto();
  public RightAuto rightAuto =  new RightAuto();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    controller.a().whileTrue(new intakein());                        //            A   =   intake in
    controller.b().whileTrue(new intakeOut());                         //            B   =   intake out
    controller.x().whileTrue(new ShooterFirer());                     //            X   =   shooter shoot
    controller.y().whileTrue(new ShooterReverse());                   //            Y   =   shooter reverse






    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
            // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
  //  // An example command will be run in autonomous
  //  return m_chooser.getSelected();
  //}
}
