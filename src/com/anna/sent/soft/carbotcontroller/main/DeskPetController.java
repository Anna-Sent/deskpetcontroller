package com.anna.sent.soft.carbotcontroller.main;

import java.io.IOException;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public abstract class DeskPetController {
	protected static enum CommandType {
		BOOSTER, FIRE, FIRE2, LBRB, LBRF, LBRS, LFRB, LFRF, LFRS, LSRB, LSRF, LSRS, MODE, TRICK, TRICK2, MCHAG, TCHAG, SPEC, BWD, FWD, LEFT, RIGHT, STOP, TEAM
	}

	protected static class Command {
		private CommandType type;
		private int soundId;
		private String fileName;

		Command(CommandType type, String fileName) {
			this.type = type;
			this.fileName = fileName;
		}
	}

	private HashMap<CommandType, Command> commands = new HashMap<CommandType, Command>();

	private static final int MAX_STREAMS = 1;
	private static final int LEFT_VOLUME = 1;
	private static final int RIGHT_VOLUME = 1;
	private static final int PRIORITY = 0;
	private static final int LOOP = 0;
	private static final int RATE = 1;

	private SoundPool soundPool;

	private boolean isFlip = false;

	protected boolean isFlip() {
		return isFlip;
	}

	private int option = 0;

	protected int getOption() {
		return option;
	}

	public String[] getOptions() {
		return null;
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public DeskPetController(Context context, boolean isFlip, int option) {
		this.isFlip = isFlip;
		this.option = option;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			soundPool = new SoundPool.Builder()
					.setAudioAttributes(
							new AudioAttributes.Builder()
									.setUsage(AudioAttributes.USAGE_GAME)
									.setContentType(
											AudioAttributes.CONTENT_TYPE_UNKNOWN)
									.build()).setMaxStreams(MAX_STREAMS)
					.build();
		} else {
			soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
		}

		Command[] availableCommands = getAvailableCommands();
		for (Command command : availableCommands) {
			commands.put(command.type, command);
			try {
				command.soundId = soundPool.load(
						context.getAssets().openFd(command.fileName), 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void release() {
		soundPool.release();
		soundPool = null;
	}

	protected abstract Command[] getAvailableCommands();

	public boolean isCommandAvailable(CommandType commandType) {
		Command command = commands.get(commandType);
		return command != null;
	}

	protected void play(CommandType commandType) {
		Command command = commands.get(commandType);
		int soundId = command == null ? 0 : command.soundId;
		if (soundId > 0) {
			soundPool.play(soundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, LOOP,
					RATE);
		}
	}
}