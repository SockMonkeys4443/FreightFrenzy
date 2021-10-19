package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "BRUH BOT BRUH BOT", group = "working")
public class BruhBot extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private DcMotor duckyWheel;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right");

        duckyWheel = hardwareMap.get(DcMotor.class, "duck_wheel");


        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            driveRobot();

            idle();
        }
    }

    private void driveRobot() {
        double forwardPower = gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x * 0.7;

        frontLeftDrive.setPower(forwardPower + turnPower);
        frontRightDrive.setPower(forwardPower - turnPower);
        backLeftDrive.setPower(forwardPower + turnPower);
        backRightDrive.setPower(forwardPower - turnPower);
    }

}
