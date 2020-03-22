package com.drk3931.platplus;
import com.drk3931.platplus.effects.GravityEffect;
import com.drk3931.platplus.projectiles.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player implements DrawableComponent, CameraController,Updateable {


    private AnimationHandler animationHandler;

    int speedX = 333, jumpVelocity = 1400;
    Entity e;

    GravityEffect gravEffect;
    Vector3 cameraUnprojected; 

    Camera camRef;

    int fireRateMS = 1000;


    public Player(float x, float y) {


        e = new Entity();
        e.setGeoRep(new GeometricRepresentation(Color.ORANGE, new Rectangle(x, y, 64, 128)));
        gravEffect = new GravityEffect();
        cameraUnprojected = new Vector3();
        animationHandler = new  AnimationHandler(GameLoader.genAnimation("player_walk_animation.png", 5, 6), e);

    }


    long lastFire = 0;

    @Override
    public void update(float delta) {


        e.setVelocityX(0);
        //e.setVelocityY(0);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            e.setVelocityX(delta * speedX * -1);
           animationHandler.incrementTime(delta);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            e.setVelocityX(delta * speedX);
            animationHandler.incrementTime(delta);

        }

        
        if (Gdx.input.isKeyPressed(Keys.UP) && e.getVelocityY() == 0) {
            e.setVelocityY(delta * jumpVelocity);
        }
        

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int yPos = Gdx.input.getY();
            int xPos = Gdx.input.getX();

            if(System.currentTimeMillis() - lastFire > fireRateMS)
            {

                cameraUnprojected.set(xPos, yPos, 0);
                cameraUnprojected.set(Renderer.getMousePosInGameWorld(cameraUnprojected, camRef));

            
                lastFire = System.currentTimeMillis();
                World.projectileStore.add(new Projectile(this.e, Color.BLUE, cameraUnprojected.x, cameraUnprojected.y));

            }
        
            
            //this.player.weapon.fire(xPos, Gdx.graphics.getHeight() - yPos);
        }

        /*
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            e.setVelocityY(delta * 300);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            e.setVelocityY(delta * 300 * -1);
        }*/

        gravEffect.apply(e, delta);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
       this.e.getGeoRep().drawShapeRenderer(shapeRenderer);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        //animationHandler.draw(b);
      
    }

    @Override
    public void applyToCam(Camera c) {


        this.camRef = c;

        
        c.position.x = e.getGeoRep().getX();

        if(e.getGeoRep().getY() > Gdx.graphics.getHeight()/2)
        {
            c.position.y = e.getGeoRep().getY();
        }
        else{
            c.position.y = Gdx.graphics.getHeight()/2;
        }


        
    }

}