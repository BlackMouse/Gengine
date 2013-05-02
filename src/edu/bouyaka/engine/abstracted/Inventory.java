package edu.bouyaka.engine.abstracted;

import edu.bouyaka.engine.Abstract;
import edu.bouyaka.engine.concreted.Item;

public class Inventory extends Abstract {
	
	Item[] inventaire = new Item[1];
	
	// Constructeur définissant la taille de l'inventaire
	
	public Inventory (int a){
		Item[] iTmp = new Item[a];
		inventaire = iTmp;
		
	}
	
	// Ajoute un objet dans la case de l'inventaire voulue
	
	public void addItem (int id, Item item){
		inventaire[id]= item;
	}
	
	// Supprime l'item de la case de l'inventaire voulue
	
	public void delItem (int id){
		inventaire[id]= null;
	}
	
	// Redéfini la taille de l'inventaire
	
	public void setLength (int a){
		Item[] iTmp = new Item[a];
		inventaire = iTmp;
	}
	
	// Renvoie l'objet de la case voulue
	
	public Item getItem (int id){
		return inventaire[id];
	}
	
	// Renvoie la taille de l'inventaire
	
	public int getLength(){
		return inventaire.length;
	}
	
	// Renvoie si l'inventaire est vide
	
	public boolean getEmpty (){
		for(int i = 0 ; i< inventaire.length; i++)
		{
			if (inventaire[i] != null) return false;
		}
		return true;
	}

}
