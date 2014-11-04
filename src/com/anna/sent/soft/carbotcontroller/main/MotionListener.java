package com.anna.sent.soft.carbotcontroller.main;

public interface MotionListener {
	public void moveForward();

	public void moveBackward();

	public void moveToLeftForward();

	public void moveToRightForward();

	public void moveToLeftBackward();

	public void moveToRightBackward();

	public void rotateLeft();

	public void rotateRight();

	public void stop();
}