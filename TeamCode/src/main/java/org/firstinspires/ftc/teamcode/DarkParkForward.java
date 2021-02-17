package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="Park 2 ft Forward", group="park")
public class DarkParkForward extends SuperDark {
    @Override
    public void darkInit() {
    }

    @Override
    public void darkRunning() {
        //THE CAMERA IS AT THE FRONT FOR THIS OPMODE
        timer.restart();

        oldDrive.driveDistance(DeadWheels.armSide, 60, 0.5, 1);

        stop();

    }
}