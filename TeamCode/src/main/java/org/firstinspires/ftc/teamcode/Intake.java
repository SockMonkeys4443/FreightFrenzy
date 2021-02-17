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
        intakeMotor.setPower(0.5);
    }

    public void stopIntake() {
        intakeMotor.setPower(0);
    }
}
