package com.anna.sent.soft.deskpetcontroller;

import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;

import com.anna.sent.soft.deskpetcontroller.main.CarBotController;
import com.anna.sent.soft.deskpetcontroller.main.DeskPetController;
import com.anna.sent.soft.deskpetcontroller.main.DeskPetController.Command;
import com.anna.sent.soft.deskpetcontroller.main.DeskPetController.CommandType;

public class ResourcesTest extends AndroidTestCase {
	public void testResources() throws Throwable {
		CarBotController carBot = new CarBotController();
		testDeskPet(carBot);
	}

	private void testDeskPet(DeskPetController deskPet) {
		Context context = getContext();
		String[] models = deskPet.getModels();
		int[] modelIndexes = new int[models.length];
		for (int i = 0; i < modelIndexes.length; ++i) {
			modelIndexes[i] = i;
		}

		for (boolean isFlip : new boolean[] { true, false }) {
			for (int modelIndex : modelIndexes) {
				deskPet.setFlip(isFlip);
				deskPet.setModelIndex(modelIndex);
				deskPet.resume(context);

				for (CommandType commandType : CommandType.values()) {
					Command command = deskPet.getCommand(commandType);
					if (command != null) {
						Assert.assertTrue(command.getSoundId() > 0);
					}
				}

				deskPet.pause();
			}
		}
	}
}