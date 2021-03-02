package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleSheeley  {

    Servo base;
    Servo toucher;

    double legalPosition = 0.54;
    double touchedPosition = 0.84;

    boolean kidsTouched = false;

    Robot opMode;

    public void init(Robot opMode) {
        this.opMode = opMode;

        base = opMode.hardwareMap.get(Servo.class, "WobbleBase");

        toucher = opMode.hardwareMap.get(Servo.class, "WobbleToucher");
    }

    public void prepareToTouch() {
        toucher.setPosition(touchedPosition);
    }

    public void disengage() {
        toucher.setPosition(legalPosition);
    }

    public void setTouchPosition(double position) {
        base.setPosition(position);
    }

    public double findSheeley() {
        return toucher.getPosition();
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
