package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Line;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="First RR")
public class Auton_First extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //Init Materials
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        //Linear Slide Init
        DcMotor LinearSlide = hardwareMap.get(DcMotor.class, "LinearSlide");
        LinearSlide.setDirection(DcMotor.Direction.FORWARD);

        //Servos Init
        Servo joint = hardwareMap.servo.get("joint"); //The one that breaks lol
        Servo joint2 = hardwareMap.servo.get("joint2"); //Up and Down movement for clipping
        Servo leftClaw = hardwareMap.servo.get("leftClaw"); //Grabbing specimen
        joint.setDirection(Servo.Direction.FORWARD);
        joint.setPosition(0.62);
        joint2.setDirection(Servo.Direction.REVERSE);
        joint2.setPosition(0.9);
        leftClaw.setDirection(Servo.Direction.REVERSE);
        leftClaw.setPosition(0.8);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0.442))
                        .stopAndAdd(new ServoJoint2(joint2,0.5)) //Grab
                        .waitSeconds(0.25) //Making sure the linear slide starts going up
                        .splineToConstantHeading(new Vector2d(28, 0), 0)
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        .stopAndAdd(new ServoJoint2(joint2,0.9)) //Clip the first specimen
                        .waitSeconds(0.8)
                        .stopAndAdd(new leftClawMotion(leftClaw,0.3)) //Grab
                        //Put up the preloaded specimen
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, -0.15209))
                        .setTangent(180)
                        .splineToConstantHeading(new Vector2d(28,-40), 180)
                        .splineToConstantHeading(new Vector2d(65,-40), 180)
                        .splineToConstantHeading(new Vector2d(55,-50), 180)
                        .splineToConstantHeading(new Vector2d(1,-50), 180)
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        .splineToConstantHeading(new Vector2d(20,-53), 180)
                        .turn(Math.toRadians(-180))
                        .stopAndAdd(new ServoJoint2(joint2,0.5))
                        .waitSeconds(0.2)
                        .stopAndAdd(new ServoJoint(joint,0.93))
                        .waitSeconds(0.2)
                        .splineToConstantHeading(new Vector2d(10,-53), 180)
                        .stopAndAdd(new leftClawMotion(leftClaw,0.8))
                        .waitSeconds(0.2)
                        .stopAndAdd(new ServoJoint(joint,0.62))
                        .turn(Math.toRadians(-180))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0.37))
                        .strafeTo(new Vector2d(15, 5))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        .stopAndAdd(new ServoJoint2(joint2,0.9)) //Clip the first specimen
                        .waitSeconds(0.8)
                        .stopAndAdd(new leftClawMotion(leftClaw,0.3)) //Grab
                        .setReversed(true)
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, -0.27))
                        .strafeTo(new Vector2d(10, -53))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        .turn(Math.toRadians(-180))
                        .stopAndAdd(new ServoJoint2(joint2,0.5))
                        .waitSeconds(0.3)
                        .stopAndAdd(new ServoJoint(joint,0.93))
                        .waitSeconds(0.4)
                        .stopAndAdd(new leftClawMotion(leftClaw,0.8))
                        .waitSeconds(0.3)
                        .stopAndAdd(new ServoJoint(joint,0.62))
                        .turn(Math.toRadians(-180))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0.37))
                        .strafeTo(new Vector2d(15, 3))
                        .stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        .stopAndAdd(new ServoJoint2(joint2,0.9)) //Clip the first specimen
                        .waitSeconds(0.8)
                        .stopAndAdd(new leftClawMotion(leftClaw,0.3)) //Grab
                        .setReversed(true)
                        .strafeTo(new Vector2d(3, -53))
                        //.stopAndAdd(new ServoJoint2(joint2,0.5))
                        //.waitSeconds(0.3)
                        //.stopAndAdd(new ServoJoint(joint,0.925))
                        //.waitSeconds(0.8)
                        //.splineToConstantHeading(new Vector2d(10,-53), 180)
                        //.waitSeconds(0.2)
                        //.stopAndAdd(new leftClawMotion(leftClaw,0.8))
                        //.waitSeconds(0.5)
                        //.stopAndAdd(new ServoJoint(joint,0.62))
                        //.waitSeconds(0.5)
                        //.turn(Math.toRadians(-180))
                        //.stopAndAdd(new LinearSlideMotion(LinearSlide, 0.37))
                        //.strafeTo(new Vector2d(18, 5))
                        //.stopAndAdd(new LinearSlideMotion(LinearSlide, 0))
                        //.stopAndAdd(new ServoJoint2(joint2,0.9)) //Clip the first specimen
                        //.waitSeconds(1)
                        //.stopAndAdd(new leftClawMotion(leftClaw,0.3)) //Grab
                        //.waitSeconds(0.5)
                        .build()
                );

    }
    public static class LinearSlideMotion implements Action {
        DcMotor LinearSlide;
        double power;

        public LinearSlideMotion(DcMotor l, double p) {
            this.LinearSlide = l;
            this.power = p;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            LinearSlide.setPower(power);
            return false;
        }
    }

    public static class ServoJoint implements Action {
        Servo joint;
        double position;

        public ServoJoint(Servo j, double p) {
            this.joint = j;
            this.position = p;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            joint.setPosition(position);
            return false;
        }
    }

    public static class ServoJoint2 implements Action {
        Servo joint2;
        double position;

        public ServoJoint2(Servo j2, double p) {
            this.joint2 = j2;
            this.position = p;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            joint2.setPosition(position);
            return false;
        }
    }

    public static class leftClawMotion implements Action {
        Servo leftClaw;
        double position;

        public leftClawMotion(Servo lc, double p) {
            this.leftClaw = lc;
            this.position = p;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            leftClaw.setPosition(position);
            return false;
        }
    }
}