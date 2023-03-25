package com.example;

import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.events.NpcDespawned;
import net.runelite.client.eventbus.Subscribe;

public class CombatListener {

    private final Client client;
    private final DamageOverlay overlay;
    private FightState fightState;
    private Actor actor;

    public CombatListener(Client client, DamageOverlay overlay) {
        this.client = client;
        this.overlay = overlay;
        this.fightState = null;
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied event) {
        actor = client.getLocalPlayer().getInteracting();

        if (actor == null) {
            fightState = null;
            return;
        }

        if (fightState == null) {
            // Start a new fight
            fightState = new FightState(actor);
            System.out.println("New fight state");
        }

        if (event.getActor() == actor) {
            // Update the fight state with the damage dealt
            int damage = event.getHitsplat().getAmount();
            fightState.addDamage(damage);
            overlay.updateDamage(fightState.getTotalDamageDealt());
        }
    }

    public void handleNpcDeath() {
        if (fightState != null) {
            overlay.updateDamage(0); // reset damage overlay
            fightState = null; // reset fight state
        }
    }
    @Subscribe
    public void onNpcDespawned(NpcDespawned event) {
        Actor despawnedActor = event.getNpc();

        if (despawnedActor.equals(actor)) {
            handleNpcDeath();
        }
    }


}
