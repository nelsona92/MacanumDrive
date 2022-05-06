// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import javax.swing.plaf.TreeUI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_zeroWheel = 
    new CANSparkMax(DriveConstants.kLeftMotor01CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_oneWheel = 
    new CANSparkMax(DriveConstants.kLeftMotor02CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_twoWheel = 
    new CANSparkMax(DriveConstants.kRightMotor03CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_threeWheel = 
    new CANSparkMax(DriveConstants.kRightMotor04CanBusID, MotorType.kBrushless);
  
  // MecanumDrive takes (frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor)
  private final MecanumDrive m_mecDrive = new MecanumDrive(m_zeroWheel, m_oneWheel, m_threeWheel, m_twoWheel);

  private int Direction = 1;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {


    m_zeroWheel.restoreFactoryDefaults();
    m_zeroWheel.setIdleMode(IdleMode.kBrake);
    m_zeroWheel.setInverted(true);
    //m_zeroWheel.burnFlash();

    m_oneWheel.restoreFactoryDefaults();
    m_oneWheel.setIdleMode(IdleMode.kBrake);
    m_oneWheel.setInverted(true);
    //m_oneWheel.burnFlash();

    m_twoWheel.restoreFactoryDefaults();
    m_twoWheel.setIdleMode(IdleMode.kBrake);
    m_twoWheel.setInverted(false);
    //m_twoWheel.burnFlash();

    m_threeWheel.restoreFactoryDefaults();
    m_threeWheel.setIdleMode(IdleMode.kBrake);
    m_threeWheel.setInverted(false);
    //m_threeWheel.burnFlash();

//ramp rate
    m_zeroWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_oneWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_twoWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_threeWheel.setOpenLoopRampRate(DriveConstants.kRampRate);

  //m_drive.
  }

  public void driveMecanum(double LeftXSpeed, double RightXSpeed, double LeftYSpeed){
    m_mecDrive.driveCartesian(Math.pow(LeftYSpeed, 3), -Math.pow(RightXSpeed, 3), Math.pow(LeftXSpeed, 3));
    
  }
  //private final AHRS m_ahrs =
  //  new AHRS(SPI.Port.kMXP);
//private final RobotContainer m_container = new RobotContainer();
//public final XboxController m_xboxController = new XboxController(OIConstants.kDriverControllerPort);
  //private final Encoder m_testEncoder = new Encoder(0, 1, false, EncodingType.k4X);
    

  // straight driving... needs gyro added
  public void simpleDrive(double kpower) {
    driveMecanum(0.0, 0.0, kpower);
  }

  // to drop maximum speed for delicate motion
  public void setMax(double maxOutput){
    m_mecDrive.setMaxOutput(maxOutput);
  }

  // turns the to half and then back on
  public void halfPower() {
    if (!Constants.powerState) {
      m_mecDrive.setMaxOutput(DriveConstants.kSlowMaxSpeed);
    }
    else {
      m_mecDrive.setMaxOutput(DriveConstants.kMaxSpeed); 
    }
    Constants.powerState = !Constants.powerState;
  } // end winchOn

  public void invertDrive(){
    Direction = -Direction;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}