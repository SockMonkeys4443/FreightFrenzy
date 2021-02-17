package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleSheeley  {

    DcMotor base;
    Servo toucher;

    double legalPosition = 0.68;
    double touchedPosition = 0.8;

    boolean kidsTouched = false;

    Robot opMode;

    public void init(Robot opMode) {
        this.opMode = opMode;

        base = opMode.hardwareMap.get(DcMotor.class, "WobbleBase");
        base.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        toucher = opMode.hardwareMap.get(Servo.class, "WobbleToucher");
    }

    public void runBase(double power) {
        base.setPower(power);
    }

    public void touchKids() {
        kidsTouched = !kidsTouched;
        if (kidsTouched) {
            toucher.setPosition(touchedPosition);
        } else {
            toucher.setPosition(legalPosition);
        }
    }

}
