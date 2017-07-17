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
public class Tempus extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Tempus(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getTempus(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getTempus(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Tempus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean equals(Tempus other){
        if(this.key.equals(other.getKey()))return true;
        if(this.key.equals("pepl")&&(other.getKey().equals("perf")||other.getKey().equals("plus"))) return true;
        if((this.key.equals("perf")||(this.key.equals("plus")))&&other.getKey().equals("pepl")) return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals(other))return true;
        if(this.key.equals("pepl")&&(other.equals("perf")||other.equals("plus"))) return true;
        if((this.key.equals("perf")||(this.key.equals("plus")))&&other.equals("pepl")) return true;
        return false;
    }

}
