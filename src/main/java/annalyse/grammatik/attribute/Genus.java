/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.attribute;

import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import annalyse.grammatik.attribute.ressources.WAtt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maciej Niemczyk
 */
public class Genus extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Genus(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getGenus(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getGenus(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Genus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Genus other){
        if(this.key.equals("mwu"))return true;
        if(other.getKey().equals("mwu"))return true;
        if(this.key.equals("m&u")&&((other.getKey().equals("männ"))||other.getKey().equals("unde")))return true;
        if(other.getKey().equals("m&u")&&((this.getKey().equals("männ"))||this.getKey().equals("unde")))return true;
        if(this.key.equals(other.getKey()))return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals("mwu"))return true;
        if(other.equals("mwu"))return true;
        if(this.key.equals("m&u")&&((other.equals("männ"))||other.equals("unde")))return true;
        if(other.equals("m&u")&&((this.equals("männ"))||this.equals("unde")))return true;
        if(this.key.equals(other))return true;
        return false;
    }

}
