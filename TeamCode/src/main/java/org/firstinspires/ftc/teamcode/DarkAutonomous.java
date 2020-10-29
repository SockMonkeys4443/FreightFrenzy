package org.firstinspires.ftc.teamcode;

public interface DarkAutonomous {
    //Jordan you don't need to make the variables final, and  THIS_LOOKS_TERRIBLE_I_HATE_IT
    float SKYSTONE_WIDTH = 20;
    float TILE_SIZE = 60;

    /** SKYSTONES SIDE BELOW **/

    //oldDrive grabberSide to scan the stones
    float TO_SCAN_DIST = 30;
    double TO_SCAN_POWER = 0.5;
    float TO_SCAN_TIME = 3;

    //deploy the arm to grab
    double ARM_DEPLOY_POWER = 0.8;

    //strafe to face the detected skystone
    double STONE_STRAFE_POWER = 0.35;
    float STONE_STRAFE_TIME = 3;

    //oldDrive grabberSide to grab skystone
    float TO_GRAB_DIST = 45;
    double TO_GRAB_POWER = 0.5;
    float TO_GRAB_TIME = 3;

    //oldDrive backwards behind the bridge, with the stone
    float BACK_AFTERGRAB_DIST = 20;
    double BACK_AFTERGRAB_POWER = 0.5;
    float BACK_AFTERGRAB_TIME = 3;

    //turn with the stone
    float TURN_TARGET_ANGLE = 90;
    double TURN_POWER = 0.9;
    float TURN_TIME = 3;

    //reposition after turning to slide between either the bridge or our alliance partner
    float REPOSITION_BRIDGE_DIST = 20;
    float REPOSITION_WALL_DIST = 33;
    double REPOSITION_POWER = 0.8;
    float REPOSITION_TIME = 4;

    //oldDrive armSide to clear the bridge before lifting the arm
    float UNDER_BRIDGE_DIST = 125;
    double UNDER_BRIDGE_POWER = 1;
    float UNDER_BRIDGE_TIME = 4;

    //oldDrive grabberSide to drop the stone, driving just far enough to cross the bridge line
    float DROP_STONE_DIST = 55;
    double DROP_STONE_POWER = 1;
    float DROP_STONE_TIME = 4;

    //after dropping, oldDrive back to park
    float TO_PARK_DIST = 55;
    double TO_PARK_POWER = 0.9;
    float TO_PARK_TIME = 4;

    /** FOUNDATION SIDE BELOW **/

}
