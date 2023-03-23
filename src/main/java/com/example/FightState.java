package com.example;

import net.runelite.api.Actor;

public class FightState {
    private Actor npc;
    private int totalDamageDealt;

    public FightState(Actor npc) {
        this.npc = npc;
        this.totalDamageDealt = 0;
    }

    public void addDamage(int damage) {
        this.totalDamageDealt += damage;
    }

    public int getTotalDamageDealt() {
        return this.totalDamageDealt;
    }

    public boolean isFinished() {
        return npc == null || npc.isDead();
    }

    public void reset() {
        totalDamageDealt = 0;
    }

    public Actor getNPC() {
        return npc;
    }

    public void setNPC(Actor npc) {
        this.npc = npc;
    }
}
