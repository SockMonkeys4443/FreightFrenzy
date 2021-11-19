package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name = "BRUH BOT BRUH BOT", group = "working")
public class BruhBot extends Robot {

    private DigitalChannel brazoLimit;

    double wheelSpeed = 1;

    double duckyPower = 0;

    double brazoSpeed = 0.4;

    double scoopPosition = 0;
    double scoopRotationModifier = 0.035;

    boolean tankDriveActive = false;

    boolean a1Released = true;
    boolean x1Released = true;


    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {

        if (gamepad1.x && x1Released) {
            tankDriveActive = !tankDriveActive;
            x1Released = false;
        } else if (!gamepad1.x && !x1Released) {
            x1Released = true;
        }

        if (tankDriveActive) {
            driveTank();
        } else {
            driveRobot();
        }

        runBrazo();

        intake.setPower(gamepad1.right_trigger - (gamepad1.left_trigger * 0.8));

        if (gamepad2.b) {
            scoopPosition = 1;
        } else if (gamepad2.a) {
            scoopPosition = 0.5;
        } else if (gamepad2.x) {
            scoopPosition = 0;
        }

        runDuckyWheel();

        duckyWheel.setPower(duckyPower);

        if (gamepad1.a && a1Released) {
            wheelSpeed = wheelSpeed == 1 ? 0.4 : 1;
            a1Released = false;
        } else if (!gamepad1.a && !a1Released) {
            a1Released = true;
        }

        brazo.setScoopPosition(scoopPosition);

        idle();
    }


    private void driveRobot() {
        double forwardPower = gamepad1.left_stick_y * wheelSpeed;
        double turnPower = gamepad1.right_stick_x * 0.85 * wheelSpeed;

        drive.setMotorPower(forwardPower + turnPower, forwardPower - turnPower, forwardPower + turnPower, forwardPower - turnPower);
    }

    private void driveTank() {
        double leftPower = gamepad1.left_stick_y * wheelSpeed;
        double rightPower = gamepad1.right_stick_y * wheelSpeed;

        drive.setMotorPower(leftPower, rightPower, leftPower, rightPower);
    }

    private void runBrazo() {
        double brazoPower = gamepad2.left_stick_y * brazoSpeed + gamepad2.right_stick_y * brazoSpeed * 0.5;

        brazo.setPower(brazoPower);
    }



    private void runDuckyWheel() {
        if (gamepad1.left_bumper) {
            duckyPower = -1;
        } else if (gamepad1.right_bumper) {
            duckyPower = 1;
        } else {
            duckyPower = 0;
        }
    }


}


//ligma