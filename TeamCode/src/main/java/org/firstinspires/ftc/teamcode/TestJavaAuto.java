package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp(name = "TestJavaAuto")
public class TestJavaAuto extends OpMode {

    DcMotor motor;
    @Override
    public void init() {

        telemetry.addData("Init", "is successssss");
        telemetry.update();
    }

    @Override
    public void loop() {

    }
}
