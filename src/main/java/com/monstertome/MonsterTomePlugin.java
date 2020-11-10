package com.monstertome;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.InteractingChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@PluginDescriptor(
		name = "Monster Tome",
		description = "Monster tome for logging and collecting monster entries"

)
public class MonsterTomePlugin extends Plugin
{
	private static final Duration WAIT = Duration.ofSeconds(5);

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private MonsterTomeOverlay overlay;

	@Getter(AccessLevel.PACKAGE)
	private Actor lastOpponent;

	private Instant lastTime;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		lastOpponent = null;
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says ", null);
		}
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged event)
	{
		if (event.getSource() != client.getLocalPlayer())
		{
			return;
		}

		Actor opponent = event.getTarget();

		if (opponent == null)
		{
			lastTime = Instant.now();
			return;
		}

		lastOpponent = opponent;
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (lastOpponent != null
				&& lastTime != null
				&& client.getLocalPlayer().getInteracting() == null)
		{
			if (Duration.between(lastTime, Instant.now()).compareTo(WAIT) > 0)
			{
				lastOpponent = null;
			}
		}
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded)
	{
		if (menuEntryAdded.getType() != MenuAction.NPC_SECOND_OPTION.getId()
				|| !menuEntryAdded.getOption().equals("Attack"))
		{
			return;
		}

		int npcIndex = menuEntryAdded.getIdentifier();
		NPC npc = client.getCachedNPCs()[npcIndex];
		if (npc == null)
		{
			return;
		}

		if (npc.getInteracting() == client.getLocalPlayer() || lastOpponent == npc)
		{
			MenuEntry[] menuEntries = client.getMenuEntries();
			menuEntries[menuEntries.length - 1].setTarget("*" + menuEntryAdded.getTarget());
			client.setMenuEntries(menuEntries);
		}
	}
}
