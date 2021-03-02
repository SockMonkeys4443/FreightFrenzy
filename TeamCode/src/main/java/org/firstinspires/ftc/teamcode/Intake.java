package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private DcMotor intakeMotor;

    Robot opMode;

    void init(Robot opMode) {
        this.opMode = opMode;

        intakeMotor = opMode.hardwareMap.get(DcMotor.class,"intake");

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void runIntake() {
        intakeMotor.setPower(1);
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
    }

    public void reverseIntake() {
        intakeMotor.setPower(-1);
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }
}
