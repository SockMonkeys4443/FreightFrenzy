package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "Auto Movement Test", group = "test")
public class AutoMovementTest extends OldRobot {

    @Override
    public void robotInit() {
        telemetry.addData("Current Movement: ", "none");
    }

    @Override
    public void robotRunning() {
        timer.restart();

        sleep(1000);

        oldMechDrive.driveForward(0.5);

        sleep(1000);

        oldMechDrive.driveBackward(0.5);

        sleep(1000);

        oldMechDrive.strafeLeft(0.5);

        sleep(1000);

        oldMechDrive.strafeRight(0.5);

        sleep(1000);

        oldMechDrive.turnLeft(0.5);

        sleep(1000);

        oldMechDrive.turnRight(0.5);

        sleep(1000);

        oldMechDrive.stopDriving();
    }
}
