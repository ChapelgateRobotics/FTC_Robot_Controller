package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DriverControlled", group = "CenterStage")
public class TelopDC extends OpMode {
    //Defines the objects
    DcMotor leftFront, leftBack, rightFront, rightBack, armMotor, droneMotor, hanger;
    Servo flipper, grabber, pusher;

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


    }

    @Override
    public void loop() {

    }
}
