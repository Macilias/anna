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
public class Modus extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Modus(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getModus(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getModus(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Modus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean equals(Modus other){
        if(this.key.equals(other.getKey()))return true;
        if(this.key.equals("ko12")&&(other.getKey().equals("kon1")||other.getKey().equals("kon2")))return true;
        if((this.key.equals("kon1")||this.key.equals("kon2"))&&(other.getKey().equals("ko13")))return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals(other))return true;
        if(this.key.equals("ko12")&&(other.equals("kon1")||other.equals("kon2")))return true;
        if((this.key.equals("kon1")||this.key.equals("kon2"))&&(other.equals("ko13")))return true;
        return false;
    }

}
