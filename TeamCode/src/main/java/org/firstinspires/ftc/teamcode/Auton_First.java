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
                        .waitSeconds(0.2)
                        .turn(Math.toRadians(-90))
                        .strafeTo(new Vector2d(6,-35))
                        .build()
        );

    }
}
