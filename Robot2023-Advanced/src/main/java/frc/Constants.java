package frc;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class Constants {
    //////////////////////////////////////////////////////////////////
    // Drivetrain Physical
    //////////////////////////////////////////////////////////////////
    static public final double WHEEL_BASE_HALF_WIDTH_M = Units.inchesToMeters(23.75 / 2.0);
    static public final double ROBOT_MASS_kg = UnitUtils.lbsToKg(140);
    static public final double ROBOT_MOI_KGM2 = 1.0 / 12.0 * ROBOT_MASS_kg
            * Math.pow((WHEEL_BASE_HALF_WIDTH_M * 2.2), 2) * 2; // Model moment of intertia as a square slab slightly
                                                                // bigger than wheelbase with axis through center

    // Drivetrain Performance Mechanical limits
    static public final double MAX_FWD_REV_SPEED_MPS = Units.feetToMeters(16.0);
    static public final double MAX_STRAFE_SPEED_MPS = Units.feetToMeters(16.0);
    static public final double MAX_ROTATE_SPEED_RAD_PER_SEC = Units.degreesToRadians(360.0);
    static public final double MAX_TRANSLATE_ACCEL_MPS2 = MAX_FWD_REV_SPEED_MPS / 1.00; // 0-full time of 0.25 second
    static public final double MAX_ROTATE_ACCEL_RAD_PER_SEC_2 = MAX_ROTATE_SPEED_RAD_PER_SEC / .25; // 0-full time of
                                                                                                    // 0.25 second

    // See
    // https://www.swervedrivespecialties.com/products/mk4i-swerve-module?variant=39598777172081
    static public final double WHEEL_GEAR_RATIO = 6.75; // L2 gearing
    static public final double AZMTH_GEAR_RATIO = 12.8;
    static public final double WHEEL_FUDGE_FACTOR = 0.9238; // carpet roughtop scrub factor
    static public final double WHEEL_RADIUS_IN = 4.0 / 2.0 * WHEEL_FUDGE_FACTOR; // four inch diameter wheels -
                                                                                 // https://www.swervedrivespecialties.com/collections/mk4i-parts/products/billet-wheel-4d-x-1-5w-bearing-bore

    // Mechanical mounting offsets of the encoder & magnet within the shaft
    // Must be updated whenever the module is reassembled
    // Procedure:
    // 0 - Put the robot up on blocks.
    // 1 - Reset all these values to 0, deploy code
    // 2 - Pull up dashboard with encoder readings (in radians)
    // 3 - Using a square, twist the modules by hand until they are aligned with the
    // robot's chassis
    // 4 - Read out the encoder readings for each module, put them here
    // 5 - Redeploy code, verify that hte encoder readings are correct as each
    // module is manually rotated
    static public final double FL_ENCODER_MOUNT_OFFSET_RAD = -2.157;
    static public final double FR_ENCODER_MOUNT_OFFSET_RAD = -1.575;
    static public final double BL_ENCODER_MOUNT_OFFSET_RAD = -2.180;
    static public final double BR_ENCODER_MOUNT_OFFSET_RAD = -0.803;

    // Location of vision cameras relative to robot center - currently front and
    // back
    static public final Transform3d robotToFrontCameraTrans = new Transform3d(
            new Translation3d(WHEEL_BASE_HALF_WIDTH_M, 0, 1.0), new Rotation3d(0.0, 0.0, 0.0));
    static public final Transform3d robotToRearCameraTrans = new Transform3d(
            new Translation3d(-1.0 * WHEEL_BASE_HALF_WIDTH_M, 0, 1.0), new Rotation3d(0.0, 0.0, Math.PI));

    //////////////////////////////////////////////////////////////////
    // Electrical
    //////////////////////////////////////////////////////////////////

    // PWM Bank
    // static public final int UNUSED = 0;
    // static public final int UNUSED = 1;
    // static public final int UNUSED = 2;
    // static public final int UNUSED = 3;
    // static public final int UNUSED = 4;
    // static public final int UNUSED = 5;
    // static public final int UNUSED = 6;
    // static public final int UNUSED = 7;
    // static public final int UNUSED = 8;
    // static public final int UNUSED = 9;

    // DIO Bank
    // static public final int FL_AZMTH_ENC_IDX = 0;
    // static public final int FR_AZMTH_ENC_IDX = 1;
    // static public final int BL_AZMTH_ENC_IDX = 2;
    // static public final int BR_AZMTH_ENC_IDX = 3;
    // static public final int UNUSED = 4;
    // static public final int UNUSED = 5;
    // static public final int UNUSED = 6;
    // static public final int UNUSED = 7;
    // static public final int UNUSED = 8;
    // static public final int UNUSED = 9;

    // Analog Bank
    // static public final int UNUSED = 0;
    // static public final int UNUSED = 1;
    // static public final int UNUSED = 2;
    // static public final int UNUSED = 3;

    // CAN Bus Addresses - Motors
    // static public final int RESERVED_DO_NOT_USE = 0; // default for most stuff
    // static public final int RESERVED_DO_NOT_USE = 1; // Rev Power Distribution
    // Hub
    // FIX:
    public static final String CANIVORE_NAME = "rio";
    // CANIVORE_NAME bus for drive train ( RealTalonFX and Cancoders )
    // use "rio" for RoboRio bus
    static public final int FL_WHEEL_MOTOR_CANID = 1;
    static public final int FL_AZMTH_MOTOR_CANID = 2;
    static public final int FR_WHEEL_MOTOR_CANID = 3;
    static public final int FR_AZMTH_MOTOR_CANID = 4;
    static public final int BR_WHEEL_MOTOR_CANID = 5;
    static public final int BR_AZMTH_MOTOR_CANID = 6;
    static public final int BL_WHEEL_MOTOR_CANID = 7;
    static public final int BL_AZMTH_MOTOR_CANID = 8;
    static public final int FL_AZMTH_ENC_IDX = 9;
    // static public final int UNUSED = 10;
    static public final int FR_AZMTH_ENC_IDX = 11;
    static public final int BL_AZMTH_ENC_IDX = 12;
    static public final int BR_AZMTH_ENC_IDX = 13;
    // static public final int UNUSED = 14;
    // static public final int UNUSED = 15;
    // static public final int UNUSED = 16;
    // static public final int UNUSED = 17;

    // Pneumatics Hub
    // static public final int UNUSED = 0;
    // static public final int UNUSED = 1;
    // static public final int UNUSED = 2;
    // static public final int UNUSED = 3;
    // static public final int UNUSED = 4;
    // static public final int UNUSED = 5;
    // static public final int UNUSED = 6;
    // static public final int UNUSED = 7;
    // static public final int UNUSED = 8;
    // static public final int UNUSED = 9;

    // PDP Channels - for current measurement
    // static public final int UNUSED = 0;
    // static public final int UNUSED = 1;
    // static public final int UNUSED = 2;
    // static public final int UNUSED = 3;
    // static public final int UNUSED = 4;
    // static public final int UNUSED = 5;
    // static public final int UNUSED = 6;
    // static public final int UNUSED = 7;
    // static public final int UNUSED = 8;
    // static public final int UNUSED = 9;
    // static public final int UNUSED = 10;
    // static public final int UNUSED = 11;
    // static public final int UNUSED = 12;
    // static public final int UNUSED = 13;
    // static public final int UNUSED = 14;
    // static public final int UNUSED = 15;
    // static public final int UNUSED = 16;
    // static public final int UNUSED = 17;
    // static public final int UNUSED = 18;
    // static public final int UNUSED = 19;

    //////////////////////////////////////////////////////////////////
    // Time-based autonomous Constants
    //////////////////////////////////////////////////////////////////
    public static final double TAXI_DRIVE_TIME_S = 2.2;
    public static final double TAXI_DRIVE_SPEED_MPS = 1.75;

    ////////////////////////////////////////////////////////////////// W
    // Nominal Sample Times
    //////////////////////////////////////////////////////////////////
    public static final double Ts = 0.02;
    public static final double SIM_SAMPLE_RATE_SEC = 0.001;

    //////////////////////////////////////////////////////////////////
    // Field Dimensions
    //////////////////////////////////////////////////////////////////
    static public final double FIELD_WIDTH_M = Units.feetToMeters(27.0);
    static public final double FIELD_LENGTH_M = Units.feetToMeters(54.0);
    static public final Translation2d MAX_ROBOT_TRANSLATION = new Translation2d(FIELD_LENGTH_M, FIELD_WIDTH_M);
    static public final Translation2d MIN_ROBOT_TRANSLATION = new Translation2d(0.0, 0.0);
    // Assumed starting location of the robot. Auto routines will pick their own
    // location and update this.
    public static final Pose2d DFLT_START_POSE = new Pose2d(3, 3, new Rotation2d(0));
    // Expected vision target locations on the field
    // TODO - Use Actual Poses
    static public final Transform3d VISION_FAR_TGT_LOCATION = new Transform3d(
            new Translation3d(FIELD_LENGTH_M, Units.feetToMeters(9.8541), 1.0), new Rotation3d(0, 0, 0));
    static public final Transform3d VISION_NEAR_TGT_LOCATION = new Transform3d(
            new Translation3d(Units.feetToMeters(0), Units.feetToMeters(17.14), 1.0), new Rotation3d(0, 0, Math.PI));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// Derived Constants
    //// - You can reference how these are calculated, but shouldn't
    //// have to change them
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // HELPER ORGANIZATION CONSTANTS
    static public final int FL = 0; // Front Left Module Index
    static public final int FR = 1; // Front Right Module Index
    static public final int BL = 2; // Back Left Module Index
    static public final int BR = 3; // Back Right Module Index
    static public final int NUM_MODULES = 4;

    // Internal objects used to track where the modules are at relative to
    // the center of the robot, and all the implications that spacing has.
    static public final List<Translation2d> robotToModuleTL = Arrays.asList(
            new Translation2d(Constants.WHEEL_BASE_HALF_WIDTH_M, Constants.WHEEL_BASE_HALF_WIDTH_M),
            new Translation2d(Constants.WHEEL_BASE_HALF_WIDTH_M, -Constants.WHEEL_BASE_HALF_WIDTH_M),
            new Translation2d(-Constants.WHEEL_BASE_HALF_WIDTH_M, Constants.WHEEL_BASE_HALF_WIDTH_M),
            new Translation2d(-Constants.WHEEL_BASE_HALF_WIDTH_M, -Constants.WHEEL_BASE_HALF_WIDTH_M));

    static public final List<Transform2d> robotToModuleTF = Arrays.asList(
            new Transform2d(robotToModuleTL.get(FL), new Rotation2d(0.0)),
            new Transform2d(robotToModuleTL.get(FR), new Rotation2d(0.0)),
            new Transform2d(robotToModuleTL.get(BL), new Rotation2d(0.0)),
            new Transform2d(robotToModuleTL.get(BR), new Rotation2d(0.0)));

    static public final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
            robotToModuleTL.get(FL),
            robotToModuleTL.get(FR),
            robotToModuleTL.get(BL),
            robotToModuleTL.get(BR));

}
