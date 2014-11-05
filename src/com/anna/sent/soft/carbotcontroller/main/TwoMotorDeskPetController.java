package com.anna.sent.soft.carbotcontroller.main;

public abstract class TwoMotorDeskPetController extends DeskPetController
		implements MotionListener {
	private CommandType lastCommand = null;

	// timestamp

	private void playMotionCommand(CommandType command) {
		if (lastCommand != command) {
			play(command, command == CommandType.LSRS ? 1 : 0,
					command == CommandType.LSRS ? 2 : 1);
			lastCommand = command;
		}
	}

	@Override
	public void moveForward() {
		playMotionCommand(CommandType.LFRF);
	}

	@Override
	public void moveBackward() {
		playMotionCommand(CommandType.LBRB);
	}

	@Override
	public void moveToLeftForward() {
		playMotionCommand(CommandType.LSRF);
	}

	@Override
	public void moveToRightForward() {
		playMotionCommand(CommandType.LFRS);
	}

	@Override
	public void moveToLeftBackward() {
		playMotionCommand(CommandType.LSRB);
	}

	@Override
	public void moveToRightBackward() {
		playMotionCommand(CommandType.LBRS);
	}

	@Override
	public void rotateLeft() {
		playMotionCommand(CommandType.LBRF);
	}

	@Override
	public void rotateRight() {
		playMotionCommand(CommandType.LFRB);
	}

	@Override
	public void stop() {
		playMotionCommand(CommandType.LSRS);
	}
}