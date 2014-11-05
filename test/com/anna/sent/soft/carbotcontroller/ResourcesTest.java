package com.anna.sent.soft.carbotcontroller;

import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;

import com.anna.sent.soft.carbotcontroller.main.CarBotController;
import com.anna.sent.soft.carbotcontroller.main.DeskPetController.Command;
import com.anna.sent.soft.carbotcontroller.main.DeskPetController.CommandType;

public class ResourcesTest extends AndroidTestCase {
	public void testResources() throws Throwable {
		Context context = getContext();
		for (boolean isFlip : new boolean[] { true, false }) {
			for (int option : new int[] { 0, 1, 2 }) {
				CarBotController carbot = new CarBotController(context, isFlip,
						option);
				for (CommandType commandType : CommandType.values()) {
					Command command = carbot.getCommand(commandType);
					if (command != null) {
						Assert.assertTrue(command.getSoundId() > 0);
					}
				}

				carbot.release();
			}
		}
	}
}