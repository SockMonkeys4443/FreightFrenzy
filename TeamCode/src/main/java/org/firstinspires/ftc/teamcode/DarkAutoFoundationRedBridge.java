package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="\uD83C\uDFE2\uD83D\uDD34\uD83C\uDF09 Foundation Red Bridge", group="workingFoundation")
public class DarkAutoFoundationRedBridge extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        //TODO: In the future we need to comment what the auto is doing


        oldDrive.driveDistance(DeadWheels.grabberSide, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);


        //oldDrive.strafeRight(0.70);
        //timer.restart();
        //strafes to the right while the foundation is still in front of us
        //while (opModeIsActive() && timer.check() < 5 && distance.cmBack() < 25);
        //sleep(325);
        //goes for a little bit of extra time
        oldDrive.goForwards(0.5);
        sleep(1000);
        oldDrive.stopAll();

        oldDrive.driveDistance(DeadWheels.grabberSide, 25.0f, 0.7, 2.25f);
        sleep(500);
        oldDrive.stopAll();



        oldDrive.newTurnTo(-90,1, 8);


        foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
        //wiggle to let go of the servo
        oldDrive.newTurnTo(-90 ,1, 2);
        sleep(1000);

        oldDrive.newTurnTo(90, 1, 8);

        //oldDrive.driveDistance(DeadWheels.armSide, 10, 1, 2);
        oldDrive.driveDistance(DeadWheels.grabberSide, 25, 1, 3);
        oldDrive.newTurnTo(90, 1, 8);
        oldDrive.driveDistance(DeadWheels.armSide, -25, 0.5, 2);
        oldDrive.driveDistance(DeadWheels.grabberSide, -85, 1, 5);
        stop();

    }
}
