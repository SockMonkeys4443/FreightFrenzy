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


    double brazoSpeed = 0.4;

    double scoopPosition = 0;
    double scoopRotationModifier = 0.035;

    boolean a1Released = true;


    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        driveRobot();
        runBrazo();

        rotateScoop();

        intake.setPower(gamepad2.right_trigger - (gamepad2.left_trigger * 0.7));

        if (gamepad2.b) {
            scoopPosition = 1;
        } else if (gamepad2.a) {
            scoopPosition = 0.5;
        } else if (gamepad2.x) {
            scoopPosition = 0;
        }

        runDuckyWheel();

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

    private void runBrazo() {
        double brazoPower = gamepad2.left_stick_y * brazoSpeed + gamepad2.right_stick_y * brazoSpeed * 0.35;

        brazo.setPower(brazoPower);
    }


    private void rotateScoop() {
        scoopPosition += gamepad2.right_stick_y * scoopRotationModifier;

        //scoop.setPosition(scoopPosition);
    }

    private void runDuckyWheel() {
        duckyWheel.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
    }


}


//ligma