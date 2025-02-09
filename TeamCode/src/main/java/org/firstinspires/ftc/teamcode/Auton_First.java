package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="First RR")
public class Auton_First extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        waitForStart();
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0,0,0))
                        .lineToX(28)
                        .strafeTo(new Vector2d(28,-36))
                        .strafeTo(new Vector2d(52, -36))
                        .strafeTo(new Vector2d(52,-47))
                        .strafeTo(new Vector2d(5,-47))
                        .strafeTo(new Vector2d(10,-47))
                        .waitSeconds(0.3)
                        .turn(Math.toRadians(-90))
                        .waitSeconds(0.3)
                        .strafeTo(new Vector2d(10,-25))
                        .strafeTo(new Vector2d(3.5,-25))
                        .strafeTo(new Vector2d(3.5,-36))
                        .waitSeconds(0.3)
                        .turn(Math.toRadians(90))
                        .waitSeconds(0.3)
                        .strafeTo(new Vector2d(24,3))
                        .setReversed(true)
                        .strafeTo(new Vector2d(3,-36))
                        .waitSeconds(0.2)
                        .setReversed(false)
                        .turn(Math.toRadians(-90))
                        .strafeTo(new Vector2d(3.2,-36)) //placeholder
                        .turn(Math.toRadians(90))
                        .strafeTo(new Vector2d(24,-5))
                        .waitSeconds(0.2)
                        .setReversed(true)
                        .strafeTo(new Vector2d(3,-50))
                        .build()
        );

    }
}
