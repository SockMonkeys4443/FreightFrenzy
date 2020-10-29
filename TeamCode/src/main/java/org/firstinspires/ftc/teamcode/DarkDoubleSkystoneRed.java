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

        telemetryEnabled = true;

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //oldDrive to where all 3 stones fit into view
        CircuitBreakersVuforia.skystonePos skystonePosition;


        //oldDrive to scan skystone

        oldDrive.driveDistance(DeadWheels.armSide, TO_SCAN_DIST, TO_SCAN_POWER, TO_SCAN_TIME);

        telemetry.speak("Scanning");

        skystonePosition = camera.circuitScan(true);
        telemetry.addData("Skystone position:", skystonePosition );
        telemetry.update();

        arm.gotoGrabLocation(ARM_DEPLOY_POWER);


        float distanceSkystone;

        if(skystonePosition == CircuitBreakersVuforia.skystonePos.LEFT) {distanceSkystone = SKYSTONE_WIDTH; telemetry.speak("Left"); }
        else if (skystonePosition == CircuitBreakersVuforia.skystonePos.RIGHT) {distanceSkystone = -SKYSTONE_WIDTH; telemetry.speak("Right"); }
        else {distanceSkystone = 0; telemetry.speak("Center"); }


        telemetry.speak("Moving to skystone");

        //strafe to face skystone
        //minus 5 to compensate for the depot placement on this side
        oldDrive.driveDistance(DeadWheels.grabberSide, distanceSkystone-SKYSTONE_WIDTH - 20, STONE_STRAFE_POWER, STONE_STRAFE_TIME);

        arm.setClaw(false); //opens claw

        telemetry.speak("Eating skystone");

        //oldDrive forwards to 'eat' skystone
        oldDrive.driveDistance(DeadWheels.armSide, TO_GRAB_DIST, TO_GRAB_POWER, TO_GRAB_TIME);

        arm.setClaw(true);
        telemetry.speak("yum");


        sleep(500);

        //drives backwards after picking up the skystone
        oldDrive.driveDistance(DeadWheels.armSide, -BACK_AFTERGRAB_DIST, BACK_AFTERGRAB_POWER, BACK_AFTERGRAB_TIME);

        telemetry.speak("turning");

        oldDrive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);
        sleep(250);
        oldDrive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);

        telemetry.speak("Driving under bridge");
        oldDrive.driveDistance(DeadWheels.armSide, UNDER_BRIDGE_DIST, UNDER_BRIDGE_POWER, UNDER_BRIDGE_TIME);

        telemetry.speak("Lifting arm");
        arm.goToAngle(-50f, 0.7);


        //drives to deliver the skystone under the bridge
        oldDrive.driveDistance(DeadWheels.armSide, DROP_STONE_DIST, DROP_STONE_POWER, DROP_STONE_TIME);

        telemetry.speak("dropping stone");
        //arm.goToAngle(20f,0.5);
        arm.setClaw(false); //opens claw

        //oldDrive.newTurnTo(-180f, 0.75, 3);

        telemetry.speak("moving to center");

        oldDrive.driveDistance(DeadWheels.grabberSide, TILE_SIZE * 3.2f, 0.75, 3);

        telemetry.speak("crossing neutral bridge");

        oldDrive.driveDistance(DeadWheels.armSide, -TILE_SIZE * 5f,0.75,5);

        telemetry.speak("joebama");
        stop();

    }
}


