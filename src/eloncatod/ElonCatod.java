/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eloncatod;

/**
 *
 * @author George Vorobyev <quaffle97@gmail.com>
 */
public class ElonCatod implements TimerListener, KeyEventListener, MouseEventListener
{
    
    public static void main(String[] args) {
        
        ElonCatod e = new ElonCatod();
    }
    
    public Screen screen;
    public Timer timer;
    public Keyboard keys;
    public Mouse mouse;
    public World world;
    
    public ElonCatod()
    {
        screen = new Screen(500, 500, "ElonCatod");
        timer = new Timer(50);
        timer.addListener(this);
        keys = new Keyboard();
        mouse = new Mouse();
        screen.c.addKeyListener(keys);
        screen.c.addMouseListener(mouse);
        screen.c.addMouseMotionListener(mouse);
        mouse.addListener(this);
        
        world = new World(mouse);
        
        timer.start();
    }

    @Override
    public void timerEvent() {
        screen.clear();
        world.update((float)timer.interval / 1000, keys, mouse);
        world.draw(screen.buffer);
        screen.flushBuffer();
    }

    @Override
    public boolean KeyChange(int index, boolean down) {
        
        return true;
    }

    @Override
    public boolean mouseClicked(int x, int y, boolean left, boolean down) {
        
        return true;
    }

    @Override
    public boolean mouseMoved(int oldX, int oldY, int x, int y, boolean left, boolean right) {
        
        return true;
    }
}
