package com.akane.j2eetd.entities;

import javax.persistence.Column;

public enum Characteristic {
    hpMax("hpMax"), manaMax("manaMax"), staminaMax("staminaMax"), hungerMax("hungerMax"), hp("hp"),
    mana("mana"), stamina("stamina"), hunger("hunger"), strength("strength"), intelligence("intelligence"),
    agility("agility"), luck("luck");


    private String characteristic;

    private Characteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    @Override
    public String toString() {
        return characteristic;
    }
}