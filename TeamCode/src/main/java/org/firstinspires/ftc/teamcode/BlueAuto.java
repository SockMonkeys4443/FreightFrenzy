package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue Auto", group = "working")
public class BlueAuto extends OldRobot {
    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        timer.restart();

        Camera.RingPosition ringCount = camera.ringCount();

        telemetry.addData("Rings: ", ringCount );

        sleep(1500);

        oldMechDrive.driveForward(0.6);

        sleep(500);

        if (ringCount == Camera.RingPosition.NONE) {

            oldMechDrive.driveForward(0.6);

            sleep(1300);

            oldMechDrive.strafeLeft(0.6);

            sleep(1950);

            oldMechDrive.driveForward(0.6);

            sleep(850);

            oldMechDrive.driveForward(0.9);

            sleep(700);

            oldMechDrive.driveBackward(0.9);

            sleep(600);

            oldMechDrive.strafeRight(0.7);

            sleep(1800);

            oldMechDrive.driveForward(0.7);

            sleep(1000);


            oldMechDrive.stopDriving();


        } else if (ringCount == Camera.RingPosition.ONE) {

            oldMechDrive.driveForward(0.6);

            sleep(3300);

            oldMechDrive.driveForward(0.9);

            sleep(800);

            oldMechDrive.driveBackward(0.9);

            sleep(300);

            oldMechDrive.stopDriving();

            sleep(1000);

            oldMechDrive.driveBackward(0.6);

            sleep(900);

            oldMechDrive.stopDriving();


        } else {

            oldMechDrive.driveForward(0.6);

            sleep(1300);

            oldMechDrive.strafeLeft(0.6);

            sleep(1850);

            oldMechDrive.turnRight(0.5);

            sleep(100);

            oldMechDrive.driveForward(0.6);

            sleep(3400);

            oldMechDrive.driveForward(0.9);

            sleep(800);

            oldMechDrive.driveBackward(0.9);

            sleep(300);

            oldMechDrive.stopDriving();

            sleep(1000);

            oldMechDrive.driveBackward(0.6);

            sleep(1700);

            oldMechDrive.stopDriving();


        }
//ligma

        stop();

    }
}
