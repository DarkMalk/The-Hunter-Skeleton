import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    private int points = 0;
    private int lastHealPoints = 0;
    private Heal heal;
    public MyWorld()
    {    
        super(600, 400, 1);
        setBackground("Background.png");
        heal = new Heal(this);
        prepare();
    }
    public void act() {
        checkPoints();
        if (lastHealPoints != points) {
            heal.spawnHeal(points);
        }
        lastHealPoints = points;
    }
    private void prepare() {
        Player player = new Player();
        addObject(player, 50, 355);
        
        Zombie zombie = new Zombie(player);
        addObject(zombie, zombie.INITIAL_X, zombie.INITIAL_Y);
    }
    public void addPoints(int number) {
        this.points += number;
    }
    private void checkPoints() {
        showText("", getWidth() / 2, 14);
        
        showText("Points: " + this.points, getWidth() / 2, 14);
    }
}