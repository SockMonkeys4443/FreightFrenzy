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

        //drive to skystone

        //drive to scoop skystone

        drive.driveDistance(DeadWheels.armSide, TO_SCAN_DIST, 0.5, 3);

        skystonePosition = camera.circuitScan(true);
        telemetry.addData("Skystone position:", skystonePosition );
        telemetry.update();

        arm.gotoGrabLocation(0.4);

        float distanceSkystone;

        if(skystonePosition == CircuitBreakersVuforia.skystonePos.LEFT) {distanceSkystone = 20;}
        else if (skystonePosition == CircuitBreakersVuforia.skystonePos.RIGHT) {distanceSkystone = -20;}
        else {distanceSkystone = 0;}

        //strafe to face skystone
        drive.driveDistance(DeadWheels.grabberSide, distanceSkystone-SKYSTONE_WIDTH-10, 0.35, 3);

        arm.setClaw(false); //opens claw

        //drive forwards to 'eat' skystone
        drive.driveDistance(DeadWheels.armSide, TO_GRAB_DIST, TO_GRAB_POWER, TO_GRAB_TIME);

        arm.setClaw(true);

        sleep(2000);

        //drives backwards after picking up the skystone
        drive.driveDistance(DeadWheels.armSide, -BACK_AFTERGRAB_DIST, BACK_AFTERGRAB_POWER, BACK_AFTERGRAB_TIME);

        drive.newTurnTo(-90, 1, 3);
        sleep(250);
        drive.newTurnTo(-90, 1, 3);

        drive.driveDistance(DeadWheels.grabberSide, REPOSITION_BRIDGE_DIST, 0.6, 3);

        //drives to deliver the skystone under the bridge
        drive.driveDistance(DeadWheels.armSide, DROP_STONE_DIST -distanceSkystone , DROP_STONE_POWER, DROP_STONE_TIME);

        //arm.goToAngle(20f,0.5);
        arm.setClaw(false); //opens claw

        drive.newTurnTo(-180f, 0.75, 3);

        drive.driveDistance(DeadWheels.armSide, -TILE_SIZE * 1.5f, 0.75, 3);

        drive.driveDistance(DeadWheels.grabberSide, -TILE_SIZE * 5f,0.75,5);

        stop();

    }
}


