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
    Vector2 origin;
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
        origin = loc.clone();
        speed = 10;//s;
        
        drawer.move(loc);
        doBounce();
        World.w.add(drawer);
    }
    
    public void update(float t)
    {
        Collider.moveTowards(location, target.getLoc(), speed * t);
        target.getLoc();
        drawer.move(location);
        
        doBounce();
        
        if(target.getLoc().equals(location))
        {
            target.takeDamage(damageType, damage);
            hit = true;
        }
    }
    
    private void doBounce()
    {
        float width = Vector2.dist(origin, target.getLoc());
        float x = location.distTo(origin);
        float height =   x * (x - width) / (width * width) * 4;//parabola of height 1 with roots at 0 and width
        drawer.z = 4 - 2 * height;//varies from height 3 to 5
        //System.out.printf("percent:%f  height:%f\n", x/width, height);
    }
    
    public boolean checkRemove()
    {return hit;}
    
    public void remove()
    {
        drawer.remove();
    }
    
}
