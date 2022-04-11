// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_zeroWheel = 
    new CANSparkMax(DriveConstants.kLeftMotor00CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_oneWheel = 
    new CANSparkMax(DriveConstants.kLeftMotor01CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_twoWheel = 
    new CANSparkMax(DriveConstants.kRightMotor02CanBusID, MotorType.kBrushless);
  private final CANSparkMax m_threeWheel = 
    new CANSparkMax(DriveConstants.kRightMotor03CanBusID, MotorType.kBrushless);
  
  // MecanumDrive takes (frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor)
  private final MecanumDrive m_mecDrive = new MecanumDrive(m_zeroWheel, m_oneWheel, m_threeWheel, m_twoWheel);
/*
  private final MotorControllerGroup m_leftMotors =
    new MotorControllerGroup(m_zeroWheel, m_oneWheel);
  private final MotorControllerGroup m_rightMotors =
    new MotorControllerGroup(m_twoWheel, m_threeWheel);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
*/
  private final SlewRateLimiter filter = new SlewRateLimiter(0.5);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    //arcadedrive limits
    //m_drive.setDeadband(0.15);
    //m_drive.setMaxOutput(0.9);

    m_zeroWheel.restoreFactoryDefaults();
    m_zeroWheel.setIdleMode(IdleMode.kBrake);
    m_zeroWheel.setInverted(false);
    //m_zeroWheel.burnFlash();

    m_oneWheel.restoreFactoryDefaults();
    m_oneWheel.setIdleMode(IdleMode.kBrake);
    m_oneWheel.setInverted(false);
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

  public void driveMecanum(double LeftYSpeed, double LeftXSpeed, double RightYSpeed){
    m_mecDrive.driveCartesian(Math.pow(LeftYSpeed, 3), Math.pow(LeftXSpeed, 3), Math.pow(RightYSpeed, 3));
    
  }
/*
  public void arcadeDrive(double fwd, double rot){
    m_drive.arcadeDrive(-fwd*Math.abs(fwd), rot);
  }
*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
