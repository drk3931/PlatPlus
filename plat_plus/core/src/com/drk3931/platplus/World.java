package com.drk3931.platplus;
import com.badlogic.gdx.graphics.Color;

class World{

    public CharacterEntity[] worldCharacters;
    private Player player;


    public Player getPlayer()
    {
        return player;
    }

    public World()
    {

        player = new Player(50, 50, 32, 32, Color.BLUE);
       
        
    }


    public void update(float delta)
    {
        player.characterEntity.update(delta);

        
        for(int i =0 ; i < worldCharacters.length; i++)
        {
            worldCharacters[i].update(delta);
        }
        
    }



}