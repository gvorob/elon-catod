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
public class Drone extends ObjectController{
    DrawComp drawer;
    UIRegion ui;
    final float speed = 2;
    Vector2 target;
    
    public Drone(World w, boolean blueTeam, UIListener l)
    {
        drawer = new DrawComp(new SpriteData(0,0,0,32,64), -15, -57);
        Color[] colors = new Color[3];
        colors[0] = new Color(0, 0, 0, 128);
        colors[1] = new Color(100, 100, 100, 128);
        colors[2] = new Color(100, 0, 0, 128);
        Point p = DrawComp.fromIso(drawer.x, drawer.y, drawer.z);
        ui = new UIRegion(new Rectangle(p.x - 16, p.y - 40, 19, 38), 1, colors, l);
        ui.blocksClick = true;
        w.add(ui);
        w.add(drawer);
        
        drawer.x = 10;
        drawer.y = blueTeam?0.5f:49.5f;
        target = new Vector2(10,blueTeam?49.5f:0.5f);
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
        Point UICorner = DrawComp.fromIso(drawer.x, drawer.y, drawer.z);
        UICorner.x -= 8;
        UICorner.y -= 33;
        ui.region.setLocation(UICorner);
        
    }
}
