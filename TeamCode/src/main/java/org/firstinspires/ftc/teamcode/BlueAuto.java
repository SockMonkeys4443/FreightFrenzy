package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "Blue Auto", group = "working")
public class BlueAuto extends Robot {
    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        timer.restart();

        Camera.RingPosition ringCount = camera.ringCount();

        telemetry.addData("Rings: ", ringCount );

        sleep(1500);

        drive.driveForward(0.6);

        sleep(500);

        if (ringCount == Camera.RingPosition.NONE) {

            drive.driveForward(0.6);

            sleep(1300);

            drive.strafeLeft(0.6);

            sleep(2000);

            drive.driveForward(0.6);

            sleep(1300);

            drive.driveForward(0.9);

            sleep(800);

            drive.driveBackward(0.9);

            sleep(300);

            drive.stopDriving();

            sleep(1000);

            drive.stopDriving();


        } else if (ringCount == Camera.RingPosition.ONE) {

            drive.driveForward(0.6);

            sleep(3300);

            drive.driveForward(0.9);

            sleep(800);

            drive.driveBackward(0.9);

            sleep(300);

            drive.stopDriving();

            sleep(1000);

            drive.driveBackward(0.6);

            sleep(900);

            drive.stopDriving();


        } else {

            drive.driveForward(0.6);

            sleep(1300);

            drive.strafeLeft(0.6);

            sleep(1900);

            drive.driveForward(0.6);

            sleep(3400);

            drive.driveForward(0.9);

            sleep(800);

            drive.driveBackward(0.9);

            sleep(300);

            drive.stopDriving();

            sleep(1000);

            drive.driveBackward(0.6);

            sleep(1700);

            drive.stopDriving();


        }
//ligma

        stop();

    }
}
