
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;



@TeleOp(name="Test Servo", group="Test OpMode")

public class ServoTest extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();

    CRServo testServo;

    @Override
    public void runOpMode() {

        testServo = hardwareMap.get(CRServo.class, "servo");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
            testServo.setPower(0.5);
        }

    }
}
