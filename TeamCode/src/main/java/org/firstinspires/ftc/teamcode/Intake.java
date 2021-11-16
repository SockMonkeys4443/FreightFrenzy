package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    DcMotor leftIntake;
    DcMotor rightIntake;

    Robot opMode;

    void init(Robot opMode) {
        this.opMode = opMode;

        leftIntake = opMode.hardwareMap.get(DcMotor.class, "left_intake");
        rightIntake = opMode.hardwareMap.get(DcMotor.class, "right_intake");
    }

    public void setPower(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

}
