package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp", group = "test")
public class TeleOpOne extends Robot {

    @Override
    public void robotInit() {

    }

    @Override
    public void robotRunning() {
        driveRobot();

        idle();
    }

    private void driveRobot() {
        double frontPower = gamepad1.left_stick_x;
        double sidePower = -gamepad1.left_stick_y;

        double turnPower = gamepad1.right_stick_x;

        double flPower = (frontPower + sidePower) + turnPower;
        double frPower = (frontPower - sidePower) - turnPower;
        double blPower = (frontPower - sidePower) + turnPower;
        double brPower = (frontPower + sidePower) - turnPower;

        drive.setMotorPower(flPower, frPower, blPower, brPower);
    }
}
