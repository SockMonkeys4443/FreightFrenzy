package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.GamepadButtons.Button.*;

@TeleOp(name = "TeleOp", group = "test")
public class TeleOpOne extends Robot {

    double drivePower = 1;

    GamepadButtons buttons1;
    GamepadButtons buttons2;

    @Override
    public void robotInit() {
        buttons1 = new GamepadButtons(gamepad1);
        buttons2 = new GamepadButtons(gamepad2);
    }

    @Override
    public void robotRunning() {

        if (buttons1.getStatus(A)) {
            toggleSpeed();
        }



        driveRobot();


        buttons1.update();
        buttons2.update();

        idle();
    }


    private void driveRobot() {
        double frontPower = gamepad1.left_stick_x * drivePower;
        double sidePower = -gamepad1.left_stick_y * drivePower;

        double turnPower = gamepad1.right_stick_x * drivePower * 0.9;

        zeroDoubleValue(frontPower, sidePower, 0.33);
        zeroDoubleValue(sidePower, frontPower, 0.33);

        double flPower = (frontPower + sidePower) + turnPower;
        double frPower = (frontPower - sidePower) - turnPower;
        double blPower = (frontPower - sidePower) + turnPower;
        double brPower = (frontPower + sidePower) - turnPower;

        drive.setMotorPower(flPower, frPower, blPower, brPower);
    }


    void toggleSpeed() {
        if (drivePower == 1) {
            drivePower = 0.3;
        } else {
            drivePower = 1;
        }
    }


    private double zeroDoubleValue(double target, double compare, double threshold) {

        double check = target;

        //returns 0 if target is less than the comparison value * the threshold
        if (java.lang.Math.abs(target) < java.lang.Math.abs(compare) * threshold) {
            check = 0;
        }

        return check;
    }
}
