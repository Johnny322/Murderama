package worldofzuul;

public class Character_fight 
{
    //Har brugt samme variables som i monster på nær name og boolean.
    private String name;
    private int hp;
    private int dmg;
    private boolean isDead = false;

    //Character attributer
    public Character_fight(String name, int hp) 
    {
        this.name = Ryu;
        this.hp = _hp;
        this.dmg = _dmg;
    }

    public void attack(Character_fight dmg) 
    {
        _dmg = 0;
        _dmg = (Math.random()*11);
        dmg.takeDamage(_dmg);
    }

    public void setHp(int dmg) 
    {
        //damage man tager
        //Hvis dmg er større end hp eller det er = 0: død
        if (_hp - _dmg <= 0) 
        {
            isDead = true;
        } 
        else 
        {
            _hp -= _dmg;
        }
    }

    public getHp()
    {
        return this.hp;

    }

    public getDmg()
    {
        System.out.println("HADOKEN!"); //Easter Egg :v

        return this.dmg;
    }

    //Ellers return død
    public boolean isDead() 
    {
        return isDead;
    }

}