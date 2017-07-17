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
public class Kasus extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Kasus(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getKasus(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getKasus(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Kasus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param other
     * @return
     */
    public boolean equals(Kasus other){
        if(this.key.equals(other.getKey()))return true;
        if(this.key.equals("unde"))return true;
        if(other.getKey().equals("unde"))return true;
        if(this.key.equals("gda")&&(other.getKey().equals("geni")||other.getKey().equals("dati")||other.getKey().equals("akku")))return true;
        if((this.getKey().equals("geni")||this.getKey().equals("dati")||this.getKey().equals("akku"))&&(other.getKey().equals("gda")))return true;
        if(this.key.equals("geda")&&(other.getKey().equals("geni")||other.getKey().equals("dati")))return true;
        if((this.getKey().equals("geni")||this.getKey().equals("dati"))&&(other.getKey().equals("geda")))return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals(other))return true;
        if(this.key.equals("unde"))return true;
        if(other.equals("unde"))return true;
        if(this.key.equals("gda")&&(other.equals("geni")||other.equals("dati")||other.equals("akku")))return true;
        if((this.equals("geni")||this.equals("dati")||this.equals("akku"))&&(other.equals("gda")))return true;
        if(this.key.equals("geda")&&(other.equals("geni")||other.equals("dati")))return true;
        if((this.equals("geni")||this.equals("dati"))&&(other.equals("geda")))return true;
        return false;
    }
}
