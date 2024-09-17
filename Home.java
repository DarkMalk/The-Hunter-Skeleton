import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Home extends World
{
    public Home()
    {    
        super(600, 400, 1);
        setBackground("Background.png");
        prepare();
    }
    private void prepare() {
        showText("Press 'Enter' to start", getWidth() / 2, getHeight() / 2);
    }
    
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
