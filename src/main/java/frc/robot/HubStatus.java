package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class HubStatus {

    private final SendableChooser<Boolean> chooser;
    
    public HubStatus(SendableChooser<Boolean> chooser) {
        this.chooser = chooser;
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

    private boolean printedSkipAuto = false;

    private boolean isRunAuto() { // Only returns false if in practice and chooser is "No Auto"
    if (!DriverStation.isFMSAttached()) {
        Boolean selected = chooser.getSelected();
        return selected == null ? true : selected;
    }

    return true; // If in real match, always return true
}

    private Boolean practiceWonAuto = null;

    public String getAllianceShift() {

        if (DriverStation.isDisabled()) { // Resets stuff
            practiceWonAuto = null;
            printedSkipAuto = false;
            return "DISABLED";
        }

        double time = DriverStation.getMatchTime();

        if (!isRunAuto()) { // If chooser was no auto, skip the auto phase by adding 20 sec to time
            time += 20;
            
            if (!printedSkipAuto) {
                System.out.println("Skipping autonomous for practice");
                printedSkipAuto = true;
            }
        }

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

        if (!isRunAuto()) { // if chooser was no auto, skip the auto phase by adding 20 sec to time
            time = 140;
        }

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
}