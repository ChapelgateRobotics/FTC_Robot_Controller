package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Config
public class DriveOpinits {
    //Defines the objects
    DcMotor leftFront, leftBack, rightFront, rightBack, hornMotor, hanger;
    DcMotorEx droneMotor;
    Servo flipper, grabber, pusher, armServo, dronePusher;

    //Defines the variable double
    OpMode op;
    public static double THRESHOLD = 0.1;
    public static double FLIPPER_INIT = 0.65;
    public static double GRABBER_OPEN = 0.25;
    public static double GRABBER_CLOSED = 0.5;
    public static double GRABBER_INIT = 0.0;
    public static  double droneMotorSpeed = 0.7;
    // positions of the horn.
    public static double HORN_POSITION_1 = 0.5;
    public static double HORN_POSITION_2 = 0.4;
    public static double HORN_POSITION_3 = 0.3;
    public static double DRONE_PUSHER_LAUNCH = 1;
    public static double DRONE_PUSHER_RESET = 0;
    public static double DRONE_MOTOR_VELOCITY = 5000*6.28;
    public static double PUSHER_INIT = HORN_POSITION_1;



    public DriveOpinits(OpMode op) {
        this.op = op;
        this.init();
    }

    public void init() {
        //assigns name to the object in the hardwareMap and inits it.
        //driveTrain
        leftFront = op.hardwareMap.dcMotor.get("leftFront");
        leftBack = op.hardwareMap.dcMotor.get("leftBack");
        rightFront = op.hardwareMap.dcMotor.get("rightFront");
        rightBack = op.hardwareMap.dcMotor.get("rightBack");
        //subAssemblies motors
        //armMotor = op.hardwareMap.dcMotor.get("armMotor");
        hornMotor = op.hardwareMap.dcMotor.get("armMotor");
        hanger = op.hardwareMap.dcMotor.get("hanger");
        droneMotor=(DcMotorEx)op.hardwareMap.dcMotor.get("droneMotor");
        //servos hardware map
        flipper = op.hardwareMap.servo.get("flipper");
        grabber = op.hardwareMap.servo.get("grabber");
        pusher = op.hardwareMap.servo.get("pusher");
        dronePusher = op.hardwareMap.servo.get("dronePusher");


        //Set motors to encoders and directions for mecanum drive.

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set motors for sub assemblies to use encoders
        hornMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hornMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hornMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        droneMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        droneMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        droneMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        hanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hanger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hanger.setDirection(DcMotorSimple.Direction.FORWARD);


        // set servos to init position.
        flipper.setPosition(FLIPPER_INIT);
        grabber.setPosition(GRABBER_INIT);
        pusher.setPosition(PUSHER_INIT);



    }
    public void setGrabberOpen(){

        grabber.setPosition(GRABBER_OPEN);
    }
    public void setGrabberClosed(){

        grabber.setPosition(GRABBER_CLOSED);
    }
    public String getGrabberString() {
        String s = "GRABBER_OPEN: "+GRABBER_OPEN + "\nGRABBER_CLOSED: " + GRABBER_CLOSED;
        return s;
    }
    public void setHornPower(double power){

        hornMotor.setPower(power);
    }

    public void setArmPosition(double position){
        armServo.setPosition(position);
    }
    public void setFlipperPosition(double position){

        flipper.setPosition(position);
    }

    public void setTeleOpDrive(double forward, double strafe, double turn){
        double leftFrontPower = trimPower(forward + strafe + turn);
        double rightFrontPower = trimPower(forward - strafe - turn);
        double leftBackPower = trimPower(forward - strafe + turn);
        double rightBackPower = trimPower(forward + strafe - turn);
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftBack.setPower(leftBackPower);
        rightBack.setPower(rightBackPower);
    }
    public void setPusherPosition (double position) {
        if (position == 1){
            pusher.setPosition(HORN_POSITION_1);
        }
        else if (position == 2) {
            pusher.setPosition(HORN_POSITION_2);
        }
        else if (position == 3) {
            pusher.setPosition(HORN_POSITION_3);
        }
    }
    // not sure what this is doing for us?
    public double trimPower(double Power) {
        if (Math.abs(Power) < THRESHOLD) {
            Power = 0;
        }
        return Power;

    }
    public void setDroneMotorSpeed(){
        droneMotor.setVelocity(DRONE_MOTOR_VELOCITY, AngleUnit.RADIANS);
    }
    public void setDroneMotorZero(){

        droneMotor.setVelocity(0);
    }
    public double getHornMotorPosition(){
        double hornMotorPosition = hornMotor.getCurrentPosition();
        return(hornMotorPosition);
    }

    public void setDronePusherLaunch(){

        dronePusher.setPosition(DRONE_PUSHER_LAUNCH);
    }

    public void setDronePusherReset(){

        dronePusher.setPosition(DRONE_PUSHER_RESET);
    }

}