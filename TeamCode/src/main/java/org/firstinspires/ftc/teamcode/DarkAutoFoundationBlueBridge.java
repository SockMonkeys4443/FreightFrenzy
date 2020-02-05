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

        //Drive grabberSide to the foundation
        drive.driveDistance(DeadWheels.grabberSide, -68.0f, 0.7, 2.25f);
        foundServo.setPosition(1);
        sleep(1000);

        //drives backwards (the robot is facing backwards) for a second
        drive.goForwards(0.5);
        sleep(1000);
        drive.stopAll();

        //turns to put the foundation into its position
        drive.newTurnTo(90,1, 8);

        foundServo.setPosition(0.17); //let go of the foundation
        //wiggle to let go of the servo
        drive.newTurnTo(-90 ,1, 2);
        //turn around before parking
        sleep(1000);
        //double check that we turned correctly
        drive.newTurnTo(-90, 1, 8);

        //push foundation further into the building zone
        //drive.driveDistance(DeadWheels.armSide, -10, 1, 2);
        drive.driveDistance(DeadWheels.grabberSide, 25, 1, 3);
        //correct orientation once more, and park
        drive.newTurnTo(-90, 1, 8);
        drive.driveDistance(DeadWheels.armSide, 10, 0.5, 2);
        drive.driveDistance(DeadWheels.grabberSide, -85, 1, 5);
        stop();

    }
}