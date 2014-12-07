package com.anna.sent.soft.deskpetcontroller.main;

import java.util.ArrayList;
import java.util.List;

public class CarBotController extends TwoMotorDeskPetController {
	private String getName(String commandName) {
		return "wave/carbot" + (isFlip() ? "Flip" : "") + "/" + commandName
				+ "Carbot" + String.valueOf(getModelIndex() + 1)
				+ (isFlip() ? "Flip" : "") + (isFlip() ? ".wav" : ".WAV");
	}

	/**
	 * TODO test that all resources are available
	 */
	@Override
	public Command[] getAvailableCommands() {
		List<Command> commands = new ArrayList<Command>();

		commands.add(new Command(CommandType.BOOSTER, getName("BOOSTER")));
		commands.add(new Command(CommandType.FIRE, getName("FIRE")));
		commands.add(new Command(CommandType.FIRE2, getName("FIRE2")));
		commands.add(new Command(CommandType.LBRB, getName("LBRB")));
		commands.add(new Command(CommandType.LBRF, getName("LBRF")));
		commands.add(new Command(CommandType.LBRS, getName("LBRS")));
		commands.add(new Command(CommandType.LFRB, getName("LFRB")));
		commands.add(new Command(CommandType.LFRF, getName("LFRF")));
		commands.add(new Command(CommandType.LFRS, getName("LFRS")));
		commands.add(new Command(CommandType.LSRB, getName("LSRB")));
		commands.add(new Command(CommandType.LSRF, getName("LSRF")));
		commands.add(new Command(CommandType.LSRS, getName("LSRS")));

		return commands.toArray(new Command[commands.size()]);
	}

	@Override
	public String[] getModels() {
		return new String[] { "Channel A", "Channel B", "Channel C" };
	}
}