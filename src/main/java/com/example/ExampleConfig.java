package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pvp")
public interface ExampleConfig extends Config
{
	@ConfigItem(
			keyName = "notifyOnEat",
			name = "Notify on eat",
			description = "Enables or disables notification when the player eats during a PvP fight",
			position = 1
	)
	default boolean notifyOnEat()
	{
		return true;
	}

	@ConfigItem(
			keyName = "eatNotificationMessage",
			name = "Eat notification message",
			description = "The message to display when the player eats during a PvP fight",
			position = 2,
			hidden = true
	)
	default String eatNotificationMessage()
	{
		return "The player you are attacking has eaten!";
	}

	@ConfigItem(
			keyName = "minHealthDifferenceForEatDetection",
			name = "Minimum health difference for eat detection",
			description = "The minimum difference in health required to detect an eat",
			position = 3
	)
	default int minHealthDifferenceForEatDetection()
	{
		return 5;
	}
}

