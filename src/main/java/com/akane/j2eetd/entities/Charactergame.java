package com.akane.j2eetd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "charactergame")
public class Charactergame {

    @Id
    @Column
    private String pseudo;
    @Column
    private String gender;
    @Column
    private Long money;
    @Column
    private Long hpMax;
    @Column
    private Long manaMax;
    @Column
    private Long staminaMax;
    @Column
    private Long hungerMax;
    @Column
    private Long hp;
    @Column
    private Long mana;
    @Column
    private Long stamina;
    @Column
    private Long hunger;
    @Column
    private Long strength;
    @Column
    private Long intelligence;
    @Column
    private Long agility;
    @Column
    private Long luck;
    @Column
    private String created;
    @Column
    private String lastLogin;
    @Column
    private String img;

    @OneToOne(mappedBy = "charactergame")@JsonIgnore
    private User user;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getHpMax() {
        return hpMax;
    }

    public void setHpMax(Long hpMax) {
        this.hpMax = hpMax;
    }

    public Long getManaMax() {
        return manaMax;
    }

    public void setManaMax(Long manaMax) {
        this.manaMax = manaMax;
    }

    public Long getStaminaMax() {
        return staminaMax;
    }

    public void setStaminaMax(Long staminaMax) {
        this.staminaMax = staminaMax;
    }

    public Long getHungerMax() {
        return hungerMax;
    }

    public void setHungerMax(Long hungerMax) {
        this.hungerMax = hungerMax;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public Long getMana() {
        return mana;
    }

    public void setMana(Long mana) {
        this.mana = mana;
    }

    public Long getStamina() {
        return stamina;
    }

    public void setStamina(Long stamina) {
        this.stamina = stamina;
    }

    public Long getHunger() {
        return hunger;
    }

    public void setHunger(Long hunger) {
        this.hunger = hunger;
    }

    public Long getStrength() {
        return strength;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    public Long getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Long intelligence) {
        this.intelligence = intelligence;
    }

    public Long getAgility() {
        return agility;
    }

    public void setAgility(Long agility) {
        this.agility = agility;
    }

    public Long getLuck() {
        return luck;
    }

    public void setLuck(Long luck) {
        this.luck = luck;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
