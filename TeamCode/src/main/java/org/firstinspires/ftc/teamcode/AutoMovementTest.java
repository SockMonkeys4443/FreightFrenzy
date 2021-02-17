package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "Auto Movement Test", group = "test")
public class AutoMovementTest extends Robot {

    @Override
    public void robotInit() {
        telemetry.addData("Current Movement: ", "none");
    }

    @Override
    public void robotRunning() {
        timer.restart();

        sleep(1000);

        drive.driveForward(0.5);

        sleep(1000);

        drive.driveBackward(0.5);

        sleep(1000);

        drive.strafeLeft(0.5);

        sleep(1000);

        drive.strafeRight(0.5);

        sleep(1000);

        drive.turnLeft(0.5);

        sleep(1000);

        drive.turnRight(0.5);

        sleep(1000);

        drive.stopDriving();
    }
}
