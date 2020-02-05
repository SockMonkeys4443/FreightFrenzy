package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.GamepadButtons.Button.*;


@TeleOp(name="★Dark TeleOp★", group="test")
public class DarkTeleOp extends SuperDark {


    enum DriveMode { DIAGONAL, NORMAL, ADJUSTED}

    double drivePower = 1;
    float armSpeed = 1f;

    boolean clawClosed = false;
    boolean armReseting = false;
    boolean pitchReset = false;
    boolean extendReset = false;

    GamepadButtons buttons1;
    GamepadButtons buttons2;

    enum PositionMode {NORMAL, TESTONE, TESTTWO}

    enum RobotFront {ARM, GRABBER, BACKARM, PHONE}


    DriveMode driveMode = DriveMode.NORMAL;
    RobotFront robotFront = RobotFront.ARM;
    PositionMode positionMode = PositionMode.NORMAL;

    @Override
    public void darkInit() {
        buttons1 = new GamepadButtons(gamepad1);
        buttons2 = new GamepadButtons(gamepad2);
    }

    @Override
    public void darkRunning() {

        //drive
        if (driveMode == DriveMode.NORMAL) {
            driveNormal(drivePower);
        } else if (driveMode == DriveMode.DIAGONAL) {
            driveDiagonal(drivePower);
        } else if (driveMode == DriveMode.ADJUSTED) {
            driveCorrected(drivePower);
        } else {
            driveNormal(drivePower);
        }

        //buttons


        if (buttons1.buttonPressed(Y)) {
            toggleFront();
        }

        if (buttons1.buttonPressed(B)) {
            toggleServo();
        }

        if (buttons1.buttonPressed(A)) {
            toggleSpeed();
        }

        if (buttons2.buttonPressed(Y)) {
            toggleArmSpeed();
        }

        if (buttons2.buttonPressed(X)) {
            arm.resetPitchEncoder();
        }

        if (buttons2.buttonPressed(A)) {
            toggleArmReseting();
        }

        buttons1.update();
        buttons2.update();

        //arm power
        //oldArm.armPower(gamepad2.right_stick_y * armSpeed);
        if (armReseting) {
            gotoGrabLocation(0.5f);
        } else {
            arm.extendPower(-gamepad2.left_stick_y * armSpeed);
            arm.pitchPower(gamepad2.right_stick_y * armSpeed);
            pitchReset = false;
            extendReset = false;
        }

        if (armReseting && !arm.pitchState() && !arm.extendState()) {
            armReseting = false;
        }

        if (armReseting && !arm.pitchState() && !arm.extendState()) {
            armReseting = false;
        }

        /**
        if (gamepad2.dpad_up) {
            arm.positionServo.setPosition(arm.positionServo.getPosition() - 1);
        } else if (gamepad2.dpad_down) {
            arm.positionServo.setPosition(arm.positionServo.getPosition() + 1);
        } else {
            //update servo position
        } **/

        //claw
        if (gamepad2.right_trigger > 0.25) {
            arm.grabServo.setPosition(1);
        } else if (gamepad2.left_trigger > 0.25) {
            arm.grabServo.setPosition(0.33);
        }


        //telemetry
        telemetry.addData("Heading", imuController.getAngle() + " " + imuController.getAngle360());
        telemetry.addData("HeadingA:", imuController.getAngle360());
        telemetry.addData("driveMode", driveMode);
        telemetry.addData("Drive Power", drivePower);

        telemetry.update();

    }


        //toggles
        void toggleFront() {
        if (robotFront == RobotFront.ARM) {
            robotFront = RobotFront.GRABBER;
        } else {
            robotFront = RobotFront.ARM;
        }
    }
        void toggleDrive() {
            if (driveMode == DriveMode.DIAGONAL) {
                driveMode = DriveMode.NORMAL;
            }
            //else if (driveMode==DriveMode.DIAGONAL) {driveMode=DriveMode.ADJUSTED;}
            else {
                driveMode = DriveMode.DIAGONAL;
            }
        }

