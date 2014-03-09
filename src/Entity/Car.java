/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

/**
 *
 * @author skas
 */
public class Car extends Render implements Updateable {
    private final static String path = "car.png";
    
    public Car(float x, float y)
    {
        super(x,y, path);
        this.rot = 0;
        this.scale = 0.75f;
        this.x = Render.screenWidth/2.0f - this.getWidthTrue()/2.0f;
        this.y -= 30;
    }

    @Override
    public void update(int delta) {
        //Animate car spinning.
        //adjust these spazzing to a smaller range for the iTL machines.
        float rangeTurbulance = (float) (Math.random() * 20.0f - 10.0f);
        float deltaf = delta;
        this.rot = rangeTurbulance/deltaf * this.getScale();
        this.img.setRotation(this.rot);
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        img.setCenterOfRotation(this.getWidth()/2,this.getHeight()/2);
        super.render(g);
    }
    
}
