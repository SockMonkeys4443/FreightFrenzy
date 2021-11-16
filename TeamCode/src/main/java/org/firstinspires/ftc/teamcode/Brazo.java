package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Brazo {

    DcMotor brazoMotor;

    Servo scoop;

    Robot opMode;

    void init(Robot opMode) {
        this.opMode = opMode;

        brazoMotor = opMode.hardwareMap.get(DcMotor.class, "brazo");
        scoop = opMode.hardwareMap.get(Servo.class, "scoop");

        brazoMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        brazoMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setPower(double power) {
        brazoMotor.setPower(power);
    }

    public void setScoopPosition(double value) {
        scoop.setPosition(value);
    }

}
