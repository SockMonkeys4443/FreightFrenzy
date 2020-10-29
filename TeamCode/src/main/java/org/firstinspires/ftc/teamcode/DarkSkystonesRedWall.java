package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="\uD83D\uDC8E\uD83D\uDD34\uD83C\uDFF0 Skystones Red Wall", group="workingSkystones")
public class DarkSkystonesRedWall extends SuperDark implements DarkAutonomous {
    @Override
    public void darkInit() {
        initCamera();

    }

    @Override
    public void darkRunning() {
        timer.restart();

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //oldDrive to where all 3 stones fit into view
        CircuitBreakersVuforia.skystonePos skystonePosition;


        //oldDrive to scan skystone

        oldDrive.driveDistance(DeadWheels.armSide, TO_SCAN_DIST, TO_SCAN_POWER, TO_SCAN_TIME);


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
        oldDrive.driveDistance(DeadWheels.grabberSide, distanceSkystone - SKYSTONE_WIDTH + 15, STONE_STRAFE_POWER, STONE_STRAFE_TIME);

        arm.setClaw(false); //opens claw

        //oldDrive forwards to 'eat' skystone
        oldDrive.driveDistance(DeadWheels.armSide, TO_GRAB_DIST, TO_GRAB_POWER, TO_GRAB_TIME);

        arm.setClaw(true);

        sleep(500);

        //drives backwards after picking up the skystone
        oldDrive.driveDistance(DeadWheels.armSide, -BACK_AFTERGRAB_DIST, BACK_AFTERGRAB_POWER, BACK_AFTERGRAB_TIME);

        oldDrive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);
        sleep(250);
        oldDrive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);

        oldDrive.driveDistance(DeadWheels.grabberSide, -REPOSITION_WALL_DIST, REPOSITION_POWER, REPOSITION_TIME);

        //goes under bridge
        oldDrive.driveDistance(DeadWheels.armSide, UNDER_BRIDGE_DIST, UNDER_BRIDGE_POWER, UNDER_BRIDGE_TIME);

        //drives to park, taking into account where we went to grab the skystone
        oldDrive.driveDistance(DeadWheels.armSide, DROP_STONE_DIST -distanceSkystone , DROP_STONE_POWER, DROP_STONE_TIME);

        arm.setClaw(false); //opens claw

        oldDrive.driveDistance(DeadWheels.armSide, -TO_PARK_DIST, TO_PARK_POWER, TO_PARK_TIME);

        stop();

    }
}