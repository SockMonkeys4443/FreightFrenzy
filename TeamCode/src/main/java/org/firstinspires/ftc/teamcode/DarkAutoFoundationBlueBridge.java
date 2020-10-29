package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="\uD83C\uDFE2\uD83D\uDD35\uD83C\uDF09 Foundation Blue Bridge", group="workingFoundation")
public class DarkAutoFoundationBlueBridge extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {

        //OldDrive grabberSide to the foundation
        oldDrive.driveDistance(DeadWheels.grabberSide, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);

        //drives backwards (the robot is facing backwards) for a second
        oldDrive.goForwards(0.5);
        sleep(1000);
        oldDrive.stopAll();

        //turns to put the foundation into its position
        oldDrive.newTurnTo(90,1, 8);

        foundServo.setPosition(0.17); //let go of the foundation
        //wiggle to let go of the servo
        oldDrive.newTurnTo(-90 ,1, 2);
        //turn around before parking
        sleep(1000);
        //double check that we turned correctly
        oldDrive.newTurnTo(-90, 1, 8);

        //push foundation further into the building zone
        //oldDrive.driveDistance(DeadWheels.armSide, -10, 1, 2);
        oldDrive.driveDistance(DeadWheels.grabberSide, 25, 1, 3);
        //correct orientation once more, and park
        oldDrive.newTurnTo(-90, 1, 8);
        oldDrive.driveDistance(DeadWheels.armSide, 10, 0.5, 2);
        oldDrive.driveDistance(DeadWheels.grabberSide, -85, 1, 5);
        stop();

    }
}