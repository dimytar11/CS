package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl extends ComparatorClass implements Player  {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.gun = gun;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {
        int currPoints = points;
        if (this.getArmor() >= points) {
            currPoints -= this.getArmor();
            setArmor(this.getArmor() - points);
        }else if ((this.getHealth() + this.getArmor()) > points) {
            this.setHealth((this.getHealth() + this.getArmor()) - points);
            this.setArmor(0);
        }else {
            this.setArmor(0);
            this.setHealth(0);
            isAlive = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();



        sb
                .append(String.format("%s: %s", this.getClass().getSimpleName(),
                this.getUsername()))
                .append(System.lineSeparator())
                .append(String.format("--Health: %d", this.getHealth()))
                .append(System.lineSeparator())
                .append(String.format("--Armor: %d", this.armor))
                .append(System.lineSeparator())
                .append(String.format("--Gun: %s", this.getGun().getName()));


        return sb.toString().trim();
    }
}
