package com.example;

import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ClientPluginToolbar;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.overlay.OverlayManager;


@Slf4j
@PluginDescriptor(
		name = "Damage Tracker",
		description = "Tracks damage for the duration of a fight."
)
public class ExamplePlugin extends Plugin {
	private boolean hasPrintedWelcomeMessage = false;
	@Inject
	private ClientPluginToolbar pluginToolbar;

	@Inject
	private Client client;

	private Actor player;

	@Inject
	private EventBus eventBus;
	@Inject
	private OverlayManager overlayManager;

	private CombatListener combatListener;
	private ExamplePluginPanel examplePanel;
	private ClientToolbar clientToolbar;

	@Override
	protected void startUp() throws Exception {
		DamageOverlay overlay = new DamageOverlay();
		overlayManager.add(overlay);
		combatListener = new CombatListener(client, overlay);
		eventBus.register(combatListener);
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
