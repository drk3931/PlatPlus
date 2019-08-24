package com.drk3931.platplus;

public class CharacterStats extends EntityStats
{
    private int health; 
    private int maxHealth = 100;
    private boolean markedForRemoval = false;

    public boolean markedForRemoval()
    {
        return markedForRemoval;
    }

    public CharacterStats(int maxHealth)
    {
        //set health to max initially
        this.maxHealth = health;
        this.health= this.maxHealth; 
    }


    public int getHealth()
    {
        return health;
    }
    

    public void subHealth(int amount)
    {
        if(entityState == STATE.SPECIAL)
        {
            return;
        }

        this.health -= amount;
        if(health < 0)
        {
            health = 0; 
            markedForRemoval = true;
        }
        
    }

    public void incHealth(int amount)
    {   


        if(entityState == STATE.SPECIAL)
        {
            return;
        }

        this.health += amount;
        if(health > maxHealth)
        {
            health = maxHealth; 
        }
     
    }
}