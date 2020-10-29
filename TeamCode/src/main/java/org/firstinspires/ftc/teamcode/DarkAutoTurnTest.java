package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="(DEBUG) Dark Auto Turn Test", group="test")
public class DarkAutoTurnTest extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        telemetryEnabled=true;
        //oldDrive.newTurn(-90, 0.4);
        //sleep(2000);
        oldDrive.newTurnTo(90,0.5, 5);
        sleep(2000);
        oldDrive.newTurnTo(180,0.5, 5);
        sleep(2000);
        oldDrive.newTurnTo(-90,0.5, 5);
        sleep(2000);
        oldDrive.newTurnTo(0,0.5, 5);
        sleep(2000);
        oldDrive.newTurnTo(-90, 0.5, 5);

        stop();
    }
}
