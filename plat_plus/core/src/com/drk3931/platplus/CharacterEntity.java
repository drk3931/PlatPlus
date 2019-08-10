package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public abstract class CharacterEntity extends Entity implements DrawableComponent, UpdateableEntity{



    public Rectangle rectangleRepresentation;
    private boolean gravityEnabled;
    public TextureRegion characterTexture;
    private CharacterRoutine characterRoutine; 



    public int xVelocity = 0,yVelocity=0, yVelocityCap = 335;
    private float lastX,lastY;

    public CharacterEntity(float x,float y, float w, float h, Color c, boolean gravityEnabled, TextureRegion texture)
    {
        super(c);
        this.shapeRepresentation = new Rectangle(x,y,w,h);
        this.rectangleRepresentation = (Rectangle)this.shapeRepresentation;
        this.gravityEnabled = gravityEnabled;
        this.characterTexture = texture;

    }


    public void setCharacterRoutine(CharacterRoutine routine)
    {
        this.characterRoutine = routine;
    }

    public void setTexture(TextureRegion texture)
    {
        this.characterTexture = texture;
    }

    public  void setXY(int x,int y)
    {
        this.rectangleRepresentation.x = x;
        this.rectangleRepresentation.y = y;

    }
    public  void setWH(int w,int h)
    {
        this.rectangleRepresentation.width = w;
        this.rectangleRepresentation.height = h;

    }
    public void translate(float dx,float dy)
    {
        this.rectangleRepresentation.x +=   dx ;
        this.rectangleRepresentation.y +=   dy ;

    }

    public boolean canJump()
    {
        return yVelocity == World.gravityAcceleration;
    }
    
    public void update(float delta){



        characterRoutine.routine(delta,this);
        if(gravityEnabled && this.yVelocity > yVelocityCap * -1)
        {
            this.yVelocity += World.gravityAcceleration;
        }
        translate(0, this.yVelocity * delta);   

        


    }

    public void setCoordinatesBeforeCollisionResolution()
    {
        this.lastX = rectangleRepresentation.x;
        this.lastY = rectangleRepresentation.y;
    }


    public void drawShapeRenderer(ShapeRenderer shapeRenderer)
    {
        shapeRenderer.set(ShapeType.Line);

        Rectangle rectRep = this.rectangleRepresentation;

        shapeRenderer.rect(rectRep.x, rectRep.y, rectRep.width, rectRep.height);

    }

    public void drawSpriteBatch(SpriteBatch spriteBatch)
    {   

        if (this.characterTexture != null) {
            Rectangle rectRep = this.rectangleRepresentation;
            spriteBatch.draw(this.characterTexture, rectRep.x, rectRep.y, rectRep.width, rectRep.height);

        }
   
    }


}