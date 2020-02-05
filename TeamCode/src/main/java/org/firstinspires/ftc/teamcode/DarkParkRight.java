package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Park Right", group="park")
public class DarkParkRight extends SuperDark {
    @Override
    public void darkInit() {
    }

    @Override
    public void darkRunning() {
        //THE CAMERA IS AT THE FRONT FOR THIS OPMODE
        timer.restart();

        drive.driveDistance(DeadWheels.armSide, 10, 0.5, 1);

        drive.driveDistance(DeadWheels.grabberSide, -60, 0.5, 2); //will probably need adjusting

        stop();

    }
}