package com.anna.sent.soft.carbotcontroller;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.anna.sent.soft.carbotcontroller.main.AccelerometerSensorEventListener;
import com.anna.sent.soft.carbotcontroller.main.CarBotController;
import com.anna.sent.soft.carbotcontroller.main.MotionListener;

public class MainActivity extends MainActivityBase implements MotionListener,
		AccelerometerSensorEventListener.LogListener {
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private AccelerometerSensorEventListener mAccelerometerListener;
	private CarBotController mCarBot;
	private TextView mTextViewTitle, mTextView1, mTextView2;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mTextViewTitle = (TextView) findViewById(R.id.textViewTitle);
		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mAccelerometerListener = new AccelerometerSensorEventListener();
		mCarBot = new CarBotController();
		mCarBot.setFlip(false);
		mCarBot.setModelIndex(0);
		if (mSensor != null) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
				int minDelay = mSensor.getMinDelay(); // in microseconds
				if (minDelay > 0) {
					mTextViewTitle
							.setText(getString(
									R.string.accelerometer_with_min_delay_in_milliseconds_is_available,
									minDelay / 1000));
				} else {
					mTextViewTitle
							.setText(R.string.not_streaming_accelerometer_is_available);
				}
			} else {
				mTextViewTitle.setText(R.string.accelerometer_is_available);
			}
		} else {
			mTextViewTitle.setText(R.string.accelerometer_is_not_available);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mCarBot.resume(getApplicationContext());
		mAccelerometerListener.addListener(this);
		mAccelerometerListener.addListener(mCarBot);
		mAccelerometerListener.setLogListener(this);
		mSensorManager.registerListener(mAccelerometerListener, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(mAccelerometerListener);
		mAccelerometerListener.setLogListener(null);
		mAccelerometerListener.removeListener(mCarBot);
		mAccelerometerListener.removeListener(this);
		mCarBot.stop();
		mCarBot.pause();
	}

	@Override
	public void log(double Gx, double Gy, double Gz, long timestamp,
			int accuracy) {
		mTextView2.setText(getString(R.string.accuracy, accuracy) + "\n"
				+ getString(R.string.timestamp, timestamp) + "\n"
				+ getString(R.string.values, Gx, Gy, Gz));
	}

	@Override
	public void moveForward() {
		mTextView1.setText("forward");
	}

	@Override
	public void moveBackward() {
		mTextView1.setText("backward");
	}

	@Override
	public void moveToLeftForward() {
		mTextView1.setText("forward to left");
	}

	@Override
	public void moveToRightForward() {
		mTextView1.setText("forward to right");
	}

	@Override
	public void moveToLeftBackward() {
		mTextView1.setText("backward to left");
	}

	@Override
	public void moveToRightBackward() {
		mTextView1.setText("backward to right");
	}

	@Override
	public void rotateLeft() {
		mTextView1.setText("rotate left");
	}

	@Override
	public void rotateRight() {
		mTextView1.setText("rotate right");
	}

	@Override
	public void stop() {
		mTextView1.setText("stop");
	}
}