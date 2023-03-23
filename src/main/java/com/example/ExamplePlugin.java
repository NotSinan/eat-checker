package com.example;

import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.OverlayManager;


@Slf4j
@PluginDescriptor(
		name = "PvP Eat Detector",
		description = "Detects when an enemy player eats during PvP combat"
)
public class ExamplePlugin extends Plugin {
	private boolean hasPrintedWelcomeMessage = false;

	@Inject
	private Client client;

	private Actor player;

	@Inject
	private EventBus eventBus;
	@Inject
	private OverlayManager overlayManager;

	private CombatListener combatListener;

	@Override
	protected void startUp() throws Exception {
		combatListener = new CombatListener(client);
		eventBus.register(combatListener);
		overlayManager.add(new DamageOverlay());
	}

	@Override
	protected void shutDown() throws Exception {
		eventBus.unregister(combatListener);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN && !hasPrintedWelcomeMessage)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Welcome to PvP Eat Detector! Configured to detect when an enemy player eats.", null);
			hasPrintedWelcomeMessage = true;
		}
	}
}
