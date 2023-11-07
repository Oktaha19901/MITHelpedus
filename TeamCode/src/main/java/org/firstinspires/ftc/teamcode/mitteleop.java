package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@TeleOp( name = "Papi's TeleOP", group = "TeleOp")


public class mitteleop extends LinearOpMode {

    public DcMotor FrontRight = null;
    public DcMotor BackRight = null;
    public DcMotor FrontLeft = null;
    public DcMotor BackLeft = null;
    public DcMotor Arm = null;
    public Servo LeftHand = null;
    public Servo RightHand = null;


    @Override
    public void runOpMode(){

        double speed;

        FrontRight = hardwareMap.get(DcMotor.class, "FR");
        FrontLeft = hardwareMap.get(DcMotor.class, "FL");
        BackRight = hardwareMap.get(DcMotor.class, "BR");
        BackLeft = hardwareMap.get(DcMotor.class, "BL");
        Arm = hardwareMap.get(DcMotor.class, "ARM");
        LeftHand = hardwareMap.get(Servo.class, "LEFT");
        RightHand = hardwareMap.get(Servo.class, "RIGHT");

        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();
        while (opModeIsActive()){

            double leftPower;
            double rightPower;
            double armRaise;

            double drive = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double armPower = gamepad2.right_stick_y;


            leftPower = Range.clip(drive - turn, -1.0, 1.0);
            rightPower = Range.clip(-drive - turn, -1.0, 1.0);
            armRaise = Range.clip(armPower, -1,1);

            if (gamepad2.right_bumper){
                LeftHand.setPosition(.98);
                RightHand.setPosition(.02);
            }
            else if (gamepad2.left_bumper){
                LeftHand.setPosition(.86);
                RightHand.setPosition(.09);

            }



            if (gamepad1.left_bumper) {
                FrontLeft.setPower(1);
                BackLeft.setPower(-1);
                FrontRight.setPower(1);
                BackRight.setPower(-1);
            }
            else if (gamepad1.right_bumper){
                FrontLeft.setPower(-1);
                BackLeft.setPower(1);
                FrontRight.setPower(-1);
                BackRight.setPower(1);
            }
            else {
                FrontLeft.setPower(0);
                BackLeft.setPower(0);
                FrontRight.setPower(0);
                BackRight.setPower(0);
            }
            speed = 1 ;




            FrontLeft.setPower(leftPower*speed);
            BackLeft.setPower(leftPower*speed);
            FrontRight.setPower(rightPower*speed);
            BackRight.setPower(rightPower*speed);
            Arm.setPower(armRaise*speed);






        }
    }
}

