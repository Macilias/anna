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
public class Altus extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Altus(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getAltus(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getAltus(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Altus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean equals(Altus other){
        if(this.key.equals("unde"))return true;
        if(other.getKey().equals("unde"))return true;
        if(this.key.equals(other.getKey()))return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals("unde"))return true;
        if(other.equals("unde"))return true;
        if(this.key.equals(other))return true;
        return false;
    }

}
