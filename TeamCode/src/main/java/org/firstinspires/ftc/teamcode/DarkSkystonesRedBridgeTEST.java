package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TEST Skystones Red Bridge", group="workingSkystones")
public class DarkSkystonesRedBridgeTEST extends SuperDark implements DarkAutonomous  {
    @Override
    public void darkInit() {
        initCamera();

    }

    @Override
    public void darkRunning() {
        timer.restart();

        telemetryEnabled = true;

        //there are 29 inches between where the closest edge of the robot starts to the stones

        //drive to where all 3 stones fit into view
        CircuitBreakersVuforia.skystonePos skystonePosition;


        //drive to scan skystone

        if(telemetryEnabled) telemetry.speak("Driving to scan skystone");
        drive.stableDriveDistance(DeadWheels.armSide, TO_SCAN_DIST, TO_SCAN_POWER, TO_SCAN_TIME);


        if(telemetryEnabled) telemetry.speak("Scanning skystone");
        skystonePosition = camera.circuitScan(true);
        telemetry.addData("Skystone position:", skystonePosition );
        telemetry.update();

        arm.gotoGrabLocation(ARM_DEPLOY_POWER);


        float distanceSkystone;

        if(skystonePosition == CircuitBreakersVuforia.skystonePos.LEFT) {distanceSkystone = SKYSTONE_WIDTH;}
        else if (skystonePosition == CircuitBreakersVuforia.skystonePos.RIGHT) {distanceSkystone = -SKYSTONE_WIDTH;}
        else {distanceSkystone = 0;}



        if(telemetryEnabled) telemetry.speak("Strafing to the skystone");
        //strafe to face skystone
        //minus 5 to compensate for the depot placement on this side
        drive.stableDriveDistance(DeadWheels.grabberSide, distanceSkystone - SKYSTONE_WIDTH + 5, STONE_STRAFE_POWER, STONE_STRAFE_TIME);

        arm.setClaw(false); //opens claw


        if(telemetryEnabled) telemetry.speak("Eating skystone");
        //drive forwards to 'eat' skystone
        drive.stableDriveDistance(DeadWheels.armSide, TO_GRAB_DIST, TO_GRAB_POWER, TO_GRAB_TIME);

        arm.setClaw(true);

        sleep(500);


        if(telemetryEnabled) telemetry.speak("Driving back to turn");
        //drives backwards after picking up the skystone
        drive.stableDriveDistance(DeadWheels.armSide, -BACK_AFTERGRAB_DIST, BACK_AFTERGRAB_POWER, BACK_AFTERGRAB_TIME);


        if(telemetryEnabled) telemetry.speak("Turning");
        drive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);
        sleep(250);
        drive.newTurnTo(-TURN_TARGET_ANGLE, TURN_POWER, TURN_TIME);

        if(telemetryEnabled) telemetry.speak("Repositioning to be against bridge");
        drive.stableDriveDistance(DeadWheels.grabberSide, -REPOSITION_BRIDGE_DIST, REPOSITION_POWER, REPOSITION_TIME);


        if(telemetryEnabled) telemetry.speak("Driving under bridge");
        //goes under bridge
        drive.stableDriveDistance(DeadWheels.armSide, UNDER_BRIDGE_DIST, UNDER_BRIDGE_POWER, UNDER_BRIDGE_TIME);

        if(telemetryEnabled) telemetry.speak("Going to drop stone");
        //drives to park, taking into account where we went to grab the skystone
        drive.stableDriveDistance(DeadWheels.armSide, DROP_STONE_DIST -distanceSkystone , DROP_STONE_POWER, DROP_STONE_TIME);

        arm.setClaw(false); //opens claw

        if(telemetryEnabled) telemetry.speak("Who's Joe");
        drive.stableDriveDistance(DeadWheels.armSide, -TO_PARK_DIST, TO_PARK_POWER, TO_PARK_TIME);

        if(telemetryEnabled) telemetry.speak("Joe Mama");


        stop();

    }
}