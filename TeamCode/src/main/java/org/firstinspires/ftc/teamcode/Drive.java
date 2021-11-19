package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    Robot opMode;

    IMUController imuController;

    float wheelDiameterInches = 5.0625f;
    float wheelCircumferenceInches = wheelDiameterInches * (float) Math.PI;
    float ticksPerRotation = 360 * 4;
    float ticksPerInch = ticksPerRotation / wheelCircumferenceInches;
    float inchesPerTick = wheelCircumferenceInches / ticksPerRotation;

    float targetTicks = 0;
    boolean forward = true;
    boolean turningRight = true;
    boolean movingToLocation = false;

    void init(Robot opMode) {
        this.opMode = opMode;

        frontLeft = opMode.hardwareMap.get(DcMotor.class, "front_left");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "front_right");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "back_left");
        backRight = opMode.hardwareMap.get(DcMotor.class, "back_right");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        this.imuController = opMode.imuController;
    }

    public void resetEncoder() {
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private int getTicks() {
        return backLeft.getCurrentPosition();
    }

    private float getTargetTicks(float distanceInches) {
        return getTicks() + distanceInches * ticksPerInch;
    }


    void stop() {
        setMotorPower(0, 0, 0, 0);
    }

    public void driveForward(double power, float distance) {
        movingToLocation = true;
        targetTicks = getTargetTicks(distance);
        forward = distance > 0;

        while (opMode.opModeIsActive() && movingToLocation) {
            opMode.telemetry.addData("target ticks", targetTicks);
            opMode.telemetry.addData("current ticks", getTicks());
            opMode.telemetry.update();
            if (forward) {
                if (getTicks() >= targetTicks) {
                    stop();
                    movingToLocation = false;
                } else {
                    setMotorPower(-power, -power, -power, -power);
                }
            } else {
                if (getTicks() <= targetTicks) {
                    stop();
                    movingToLocation = false;
                } else {
                    setMotorPower(-power, -power, -power, -power);
                }
            }
        }
    }

    public void turnLeft(double power, float angle) {
        float startingAngle = imuController.getAngle();
        float targetAngle = startingAngle + angle;
        movingToLocation = true;

        while (opMode.opModeIsActive() && movingToLocation) {
            if (imuController.getAngle() > targetAngle) {
                stop();
                movingToLocation = false;
            } else {
                setMotorPower(-power, power, -power, power);
            }
        }


    }

    public void setMotorPower(double frontLeft, double frontRight, double backLeft, double backRight) {
        this.frontLeft.setPower(frontLeft);
        this.frontRight.setPower(frontRight);
        this.backLeft.setPower(backLeft);
        this.backRight.setPower(backRight);
    }
}
