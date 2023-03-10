package frc.robot;

import frc.lib.Signal.SignalUtils;
import frc.lib.Signal.Annotations.Signal;
import frc.lib.Webserver2.Webserver2;
import frc.lib.Webserver2.DashboardConfig.DashboardConfig;
import frc.lib.Webserver2.DashboardConfig.SwerveStateTopicSet;
import frc.robot.Autonomous.Autonomous;
import frc.robot.Drivetrain.DrivetrainPoseEstimator;


public class Dashboard {

    @Signal(name = "db_visionTargetAngle")
    double visionTargetAngle;

    @Signal(name = "db_visionTargetVisible")
    boolean visionTargetVisible;


    @Signal(name = "db_masterCaution")
    boolean masterCaution;
    String masterCautionTxt;

    @Signal(name="db_shooterSpeed")
    double shooterSpeed;

    @Signal(name="db_pneumaticsPressure")
    double pnuemPressure;

    @Signal(name="db_shooterSpoolup")
    boolean shooterSpoolup;

    @Signal(name="db_hopperFull")
    boolean hopperFull;

    @Signal(name="db_Yeet")
    boolean Yeet;

    @Signal(name="db_clmberExtend")
    boolean climberExtend;

    boolean pneumaticPressureLow = false; //TODO?

    DashboardConfig d;

    public Dashboard (Webserver2 ws_in) {
        d = ws_in.dashboard;

        final double LEFT_COL = 17;
        final double CENTER_COL = 50;
        final double RIGHT_COL = 83;

        final double ROW1 = 15;
        final double ROW2 = 45;
        final double ROW3 = 55;
        final double ROW4 = 68;

        //d.addCamera("cam1", "http://10.99.99.10:1181/stream.mjpg", LEFT_COL, ROW2, 0.25);
        //d.addCamera("cam2", "http://10.99.99.10:1182/stream.mjpg", RIGHT_COL, ROW2, 0.75);

        d.addFieldPose("pose", "Field", LEFT_COL, ROW1, 0.75);
        SwerveStateTopicSet[] topicList = new SwerveStateTopicSet[4];
        topicList[0] = new SwerveStateTopicSet("FL",0);
        topicList[1] = new SwerveStateTopicSet("FR",1);
        topicList[2] = new SwerveStateTopicSet("BL",2);
        topicList[3] = new SwerveStateTopicSet("BR",3);
        d.addSwerveState(topicList, "SwerveState", RIGHT_COL+3.5, ROW1, 0.8);

        d.addCircularGauge(SignalUtils.nameToNT4ValueTopic("db_shooterSpeed"), "Shooter", "RPM", 0, 5000, 1500, 4000, CENTER_COL-7, ROW1, 1.0);
        d.addCircularGauge(SignalUtils.nameToNT4ValueTopic("db_pneumaticsPressure"), "Pressure", "psi", 0, 140, 80, 130, CENTER_COL+13, ROW1, 1.0);

        
        d.addAutoChooser(Autonomous.getInstance().delayModeList, CENTER_COL, ROW2, 1.0);
        d.addAutoChooser(Autonomous.getInstance().mainModeList, CENTER_COL, ROW3, 1.0);


        d.addIcon(SignalUtils.nameToNT4ValueTopic("db_masterCaution"),"Master Caution", "#FF0000", "icons/alert.svg", CENTER_COL-6, ROW4, 1.0);
        d.addIcon(SignalUtils.nameToNT4ValueTopic("db_visionTargetVisible"),"Vision Target Visible", "#00FF00", "icons/vision.svg", CENTER_COL, ROW4, 1.0);
        d.addIcon(SignalUtils.nameToNT4ValueTopic("db_hopperFull"),"Hopper Full", "#00FF00", "icons/intake.svg", CENTER_COL+12, ROW4, 1.0);
        d.addIcon(SignalUtils.nameToNT4ValueTopic("db_clmberExtend"),"Climber Extend", "#FFFF00", "icons/climb.svg", CENTER_COL+6, ROW4, 1.0);
        d.addIcon(SignalUtils.nameToNT4ValueTopic("db_shooterSpoolup"),"Shooter Spoolup", "#FFFF00", "icons/speed.svg", CENTER_COL-12, ROW4, 1.0);
        d.addSound( SignalUtils.nameToNT4ValueTopic("db_Yeet"), "YEET", "sfx/YEET.mp3", false);
       

      }
    
      public void updateDriverView() {

        visionTargetVisible = DrivetrainPoseEstimator.getInstance().getVisionTargetsVisible();


        //master caution handling
        if ( pnuemPressure < 80.0 ) {
          masterCautionTxt = "Low Pneumatic Pressure";
          masterCaution = true;
        //}else if( !Vision.getInstance().getCamOnline() ) {
        //  masterCautionTxt = "Vision Camera Disconnected";
        //  masterCaution = true;
        } else {
          masterCautionTxt = "";
          masterCaution = false;
        }

        
      }
    

}
