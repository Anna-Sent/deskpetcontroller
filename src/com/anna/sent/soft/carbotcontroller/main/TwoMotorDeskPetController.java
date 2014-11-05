package com.anna.sent.soft.carbotcontroller.main;

import android.content.Context;

public abstract class TwoMotorDeskPetController extends DeskPetController
		implements MotionListener {
	public TwoMotorDeskPetController(Context context, boolean isFlip, int option) {
		super(context, isFlip, option);
	}

	@Override
	public void moveForward() {
		play(CommandType.LFRF);
	}

	@Override
	public void moveBackward() {
		play(CommandType.LBRB);
	}

	@Override
	public void moveToLeftForward() {
		play(CommandType.LBRF);
	}

	@Override
	public void moveToRightForward() {
		play(CommandType.LFRB);
	}

	@Override
	public void moveToLeftBackward() {
		play(CommandType.LFRB);
	}

	@Override
	public void moveToRightBackward() {
		play(CommandType.LBRF);
	}

	@Override
	public void rotateLeft() {
		play(CommandType.LSRF);
	}

	@Override
	public void rotateRight() {
		play(CommandType.LFRS);
	}

	@Override
	public void stop() {
		play(CommandType.LSRS);
	}
}