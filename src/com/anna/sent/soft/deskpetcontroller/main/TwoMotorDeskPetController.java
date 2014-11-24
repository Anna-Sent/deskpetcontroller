package com.anna.sent.soft.deskpetcontroller.main;

public abstract class TwoMotorDeskPetController extends DeskPetController
		implements MotionListener {
	private CommandType lastCommand;
	private long lastTimestamp;
	private final static long TIME_ELAPSED = 4000; // 4 seconds, in milliseconds

	private void playMotionCommand(CommandType command) {
		long timestamp = System.currentTimeMillis();
		if (lastCommand != command || timestamp - lastTimestamp > TIME_ELAPSED) {
			int priority = command == CommandType.LSRS ? 1 : 0;
			int loop = command == CommandType.LSRS ? 2
					: (command == CommandType.LFRF
							|| command == CommandType.LBRB ? 1 : 0);
			play(command, priority, loop);
			lastCommand = command;
			lastTimestamp = timestamp;
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