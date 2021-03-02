package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOp", group = "working")
public class TeleOpOne extends Robot {

    double drivePower = 1;
    boolean conveyorOn = false;
    boolean shooting = false;
    int intakeMode = 0;

    double sheeleyLocation = 1;

    NewGamepadButtons buttons1;
    NewGamepadButtons buttons2;

    boolean[] button1Values;
    boolean[] button2Values;

    @Override
    public void robotInit() {
        buttons1 = new NewGamepadButtons(gamepad1);
        buttons2 = new NewGamepadButtons(gamepad2);
        wobbleSheeley.setTouchPosition(1.00);
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

        if (button2Values[2]) {
            toggleIntakeNegative();
        }

        //A2 pressed
        if (button2Values[0]) {
            toggleIntake();
        }


         if (gamepad2.b) {
             conveyorMotor.setPower(1);
         } else if (gamepad2.y) {
             conveyorMotor.setPower(-1);
         } else {
             conveyorMotor.setPower(0);
         }



        if (gamepad2.right_trigger > 0.5f) {
            wobbleSheeley.prepareToTouch();
        }
        if (gamepad2.left_trigger > 0.5f) {
            wobbleSheeley.disengage();
        }

        if (gamepad2.left_stick_y > 0.6) {
            sheeleyLocation += 0.01;
        } else if (gamepad2.left_stick_y > 0.2) {
            sheeleyLocation += 0.005;
        }
        if (gamepad2.left_stick_y < -0.6) {
            sheeleyLocation -= 0.01;
        } else if (gamepad2.left_stick_y < -0.2) {
            sheeleyLocation -= 0.005;
        }
        sheeleyLocation = Math.max(sheeleyLocation, 0);
        sheeleyLocation = Math.min(sheeleyLocation, 1.00);

        wobbleSheeley.setTouchPosition(sheeleyLocation);

        driveRobot();
        telemetry.addData("servoPos", wobbleSheeley.base.getPosition());
        telemetry.update();


        idle();
    }


    private void driveRobot() {
        //sets the powers based on stick positions
        double sidePower = gamepad1.left_stick_x * drivePower;
        double frontPower = gamepad1.left_stick_y * drivePower;
        double turnPower = gamepad1.right_stick_x * drivePower;

        //sets sidePower or frontPower to zero if it is less than a third of the other's value
        zeroDoubleValue(frontPower, sidePower, 0.33);
        zeroDoubleValue(sidePower, frontPower, 0.33);

        //calculates the power needed for each motor
        double flPower = (frontPower + sidePower) + turnPower;
        double frPower = (frontPower - sidePower) - turnPower;
        double blPower = (frontPower - sidePower) + turnPower;
        double brPower = (frontPower + sidePower) - turnPower;

        //sets each motor to the desired power
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
        if (intakeMode == 1) {
            intake.stopIntake();
            intakeMode = 0;
        } else {
            intake.runIntake();
            intakeMode = 1;
        }
    }

    void toggleIntakeNegative() {
        if (intakeMode == -1) {
            intake.stopIntake();
            intakeMode = 0;
        } else {
            intake.reverseIntake();
            intakeMode = -1;
        }
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
