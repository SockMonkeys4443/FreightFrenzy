package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOp", group = "working")
public class TeleOpOne extends Robot {

    double drivePower = 1;
    boolean intakeOn = false;
    boolean conveyorOn = false;
    boolean shooting = false;

    double sheeleyLocation;

    NewGamepadButtons buttons1;
    NewGamepadButtons buttons2;

    boolean[] button1Values;
    boolean[] button2Values;

    @Override
    public void robotInit() {
        buttons1 = new NewGamepadButtons(gamepad1);
        buttons2 = new NewGamepadButtons(gamepad2);
        sheeleyLocation = wobbleSheeley.findSheeley();
    }

    @Override
    public void robotRunning() {
        //[A, B. X. Y]
        button1Values = buttons1.checkButtonValues();
        button2Values = buttons2.checkButtonValues();

        //A1 pressed
        if (button1Values[0]) {
            toggleSpeed();
        }
        //B1 pressed
        if (button1Values[1]) {
            toggleShooter();
        }

        //A2 pressed
        if (button2Values[0]) {
            toggleIntake();
        }
        //B2 pressed
        if (button2Values[1]) {
            toggleConveyor();
        }
        //X2 pressed
        if (button2Values[2]) {
            if (intakeOn || conveyorOn) {
                conveyorMotor.setPower(0);
                intake.stopIntake();
                conveyorOn = false;
                intakeOn = false;
            } else {
                conveyorMotor.setPower(1);
                intake.runIntake();
                conveyorOn = true;
                intakeOn = false;
            }
        }
        //Y2 pressed
        if (button2Values[3]) {
            wobbleSheeley.touchKids();
        }

        if (gamepad2.right_trigger > 0.5f) {
            wobbleSheeley.prepareToTouch();
        }
        if (gamepad2.left_trigger > 0.5f) {
            wobbleSheeley.disengage();
        }

        if (gamepad2.left_stick_y > 0.5) {
            sheeleyLocation += 0.01;
        }
        if (gamepad2.left_stick_y < -0.5) {
            sheeleyLocation -= 0.01;
        }
        sheeleyLocation = Math.max(sheeleyLocation, 0.36);
        sheeleyLocation = Math.min(sheeleyLocation, 1.00);

        wobbleSheeley.setTouchPosition(sheeleyLocation);

        driveRobot();


        idle();
    }


    private void driveRobot() {
        double sidePower = gamepad1.left_stick_x * drivePower;
        double frontPower = gamepad1.left_stick_y * drivePower;

        double turnPower = gamepad1.right_stick_x * drivePower;

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

    void toggleShooter() {
        shooting = !shooting;
        if (shooting) {
            shooter.shoot();
        } else {
            shooter.stopShooting();
        }
    }

    void toggleIntake() {
        if (intakeOn) {
            intake.stopIntake();
        } else {
            intake.runIntake();
        }
        intakeOn = !intakeOn;
    }

    void toggleConveyor() {
        conveyorOn = !conveyorOn;
        if (conveyorOn) {
            conveyorMotor.setPower(1);
        } else {
            conveyorMotor.setPower(0);
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