        void toggleServo() {
            if (foundServo.getPosition() == 1) {
                telemetry.speak("Whos Joe?");
                foundServo.setPosition(0.17); //~30 degrees from the 0 point - that being the top
            } else {
                foundServo.setPosition(1);
            }
        }

        void toggleArmReseting() {
            if (!armReseting) {
                armReseting = true;
            } else {
                armReseting = false;
            }
        }

        void toggleSpeed() {
            if (drivePower == 1) {
                telemetry.speak("Slowmode Activated");
                drivePower = 0.3;
            } else {
                drivePower = 1;
                telemetry.speak("Slowmode Off");
            }
        }

        void toggleArmSpeed() {
            if (armSpeed != 1f) {
                armSpeed = 1f;
            } else {
                armSpeed = 0.4f;
            }
        }

        void gotoGrabLocation(float power) {
            if(!pitchReset && arm.pitchState()) {
                arm.pitchPower(power);
            } else if (!arm.pitchState()) {
                pitchReset = true;
                arm.pitchPower(0);
            }

            if (pitchReset) {
                if (!extendReset && arm.extendState()) {
                    arm.extendPower(-power);
                } else if (!arm.extendState()) {
                    extendReset = true;
                    arm.extendPower(0);
                }
            }
        }


        //Drive Methods

        void driveNormal(double power) {
            double frontPower = -gamepad1.left_stick_y * power;
            double sidePower = gamepad1.left_stick_x * power;

            double turnPower = gamepad1.right_stick_x * power * 0.8;

            //sets side power or front power to zero if they are less than 1/3 the value of the other power
            //this stops the robot from going at a slight angle when pushing the stick straight grabberSide
            sidePower = zeroDoubleValue(sidePower, frontPower, 0.3);
            frontPower = zeroDoubleValue(frontPower, sidePower, 0.3);

            if (robotFront == RobotFront.ARM) {
                drive.frontLeft.setPower(frontPower - sidePower + turnPower);
                drive.frontRight.setPower(-frontPower + sidePower + turnPower);
                drive.backLeft.setPower(-frontPower - sidePower + turnPower);
                drive.backRight.setPower(frontPower + sidePower + turnPower);
            } else if (robotFront == RobotFront.GRABBER) {
                drive.frontLeft.setPower(-frontPower - sidePower + turnPower);
                drive.frontRight.setPower(frontPower + sidePower + turnPower);
                drive.backLeft.setPower(-frontPower + sidePower + turnPower);
                drive.backRight.setPower(frontPower - sidePower + turnPower);
            }
        }


        void oldDriveNormal(double power){

            double frontPower = -gamepad1.left_stick_y * power;
            double sidePower = gamepad1.left_stick_x * power;

            double turnPower = gamepad1.right_stick_x * power * 0.8;

            //sets side power or front power to zero if they are less than 1/3 the value of the other power
            //this stops the robot from going at a slight angle when pushing the stick straight grabberSide
            sidePower = zeroDoubleValue(sidePower, frontPower, 0.33);
            frontPower = zeroDoubleValue(frontPower, sidePower, 0.33);


            drive.frontLeft.setPower((frontPower + sidePower) + turnPower);
            drive.frontRight.setPower((frontPower - sidePower) - turnPower);
            drive.backLeft.setPower((frontPower - sidePower) + turnPower);
            drive.backRight.setPower((frontPower + sidePower) - turnPower);


            telemetry.addData("frontPower", frontPower);
            telemetry.addData("sidePower", sidePower);
        }


        void driveDiagonal(double power){
            double frontPower = -gamepad1.left_stick_y * power;
            double sidePower = gamepad1.left_stick_x * power;

            double turnPower = gamepad1.right_stick_x * power * 0.8;

        /*
        drive.frontLeft.setPower((frontPower +sidePower) +turnPower);
        drive.frontRight.setPower((frontPower-sidePower) -turnPower);
        drive.backLeft.setPower((frontPower-sidePower) +turnPower);
        drive.backRight.setPower((frontPower+sidePower) -turnPower);
        */
            //frontLeft grabberSide
            //backRight grabberSide

            drive.frontLeft.setPower(frontPower + turnPower);
            drive.backRight.setPower(frontPower - turnPower);
            drive.frontRight.setPower(-sidePower - turnPower);
            drive.backLeft.setPower(-sidePower + turnPower);


        }

