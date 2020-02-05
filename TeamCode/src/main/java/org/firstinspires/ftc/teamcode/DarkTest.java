package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="\uD83D\uDD34\uD83D\uDD35 \uD83D\uDC8E\uD83C\uDFE2 \uD83C\uDFF0\uD83C\uDF09 Dark Test", group="test")
public class DarkTest extends SuperDark {

    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        drive.strafeLeft(0.33);
        sleep(2000);
        drive.strafeRight(0.33);
        sleep(2000);
        drive.goForwards(0.33);
        sleep(2000);
        drive.goBackwards(0.33);
        sleep(2000);
        drive.turnLeft(0.33);
        sleep(2000);
        drive.turnRight(0.33);
        sleep(2000);
        drive.stopAll();


        stop();
    }
}

