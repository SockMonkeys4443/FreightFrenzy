package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DuckyWheel {

    DcMotor duckMotor;

    Robot opMode;

    void init(Robot opMode) {
        this.opMode = opMode;

        duckMotor = opMode.hardwareMap.get(DcMotor.class, "ducky_wheel");
    }

    public void setPower(double power) {
        duckMotor.setPower(power);
    }
}
