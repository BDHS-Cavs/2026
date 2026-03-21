// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.intakeOut;
import frc.robot.commands.intakeIn;
import frc.robot.commands.shooterFire;
import frc.robot.commands.shooterReverse;
import frc.robot.commands.move;

import frc.robot.subsystems.shooter;
import frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.CenterAuto;
import frc.robot.commands.LeftAuto;
import frc.robot.commands.RightAuto;
import frc.robot.commands.NoAuto;


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
  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final CommandXboxController controller = new CommandXboxController(1); //binds controller
  //public final CommandXboxController driverController = new CommandXboxController(0); //driving controller
  public final CommandPS5Controller driverController = new CommandPS5Controller(0); //driving controller

  public LeftAuto leftAuto =  new LeftAuto();
  public CenterAuto centerAuto =  new CenterAuto();
  public RightAuto rightAuto =  new RightAuto();
  public NoAuto noAuto = new NoAuto();



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();

    m_drive.setDefaultCommand(new move(m_drive, driverController)); // drive with controller input
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
    controller.a().whileTrue(new intakeIn());                        //            A   =   intake in
    controller.b().whileTrue(new intakeOut());                       //            B   =   intake out
    controller.x().whileTrue(new shooterFire());                     //            X   =   shooter shoot
    controller.y().whileTrue(new shooterReverse());                  //            Y   =   shooter reverse





    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
            // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

  }

/*

Match timings:
2:45 - 2:25 Auto (both active) "AUTO"
2:25 - 2:20 Wait 5 seconds "DELAY"
2:20 - 2:10 Both active "TRANSITION"
2:10 - 1:45 Auto loser active "SHIFT_1"
1:45 - 1:20 Trade active "SHIFT_2"
1:20 - 0:55 Trade active "SHIFT_3"
0:55 - 0:30 Trade active "SHIFT_4"
0:30 - 0:00 Both active "ENDGAME"

*/


  private Boolean practiceWonAuto = null;

  public String getAllianceShift() {

    if (DriverStation.isDisabled()) {
      practiceWonAuto = null;
      return "DISABLED";
    }

    double time = DriverStation.getMatchTime();

    if (time > 145) return "AUTO";
    if (time > 140) return "DELAY";
    if (time > 130) return "TRANSITION";
    if (time > 105) return "SHIFT_1";
    if (time > 80) return "SHIFT_2";
    if (time > 55) return "SHIFT_3";
    if (time > 30) return "SHIFT_4";

    return "ENDGAME";
  }

  public String getHubStatus(String shift) {
    if(shift.equals("AUTO") || shift.equals("DELAY") || shift.equals("TRANSITION") || shift.equals("ENDGAME")) {
      return "ACTIVE";
    }

    var maybeAlliance = DriverStation.getAlliance();

    if(maybeAlliance.isEmpty()) {
      return "ACTIVE"; // only if alliance is unknown, default to active
    }

    var alliance = maybeAlliance.get();

    boolean wonAuto;

    if(!DriverStation.isFMSAttached()) {
      if(shift.equals("SHIFT_1") && practiceWonAuto == null) {
        practiceWonAuto = Math.random() < 0.5;
      }

      wonAuto = practiceWonAuto != null && practiceWonAuto;
    }

    else {
      String msg = DriverStation.getGameSpecificMessage();
      
      if(msg.isEmpty()) {
        return "ACTIVE"; // only if FMS has not sent auto winner yet, default to active
      }

      DriverStation.Alliance autoWinner;

      switch(msg.charAt(0)) {
        case 'R' -> autoWinner = DriverStation.Alliance.Red;
        case 'B' -> autoWinner = DriverStation.Alliance.Blue;
        default -> {return "UNKNOWN";}
      }

      wonAuto = alliance == autoWinner;
    }

    if(shift.startsWith("SHIFT_")) {
      int shiftNum = Integer.parseInt(shift.substring(6));

      if(shiftNum % 2 == 1) {
        return wonAuto ? "INACTIVE" : "ACTIVE";
      }
      else {
        return wonAuto ? "ACTIVE" : "INACTIVE";
      }
    }

    return "UNKNOWN";
  }

  public double secondsUntilNextShift() {
    double time = DriverStation.getMatchTime();
    double result;

    if (time > 145) result = time - 145;
    else if (time > 140) result = time - 140;
    else if (time > 130) result = time - 130;
    else if (time > 105) result = time - 105;
    else if (time > 80) result = time - 80;
    else if (time > 55) result = time - 55;
    else if (time > 30) result = time - 30;
    else result = time;

    return Math.max(0, result);
  }

  public String getMatchPeriod() {
    String shift = getAllianceShift();
    String status = getHubStatus(shift);
    double timeLeft = secondsUntilNextShift();

    if(status.equals("ACTIVE")) {
        return "SCORE (" + String.format("%.1f", timeLeft) + "s)";
    }
    
    else if(status.equals("INACTIVE")) {
        return "REFUEL (" + String.format("%.1f", timeLeft) + "s)";
    }
    
    else {
        return "UNKNOWN (" + String.format("%.1f", timeLeft) + "s)";
    }
  }

  public boolean canScore() {
    String shift = getAllianceShift();
    String status = getHubStatus(shift);
    return status.equals("ACTIVE");
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

/*

      we do autonomous stuff in Robot.java autonomousInit()

 */

  //public Command getAutonomousCommand() {
  //  // An example command will be run in autonomous
  //  return m_chooser.getSelected();
  //}
}
