/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author George
 */
public class Player extends ObjectController implements UIListener{
    DrawComp drawer;
    UIRegion interactRegion;
    Vector2 target;
    
    final float speed = 6;
    
    public Player(World w)
    {
        SpriteData temp = new SpriteData(1, 0, 0, 64, 128);
        drawer = new DrawComp(temp,-32,-116);
        interactRegion = new UIRegion(new Rectangle(-50000, -50000, 100000, 100000), 0, this);
        w.add(drawer);
        w.add(interactRegion);
    }
    
    public void update(float t, World w)
    {
        if(target != null)
        {
            Vector2 temp = Vector2.vecSubt(target, new Vector2(drawer.x,drawer.y));
            if(temp.length() < speed * t)
            {
                target = null;
            }
            else
            {
                temp.setLength(speed * t);
                drawer.x += temp.x;
                drawer.y += temp.y;
            }
        }
    }
    
    public Point getView()
    {
        Point view =  DrawComp.fromIso(drawer.x, drawer.y, drawer.z);
        view.y -= 250;
        view.x -= 250;
        return view;
    }

    @Override
    public void informClicked(int i, Mouse m) {
        switch(i)
        {
            case 0:
                target = DrawComp.toIso(m.getX(),m.getY());
                break;
        }
    }

    public void clickedOn(Attackable att, Mouse m) {
        if(!m.getL())
        {
            createProjectile(att);
        }
    }
    
    private void createProjectile(Attackable att)
    {
        att.takeDamage(0, 200);
    }
}