package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto", group = "working")
public class BasicAuto extends Robot {
    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        timer.restart();



        sleep(1000);

        drive.driveForward(0.6);

        sleep(3800);

        drive.driveForward(0.9);

        sleep(800);

        drive.driveBackward(0.9);

        sleep(300);

        drive.stopDriving();

        sleep(1000);

        drive.driveBackward(0.6);

        sleep(900);

        drive.stopDriving();

        stop();
    }
}
