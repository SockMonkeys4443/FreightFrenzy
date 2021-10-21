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

    private DcMotor brazo;

    private DcMotor leftIntake;
    private DcMotor rightIntake;

    private DcMotor duckyWheel;

    double brazoSpeed = 0.2;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right");

        brazo = hardwareMap.get(DcMotor.class, "brazo");

        leftIntake = hardwareMap.get(DcMotor.class, "left_intake");
        rightIntake = hardwareMap.get(DcMotor.class, "right_intake");

        duckyWheel = hardwareMap.get(DcMotor.class, "ducky_wheel");

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            driveRobot();
            runBrazo();

            setIntakePower(gamepad2.right_trigger - gamepad2.left_trigger);

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

    private void runBrazo() {
        double brazoPower = gamepad2.right_stick_y * brazoSpeed;

        brazo.setPower(brazoPower);
    }

    private void setIntakePower(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }


}
