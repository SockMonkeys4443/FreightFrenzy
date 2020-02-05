package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="wheel test", group="test")
public class andersonsidea extends SuperDark {


    @Override
    public void darkInit() {
        //
    }

    @Override
    public void darkRunning() {
        //TODO: In the future we need to comment what the auto is doing


        float angle = imuController.getAngle();

        drive.frontLeft.setPower(0.7);
        drive.frontRight.setPower(0.3);
        drive.backLeft.setPower(0.7);
        drive.backRight.setPower(0);

        while (imuController.getAngle() < angle + 45) ;

        drive.frontLeft.setPower(0);
        drive.frontRight.setPower(0.7);
        drive.backLeft.setPower(0.7);
        drive.backRight.setPower(-0.3);

        while (imuController.getAngle() < angle + 90) ;

        drive.frontLeft.setPower(-0.7);
        drive.frontRight.setPower(0);
        drive.backLeft.setPower(0.7);
        drive.backRight.setPower(-0.7);

        while (imuController.getAngle() < angle + 135) ;

        drive.frontLeft.setPower(-0.7);
        drive.frontRight.setPower(-0.3);
        drive.backLeft.setPower(0);
        drive.backRight.setPower(-0.7);


        while (imuController.getAngle() < angle + 180) ;

        stop();

    }
}