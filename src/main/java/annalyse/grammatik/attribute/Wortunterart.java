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
public class Wortunterart extends Attribut{

    String wortart;

    /**
     * Konstructor
     * @param key
     * @param wortart 
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Wortunterart(String key, String wortart) throws UnknownKeyException{
        this.setKey(key,wortart);
        this.wortart = wortart;
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @param wortart
     * @throws UnknownKeyException
     */
    public void setKey(String key, String wortart) throws UnknownKeyException{
        if(WAtt.getWortunterart(key,wortart)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getWortunterart(this.key,wortart);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Wortunterart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean equals(Wortunterart other){
        if(this.key.equals(other.getKey()))return true;
        if(this.key.equals("mehr")&&other.getKey().equals("nebo"))return true;
        if(this.key.equals("nebo")&&other.getKey().equals("mehr"))return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals(other))return true;
        if(this.key.equals("mehr")&&other.equals("nebo"))return true;
        if(this.key.equals("nebo")&&other.equals("mehr"))return true;
        return false;
    }

}
