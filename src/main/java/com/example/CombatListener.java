package com.example;

import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.PlayerDespawned;
import net.runelite.client.eventbus.Subscribe;

public class CombatListener {

    private final Client client;
    private FightState fightState;

    public CombatListener(Client client) {
        this.client = client;
        this.fightState = null;
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied event) {
        Actor npc = client.getLocalPlayer().getInteracting();
        if (npc != null && event.getActor() == client.getLocalPlayer()) {
            if (fightState == null || !fightState.getNPC().equals(npc)) {
                // Start a new fight
                fightState = new FightState(npc);
                System.out.println("New fight state");
            }

            if (event.getActor() == npc) {
                // Update the fight state with the damage dealt
                int damage = event.getHitsplat().getAmount();
                fightState.addDamage(damage);
            }

        }
    }
    @Subscribe
    public void onNpcDespawned(NpcDespawned event) {
        int totalDamageDealt = fightState.getTotalDamageDealt();
        System.out.println("Total damage dealt: " + totalDamageDealt);
        fightState.reset();
    }
}
