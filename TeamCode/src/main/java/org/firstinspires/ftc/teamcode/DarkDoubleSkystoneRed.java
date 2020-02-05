package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Double Skystones Red", group="test")
public class DarkDoubleSkystoneRed extends SuperDark implements DarkAutonomous {
    @Override
    public void darkInit() {
        initCamera();

    }

    @Override
    public void darkRunning() {
        timer.restart();

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //drive to where all 3 stones fit into view
        CircuitBreakersVuforia.skystonePos skystonePosition;


        //drive to scan skystone

        drive.driveDistance(DeadWheels.armSide, TO_SCAN_DIST, TO_SCAN_POWER, TO_SCAN_TIME);


        skystonePosition = camera.circuitScan(true);
        telemetry.addData("Skystone position:", skystonePosition );
        telemetry.update();


        arm.gotoGrabLocation(ARM_DEPLOY_POWER);


        float distanceSkystone;

        if(skystonePosition == CircuitBreakersVuforia.skystonePos.LEFT) {distanceSkystone = SKYSTONE_WIDTH;}
        else if (skystonePosition == CircuitBreakersVuforia.skystonePos.RIGHT) {distanceSkystone = -SKYSTONE_WIDTH;}
        else {distanceSkystone = 0;}


        //strafe to face skystone
        //minus 5 to compensate for the depot placement on this side
        drive.driveDistance(DeadWheels.grabberSide, distanceSkystone-SKYSTONE_WIDTH - 20, STONE_STRAFE_POWER, STONE_STRAFE_TIME);

        arm.setClaw(false); //opens claw

        //drive forwards to 'eat' skystone
        drive.driveDistance(DeadWheels.armSide, TO_GRAB_DIST, TO_GRAB_POWER, TO_GRAB_TIME);

        arm.setClaw(true);

        sleep(500);

        //drives backwards after picking up the skystone
        drive.driveDistance(DeadWheels.armSide, -BACK_AFTERGRAB_DIST, BACK_AFTERGRAB_POWER, BACK_AFTERGRAB_TIME);

        drive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);
        sleep(250);
        drive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);

        drive.driveDistance(DeadWheels.armSide, UNDER_BRIDGE_DIST, UNDER_BRIDGE_POWER, UNDER_BRIDGE_TIME);
        arm.goToAngle(50f, 0.7);

        //drives to deliver the skystone under the bridge
        drive.driveDistance(DeadWheels.armSide, DROP_STONE_DIST, DROP_STONE_POWER, DROP_STONE_TIME);

        //arm.goToAngle(20f,0.5);
        arm.setClaw(false); //opens claw

        //drive.newTurnTo(-180f, 0.75, 3);

        drive.driveDistance(DeadWheels.grabberSide, -TILE_SIZE * 1.8f, 0.75, 3);

        drive.driveDistance(DeadWheels.armSide, -TILE_SIZE * 5f,0.75,5);

        stop();

    }
}


