import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Heal extends Actor
{
    private boolean existHeal = false;
    private int amount = 5;
    
    private World world;
    public Heal(World world) {
        setImage("Heal.png");
        this.world = world;
    }
    public int amountHeal() {
        return this.amount;
    }
    public void spawnHeal(int points) {
        if (existHeal) {
            return;
        }
        
        int randomX = Greenfoot.getRandomNumber(world.getWidth());
        int y = 355;
        if (points % 10 == 0 && points > 0) {
            world.addObject(this, randomX, y);
            existHeal = true;
        }
    }
    public void removeHeal() {
        this.existHeal = false;
        world.removeObject(this);
    }
    public boolean isExistHeal() {
        return this.existHeal;
    }
}
