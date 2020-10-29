package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="✡ Dark Auto Foundation Red Distance Sensor", group="working")
public class DarkAutoFoundationRedSensor extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        timer.restart();
        oldDrive.goBackwards(0.8);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 50) {
            if(telemetryEnabled)
                telemetry.addData("Distance: ",distance.cmBack()); telemetry.update();
        } //TODO: make a method to do this part automatically.

        oldDrive.stopAll();
        oldDrive.goBackwards(0.3);
        while (opModeIsActive() && timer.check() < 1.33f && distance.cmBack() > 10) {
            if(telemetryEnabled)
                telemetry.addData("Distance: ",distance.cmBack()); telemetry.update();
        }
        oldDrive.stopAll();
        //oldDrive.driveDistance(DeadWheels.grabberSide, -67.5f, 0.3, 10);
        foundServo.setPosition(1);
        sleep(1000);

        oldDrive.strafeRight(0.70);
        timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        sleep(325);
        //goes for a little bit of extra time
        oldDrive.goForwards(1);
        sleep(500);
        oldDrive.stopAll();


        oldDrive.turn(-90,0.75);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        sleep(300);

        oldDrive.newTurn(-90, 0.75, 5);
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
        oldDrive.driveDistance(DeadWheels.grabberSide, -10, 1, 2);
        //50 to the right
        oldDrive.driveDistance(DeadWheels.armSide, 25, 1, 3);
        //TODO: put a newTurnTo() here to get back to -180 degrees.
        oldDrive.driveDistance(DeadWheels.armSide, -70, 1, 6);
        oldDrive.driveDistance(DeadWheels.grabberSide, 15, 0.5, 2);
        stop();

    }
}
