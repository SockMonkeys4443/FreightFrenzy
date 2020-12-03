package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Drive {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    Robot opMode;

    void init (Robot opMode) {
        this.opMode = opMode;

        frontLeft = opMode.hardwareMap.get(DcMotor.class,"frontLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class,"frontRight");
        backLeft = opMode.hardwareMap.get(DcMotor.class,"backLeft");
        backRight = opMode.hardwareMap.get(DcMotor.class,"backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

    }

    //Directional Movement
    public void driveForward(double power) {
        setMotorPower(power, power, power, power);
    }

    public void driveBackward(double power) {
        setMotorPower(-power, -power, -power, -power);
    }

    public void strafeLeft(double power) {
        setMotorPower(-power, power, power, -power);
    }

    public void strafeRight(double power) {
        setMotorPower(power, -power, -power, power);
    }


    //Angular Movement
    public void strafeForwardLeft(double power) {
        setMotorPower(0, power, power, 0);
    }

    public void strafeForwardRight(double power) {
        setMotorPower(power, 0, 0, power);
    }

    public void strafeBackwardLeft(double power) {
        setMotorPower(-power, 0, 0, -power);
    }

    public void strafeBackwardRight(double power) {
        setMotorPower(0, -power, -power, 0);
    }


    //turning
    public void turnLeft(double power) {
        setMotorPower(-power, power, -power, power);
    }

    public void turnReft(double power) {
        setMotorPower(power, -power, power, -power);
    }




    public void setMotorPower(double frontLeft, double frontRight, double backLeft, double backRight) {
        this.frontLeft.setPower(frontLeft);
        this.frontRight.setPower(frontRight);
        this.backLeft.setPower(backLeft);
        this.backRight.setPower(backRight);
    }

}
