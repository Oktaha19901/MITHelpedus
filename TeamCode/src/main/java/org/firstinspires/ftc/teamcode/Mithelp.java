package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp( name = "Papi's TeleOP", group = "TeleOp")


public class Mithelp extends LinearOpMode {

    public DcMotor FrontRight = null;
    public DcMotor BackRight = null;
    public DcMotor FrontLeft = null;
    public DcMotor BackLeft = null;
    public  DcMotor Arm = null;
    public Servo Claw = null;


    @Override
    public void runOpMode() {

        double speed;

        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        Arm = hardwareMap.get(DcMotor.class, "Arm");
        Claw = hardwareMap.get(Servo.class, "Claw");

        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        waitForStart();
        while (opModeIsActive()) {

            drive.setWeightedDrivePower(
                    new Pose2d(
                                -gamepad1.left_stick_y * 1,
                                gamepad1.left_stick_x * 1,
                                -gamepad1.right_stick_x * 0.8
                    )
            );

            double Power1 = gamepad2.left_stick_y;
            speed = .5;
            Arm.setPower(Power1 * speed);


            if (gamepad2.right_bumper) {
                Claw.setPosition(0.75);
            }
            else if (gamepad2.left_bumper) {
                Claw.setPosition(0.4);
            }




            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("a", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}

