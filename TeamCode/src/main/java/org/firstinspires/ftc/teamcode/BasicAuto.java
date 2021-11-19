package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "Auto", group = "working")
public class BasicAuto extends OldRobot {
    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        timer.restart();



        sleep(1000);

        oldMechDrive.driveForward(0.6);

        sleep(3800);

        oldMechDrive.driveForward(0.9);

        sleep(800);

        oldMechDrive.driveBackward(0.9);

        sleep(300);

        oldMechDrive.stopDriving();

        sleep(1000);

        oldMechDrive.driveBackward(0.6);

        sleep(900);

        oldMechDrive.stopDriving();

        stop();
    }
}
