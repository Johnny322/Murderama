/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;



/**
 *
 * @author Simon
 */
public class Monster {
    
    private int hp, damage;
    
    Monster(int hp, int damage) {
        this.hp = hp;
        this.damage = damage;
    }
    
    Monster() {
        hp = 10;
        damage = 2;
    } 
    
    public int getHp() {
        return this.hp;
    }
    
    public void setHp(int damageTaken) {
    	this.hp = this.hp - damageTaken;
    }
    
    public int getDamage() {
    	return this.damage;
    }
    
    public boolean alive() {
        if(this.hp > 0) {
            return true;
        }
        return false;
    }
    
}