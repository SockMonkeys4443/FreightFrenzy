package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


public abstract class OldRobot extends LinearOpMode {

    OldMechDrive oldMechDrive = new OldMechDrive();
    OldIntake oldIntake = new OldIntake();
    Camera camera = new Camera();
    WobbleSheeley wobbleSheeley = new WobbleSheeley();
    //Shooter shooter = new Shooter();
    DcMotor conveyorMotor;
    IMUController imuController = new IMUController();

    //Timer
    public ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    Timer timer = new Timer(runtime);

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing Robot...");
        telemetry.update();

        oldMechDrive.init(this);
        oldIntake.init(this);
        camera.init(this);
        wobbleSheeley.init(this);
        //shooter.init(this);

        conveyorMotor = hardwareMap.get(DcMotor.class, "conveyor");
        conveyorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        conveyorMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        imuController.init(hardwareMap, telemetry);
        while(!imuController.imu.isGyroCalibrated() && !isStopRequested()) {
            sleep(50);
            idle();
        }
        telemetry.addData("IMU calib status", imuController.imu.getCalibrationStatus().toString());

        telemetry.addData("Status", "Initializing OpMode...");
        telemetry.update();

        robotInit();

        telemetry.addData("Status", "Initialization Complete!");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
            robotRunning();
        }
    }

    public abstract void robotInit();
    public abstract void robotRunning();

}
