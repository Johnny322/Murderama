package worldofzuul;

public class Character_fight 
{
    //Har brugt samme variables som i monster på nær name og boolean.
    private String name;
    private int hp;
    private int dmg;
    private boolean isDead = false;

    //Character attributer
    public Character_fight(String _name, int _hp) 
    {
        this._name = name;
        this._hp = hp;
        this._dmg = dmg;
    }

    public void attack(Character_fight _dmg) 
    {
        dmg = 0;
        dmg = (Math.random()*11);
        _dmg.takeDamage(dmg);
    }

    public void setHp(int dmg) 
    {
        //damage man tager
        //Hvis dmg er større end hp eller det er = 0: død
        if (hp - dmg <= 0) 
        {
            isDead = true;
        } 
        else 
        {
            hp -= dmg;
        }
    }

    public getHp()
    {
        return this.hp;

    }

    public getDmg()
    {
        return this._dmg;
    }

    //Ellers return død
    public boolean isDead() 
    {
        return isDead;
    }

}