        void driveWeird ( double power){

            double frontPower = -gamepad1.left_stick_y;
            double sidePower = gamepad1.left_stick_x;
            double turnPower = gamepad1.right_stick_x;

            if (frontPower > 0.5) {
                frontPower = 1;
            } else if (frontPower < -0.5) {
                frontPower = -1;
            } else {
                frontPower = 0;
            }
            if (sidePower > 0.5) {
                sidePower = 1;
            } else if (sidePower < -0.5) {
                sidePower = -1;
            } else {
                sidePower = 0;
            }
            //if(turnPower>0.5) {turnPower=1;} else if (turnPower<-0.5) {turnPower=-1;} else {turnPower=0;}


            drive.frontLeft.setPower((frontPower + sidePower) + turnPower);
            drive.frontRight.setPower((frontPower - sidePower) - turnPower);
            drive.backLeft.setPower((frontPower - sidePower) + turnPower);
            drive.backRight.setPower((frontPower + sidePower) - turnPower);

        }

        void driveCorrected ( double power){

            //gets the robots current angle
            float angle = imuController.getAngle360();

            //values for FL and BR motors
            float[] modFL = new float[]{
                    -java.lang.Math.abs((angle - 315) / 45),
                    java.lang.Math.abs((angle - 315) / 45),
                    1,
                    1,
                    java.lang.Math.abs((angle - 135) / 45),
                    -java.lang.Math.abs((angle - 135) / 45),
                    -1,
                    -1,
                    -java.lang.Math.abs((angle - 315) / 45),
                    java.lang.Math.abs((angle - 315) / 45)
            };

            //values for FR and BL motors
            float[] modFR = new float[]{
                    1,
                    1,
                    java.lang.Math.abs((angle - 45) / 45),
                    -java.lang.Math.abs((angle - 45) / 45),
                    -1,
                    -1,
                    -java.lang.Math.abs((angle - 225) / 45),
                    java.lang.Math.abs((angle - 225) / 45),
                    1,
                    1
            };

            //sets any values less than 0.33 to 0
            modFL = zeroFloatArrayValues(modFL, .33f);
            modFR = zeroFloatArrayValues(modFR, .33f);

            //finds which value to use from the arrays, uses max function to prevent an error
            int angleTest = java.lang.Math.max((int) (java.lang.Math.floor(angle / 45)) + 2, 9);


            double gameY = gamepad1.left_stick_y;
            double gameX = gamepad1.left_stick_x;

            gameY = zeroDoubleValue(java.lang.Math.abs(gameY), java.lang.Math.abs(gameX), 0.33);
            gameX = zeroDoubleValue(java.lang.Math.abs(gameX), java.lang.Math.abs(gameY), 0.33);


            double frontPower1 = -gameY * power * modFL[angleTest];
            double frontPower2 = -gameY * power * modFR[angleTest];

            double sidePower1 = gameX * power * modFL[angleTest - 2];
            double sidePower2 = gameX * power * modFR[angleTest - 2];

            double turnPower = gamepad1.right_stick_x * power;

            drive.frontLeft.setPower((frontPower1 + sidePower1) + turnPower);
            drive.frontRight.setPower((frontPower2 - sidePower2) - turnPower);
            drive.backRight.setPower((frontPower1 + sidePower1) - turnPower);
            drive.backLeft.setPower((frontPower2 - sidePower2) + turnPower);

        }

    private float[] zeroFloatArrayValues(float[] array, float threshold) {

        //checks each value of the array and sets any values less than the threshold to 0
        for (int i = 0; i < array.length; i++) {
            if (array[i] < java.lang.Math.abs(threshold)) {
                array[i] = 0;
            }
        }
        return array;
    }

    //these parameters need better names
    private double zeroDoubleValue(double target, double compare, double threshold) {

        double check = target;

        //returns 0 if target is less than the comparison value * the threshold
        if (java.lang.Math.abs(target) < java.lang.Math.abs(compare) * threshold) {
            check = 0;
        }

        return check;
    }

}
