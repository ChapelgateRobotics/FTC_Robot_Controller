package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DriverControlled", group = "CenterStage")
public class TelopDC extends OpMode {
    //Defines the objects
    DcMotor leftFront, leftBack, rightFront, rightBack, armMotor, droneMotor, hanger;
    Servo flipper, grabber, pusher;
    //Defines the variable double
    final double FLIPPER_INIT = 0.0;
    final double GRABBER_INIT = 0.0;
    final double Pusher_INIT = 0.0;

    @Override
    public void init() {
        //assigns name to the object in the hardwareMap and inits it.
        //driveTrain
        leftFront=hardwareMap.dcMotor.get("leftFront");
        leftBack=hardwareMap.dcMotor.get("leftBack");
        rightFront=hardwareMap.dcMotor.get("rightFront");
        rightBack=hardwareMap.dcMotor.get("rightBack");
        //subAssemblies
        armMotor=hardwareMap.dcMotor.get("armMotor");
        droneMotor=hardwareMap.dcMotor.get("droneMotor");
        hanger=hardwareMap.dcMotor.get("hanger");
        //servos
        flipper=hardwareMap.servo.get("flipper");
        grabber=hardwareMap.servo.get("grabber");
        pusher=hardwareMap.servo.get("pusher");

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
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        droneMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        droneMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        droneMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        hanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hanger.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hanger.setDirection(DcMotorSimple.Direction.FORWARD);

        // set servos to init position.
        flipper.setPosition(FLIPPER_INIT);
        grabber.setPosition(GRABBER_INIT);
        pusher.setPosition(Pusher_INIT);
    }

    @Override
    public void loop() {

    }
}
