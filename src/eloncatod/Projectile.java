/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

/**
 *
 * @author George
 */
public class Projectile extends ObjectController{
    Attackable target;
    Vector2 location;
    float speed;
    int damage;
    int damageType;
    boolean hit; //set to true if projectile has hit its mark;
    
    DrawComp drawer;
    
    public Projectile(Attackable tar, int dam, int damt, float s, Vector2 loc)
    {
        drawer = new DrawComp(new SpriteData(3,0,0,32,64), -15, -57);
        drawer.z = 3;
        target = tar;
        damage = dam;
        damageType = damt;
        location = loc;
        speed = s;
        
        drawer.move(loc);
        World.w.add(drawer);
    }
    
    public void update(float t)
    {
        Collider.moveTowards(location, target.getLoc(), speed * t);
        target.getLoc();
        drawer.move(location);
        
        if(target.getLoc().equals(location))
        {
            target.takeDamage(damageType, damage);
            hit = true;
        }
    }
    
    public boolean checkRemove()
    {return hit;}
    
    public void remove()
    {
        drawer.remove();
    }
    
}
