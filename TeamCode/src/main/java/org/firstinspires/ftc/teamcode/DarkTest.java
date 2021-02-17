package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name="\uD83D\uDD34\uD83D\uDD35 \uD83D\uDC8E\uD83C\uDFE2 \uD83C\uDFF0\uD83C\uDF09 Dark Test", group="test")
public class DarkTest extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        oldDrive.strafeLeft(0.33);
        sleep(2000);
        oldDrive.strafeRight(0.33);
        sleep(2000);
        oldDrive.goForwards(0.33);
        sleep(2000);
        oldDrive.goBackwards(0.33);
        sleep(2000);
        oldDrive.turnLeft(0.33);
        sleep(2000);
        oldDrive.turnRight(0.33);
        sleep(2000);
        oldDrive.stopAll();


        stop();
    }
}

