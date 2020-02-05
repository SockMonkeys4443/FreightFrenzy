package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Encoder Test", group="test")
public class EncoderTest extends LinearOpMode {
    DeadWheels deadWheels = new DeadWheels();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        deadWheels.init(hardwareMap);

        waitForStart();
        runtime.reset();


        while(opModeIsActive()) {
            telemetry.addData("Encoder:",deadWheels.getTicks(deadWheels.grabberSide));
            telemetry.addData("CM Travelled:", deadWheels.getCM(deadWheels.grabberSide));
            telemetry.addData("Rotations:",deadWheels.getRotations(deadWheels.grabberSide));
            telemetry.update();
        }
    }
}
