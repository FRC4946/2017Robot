package org.usfirst.frc.team4946.robot.commands.autonomous;

import org.usfirst.frc.team4946.robot.commands.driveTrain.AutoDriveDistancePID;
import org.usfirst.frc.team4946.robot.commands.gearpusher.ExtendGearPusher;
import org.usfirst.frc.team4946.robot.commands.gearpusher.RetractGearPusher;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearAndBackup extends CommandGroup {

	public PlaceGearAndBackup() {
		addSequential(new ExtendGearPusher());
		addSequential(new Wait(0.5));
		addSequential(new AutoDriveDistancePID(-30));
		addSequential(new RetractGearPusher());
	}
}
