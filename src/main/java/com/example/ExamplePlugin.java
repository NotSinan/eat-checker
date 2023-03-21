package com.example;

import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PluginDescriptor(
		name = "PvP Eat Detector",
		description = "Detects when an enemy player eats during PvP combat"
)
public class ExamplePlugin extends Plugin
{
	private boolean hasPrintedWelcomeMessage = false;
	private boolean hasPrintedEnemyPlayerName = false;
	private int oldHp = -1;
	private int currentHp = -1;
	private String enemyPlayerName = null;

	private int totalDamage = 0;

	@Inject
	private Client client;

	private Actor player;

	@Override
	protected void startUp() throws Exception
	{
		log.info("PvP Eat Detector started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("PvP Eat Detector stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN && !hasPrintedWelcomeMessage)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Welcome to PvP Eat Detector! Configured to detect when an enemy player eats.", null);
			hasPrintedWelcomeMessage = true;
		}
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged interactingChanged)
	{
		player = interactingChanged.getSource().getInteracting();

		if (player != null && player != client.getLocalPlayer()) {
			enemyPlayerName = player.getName();
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Your current target is: " + enemyPlayerName, "Game");
		} else {
			enemyPlayerName = null;
			totalDamage = 0;
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event) {
		if (enemyPlayerName != null) {
			Hitsplat hitsplat = event.getHitsplat();
			int damageDealt = hitsplat.getAmount();
			totalDamage += damageDealt;
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Damage dealt: " + totalDamage, "Game");
		}
	}
}
