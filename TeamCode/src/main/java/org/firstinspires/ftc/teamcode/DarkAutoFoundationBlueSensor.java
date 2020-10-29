package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="✡ Dark Auto Foundation Blue Distance Sensor", group="working")
public class DarkAutoFoundationBlueSensor extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        timer.restart();
        oldDrive.goBackwards(0.8);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 50); //TODO: make a method to do this part automatically.
        oldDrive.stopAll();
        oldDrive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 10);
        oldDrive.stopAll();
        foundServo.setPosition(1);
        sleep(1000);

        //oldDrive.driveDistance(DeadWheels.grabberSide, -60.5f, 0.8, 2);

        foundServo.setPosition(1);
        sleep(1000);

        oldDrive.strafeLeft(0.75);
        timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        sleep(325);
        //goes for a little bit of extra time
        oldDrive.goForwards(1);
        sleep(500);
        oldDrive.stopAll();


        //sleep(300);

        oldDrive.turn(90,0.75);

        foundServo.setPosition(0.17);
        sleep(300);

        //TODO: add a timer failsafe of 2 seconds (maybe 3?)
        oldDrive.newTurn(-180, 0.75, 5);
        //oldDrive.turnTo(0, 0.75);


        //deploy arm
        arm.pitchPower(0.5f);
        sleep(500);
        arm.pitchPower(0);
        sleep(500);
        arm.pitchPower(-0.5f);
        sleep(1000);
        arm.pitchPower(0);


        //15 forwards
        oldDrive.driveDistance(DeadWheels.armSide, -10, 1, 2);
        oldDrive.driveDistance(DeadWheels.grabberSide, 25, 1, 3);
        //TODO: put a newTurnTo() here to get back to -180 degrees.
        oldDrive.driveDistance(DeadWheels.grabberSide, -70, 1, 5);
        oldDrive.driveDistance(DeadWheels.armSide, 15, 0.5, 2);
        stop();
    }
}
