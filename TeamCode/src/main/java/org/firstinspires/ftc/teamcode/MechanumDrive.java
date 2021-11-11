package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Mechanum Drive", group = "working")
public class MechanumDrive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    DcMotor frontLeftWheel;
    DcMotor frontRightWheel;
    DcMotor backLeftWheel;
    DcMotor backRightWheel;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontLeftWheel = hardwareMap.get(DcMotor.class, "front left wheel");
        frontRightWheel = hardwareMap.get(DcMotor.class, "front right wheel");
        backLeftWheel = hardwareMap.get(DcMotor.class, "back left wheel");
        backRightWheel = hardwareMap.get(DcMotor.class, "back right wheel");

        frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        //Waits for program to start
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            drive();

            idle();
        }

    }


    private void drive() {
        frontLeftWheel.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
        frontRightWheel.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
        backLeftWheel.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
        backRightWheel.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x);
    }


}
