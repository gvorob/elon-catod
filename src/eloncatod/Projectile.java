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
    
    DrawComp drawer;
    
    public Projectile(Attackable tar, int dam, int damt, float s, Vector2 loc)
    {
        target = tar;
        damage = dam;
        damageType = damt;
        location = loc;
        speed = s;
    }
    
    public void update(float t)
    {
        Collider.moveTowards(location, target.getLoc(), speed * t);
        target.getLoc();
    }
    
    
}
