package edu.bouyaka.testGame;

import edu.bouyaka.engine.Concrete;
import edu.bouyaka.engine.Player;

public class Humain extends Player{
	public Humain(){
		super();
		type="humain";
	}
	
	
	public void collideTo(Concrete E){
		if(E.getType()=="ombre"){
			
			
			
			if (getY() - E.getY() > 0 && getX() - E.getX() < 0) {
				moveLeft(2);
				moveDown(2);

			} else if (getY() - E.getY() > 0 && getX() - E.getX() > 0) {
				moveRight(2);
				moveDown(2);

			} else if (getY() - E.getY() < 0 && getX() - E.getX() < 0) {
				moveLeft(2);
				moveUp(2);

			} else if (getY() - E.getY() < 0 && getX() - E.getX() > 0) {
				moveRight(2);
				moveUp(2);

			}
		}
	}
}
