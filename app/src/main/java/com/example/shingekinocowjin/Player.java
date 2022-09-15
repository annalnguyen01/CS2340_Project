package com.example.shingekinocowjin;

public class Player {
    private static int monumentHealth = 0;
    private static int money;
    private String name;

    public Player(int health, int money, String name) {
        monumentHealth = health;
        this.money = money;
        this.name = name;
    }

    public void update() {

    }

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMonumentHealth(int health) {
        monumentHealth = health;
    }

    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void removeOneMonumentHealth() {
        monumentHealth--;
        if (monumentHealth <= 0) {
            System.out.println("Game Over"); // replace this with the game over screen soon TM.
        }
    }
}
