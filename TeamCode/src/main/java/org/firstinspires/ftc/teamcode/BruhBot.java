package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



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

    private Servo scoop;

    private DigitalChannel brazoLimit;

    double wheelSpeed = 1;

    double duckyPower = 0;

    double brazoSpeed = 0.4;

    double scoopPosition = 0;
    double scoopRotationModifier = 0.035;

    boolean y1Released = true;
    boolean a1Released = true;
    boolean y2Released = true;


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

        scoop = hardwareMap.get(Servo.class, "scoop");

        brazoLimit = hardwareMap.get(DigitalChannel.class, "brazo_limit");

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        brazo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        brazo.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();



        while (opModeIsActive()) {
            driveRobot();
            runBrazo();

            rotateScoop();

            setIntakePower(gamepad2.right_trigger - gamepad2.left_trigger);

            if (gamepad2.b) {
                scoopPosition = 1;
            } else if (gamepad2.a) {
                scoopPosition = 0.5;
            } else if (gamepad2.x) {
                scoopPosition = 0;
            }



            if (gamepad1.y && y1Released) {
                duckyPower = duckyPower == 1 ? 0 : 1;
                y1Released = false;
            } else if (!gamepad1.y && !y1Released) {
                y1Released = true;
            }

            if (gamepad1.a && a1Released) {
                wheelSpeed = wheelSpeed == 1 ? 0.4 : 1;
                a1Released = false;
            } else if (!gamepad1.a && !a1Released) {
                a1Released = true;
            }

            scoop.setPosition(scoopPosition);

            duckyWheel.setPower(duckyPower);

            idle();
        }
    }

    private void driveRobot() {
        double forwardPower = gamepad1.left_stick_y * wheelSpeed;
        double turnPower = gamepad1.right_stick_x * 0.85 * wheelSpeed;

        frontLeftDrive.setPower(forwardPower + turnPower);
        frontRightDrive.setPower(forwardPower - turnPower);
        backLeftDrive.setPower(forwardPower + turnPower);
        backRightDrive.setPower(forwardPower - turnPower);
    }

    private void runBrazo() {
        double brazoPower = gamepad2.left_stick_y * brazoSpeed;

        brazo.setPower(brazoPower);
    }

    private void setIntakePower(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    private void rotateScoop() {
        scoopPosition += gamepad2.right_stick_y * scoopRotationModifier;

        //scoop.setPosition(scoopPosition);
    }


}
