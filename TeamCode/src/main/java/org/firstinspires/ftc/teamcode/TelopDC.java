package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DriveOpinits.FLIPPER_INIT;
import static org.firstinspires.ftc.teamcode.DriveOpinits.THRESHOLD;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp(name = "TelopDC", group = "robot")

public class TelopDC extends OpMode {
    DriveOpinits myBot;
    boolean GRABBER_OPEN = true;
    boolean B_AVAILABLE = true;
    boolean X_AVAILABLE = true;
    boolean Y_AVAILABLE = true;
    boolean A_AVAILABLE = true;


    //check to see what servo values we actually want for forward and backward arm position
    public static double ARM_BACKWARD = 0.98;
    public static double ARM_FORWARD = 0.02;

    //check to see what servo values we actually want for up and down grabber positions
    public static double FLIPPER_UP = FLIPPER_INIT;
    public static double FLIPPER_DOWN= 0.98;

    public static double HORN_POSITION = 1;


    @Override

    public void init() {

        myBot = new DriveOpinits(this);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override

    public void loop() {
        setTeleOpDrive();
        setTeleOpFlipper();
        setTeleOpGrabber();
        setTeleOpHorn();
        setTeleOpArm();
        setTeleOpLauncher();
        setTeleOpPusher();
        setTeleOpDronePusher();
    }


    // pulled drive code in from PowerPlay setDrive()
    public void setTeleOpDrive() {
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x / 2;
        myBot.setTeleOpDrive(forward, strafe, turn);

    }

    public void setTeleOpGrabber() {
        if (gamepad2.b && B_AVAILABLE) {
            if (GRABBER_OPEN) {
                myBot.setGrabberClosed();
                GRABBER_OPEN = false;
            } else {
                myBot.setGrabberOpen();
                GRABBER_OPEN = true;

            }
            B_AVAILABLE = false;
        } else if (!gamepad2.b && !B_AVAILABLE){
            B_AVAILABLE = true;
        }
        telemetry.addData("Grabber Values: ", myBot.getGrabberString());
    }



    public void setTeleOpFlipper() {
        if (gamepad2.a && A_AVAILABLE) {
            myBot.setFlipperPosition(FLIPPER_UP);
            A_AVAILABLE = false;
        } else if(!gamepad2.a && !A_AVAILABLE) {
            A_AVAILABLE = true;
        }
        if (gamepad2.y && !Y_AVAILABLE){
            myBot.setFlipperPosition(FLIPPER_DOWN);
            Y_AVAILABLE = false;
        } else if (!gamepad2.a && !Y_AVAILABLE){
            Y_AVAILABLE = true;
        }
    }


    public void setTeleOpHorn() {
        myBot.setHornPower(-gamepad2.left_stick_y);
        telemetry.addData("Horn position: ", myBot.getHornMotorPosition());

    }

    public void setTeleOpArm() {
        if (gamepad2.dpad_down) {
            myBot.setArmPosition(ARM_BACKWARD);
        } else if (gamepad2.dpad_up) {
            myBot.setArmPosition(ARM_FORWARD);
        }

    }

    public void setTeleOpLauncher(){
        if(gamepad2.left_trigger>THRESHOLD) {
            myBot.setDroneMotorSpeed();
        }
        else{
            myBot.setDroneMotorZero();

        }

    }

    public void setTeleOpDronePusher(){
        if(gamepad2.right_trigger>THRESHOLD){
            myBot.setDronePusherLaunch();
        }
        else{
            myBot.setDronePusherReset();
        }
    }

    public void setTeleOpPusher() {
        if (gamepad2.x && X_AVAILABLE) {
            if (HORN_POSITION == 3) {
                HORN_POSITION = 1;
            } else {
                HORN_POSITION = HORN_POSITION + 1;
            }
            myBot.setPusherPosition(HORN_POSITION);
            X_AVAILABLE = false;
        } else {
            X_AVAILABLE = true;
        }

        telemetry.addData("Horn position: ", HORN_POSITION);

    }
}

