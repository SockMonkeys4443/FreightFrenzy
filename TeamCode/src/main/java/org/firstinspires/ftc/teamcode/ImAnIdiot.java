package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



@Autonomous(name = "Test Stuff", group = "test")
public class ImAnIdiot extends Robot {

    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        timer.restart();

        sleep(500);

        drive.setMotorPower(1, 0, 0, 0);

        sleep(1000);

        drive.setMotorPower(0, 1, 0, 0);

        sleep(1000);

        drive.setMotorPower(0, 0, 1, 0);

        sleep(1000);

        drive.setMotorPower(0, 0, 0, 1);

        sleep(1000);

        stop();
    }

}
