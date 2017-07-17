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
public class Person extends Attribut{

    /**
     * Konstructor
     * @param key
     * @throws annalyse.grammatik.attribute.ressources.UnknownKeyException
     */
    public Person(String key) throws UnknownKeyException{
        this.setKey(key);
    }

    /**
     * Definiert die Atrributart über den Schlüssel
     * @param key
     * @throws UnknownKeyException
     */
    @Override
    public void setKey(String key) throws UnknownKeyException{
        if(WAtt.getPerson(key)==null) throw new UnknownKeyException();
        this.key = key;
    }

    @Override
    public String getName(){
        try {
            return WAtt.getPerson(this.key);
        } catch (UnknownKeyException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean equals(Person other){
        if(this.key.equals("123"))return true;
        if(other.getKey().equals("123"))return true;
        if(this.key.equals(other.getKey()))return true;
        if(this.key.equals("1&2")&&(other.getKey().equals("1p")||other.getKey().equals("1&3")||other.getKey().equals("2p")||other.getKey().equals("2&3"))) return true;
        if(this.key.equals("2&3")&&(other.getKey().equals("2p")||other.getKey().equals("1&2")||other.getKey().equals("3p")||other.getKey().equals("1&3"))) return true;
        if(this.key.equals("1&3")&&(other.getKey().equals("1p")||other.getKey().equals("1&2")||other.getKey().equals("3p")||other.getKey().equals("1&3"))) return true;
        return false;
    }

    public boolean equals(String other){
        if(this.key.equals("123"))return true;
        if(other.equals("123"))return true;
        if(this.key.equals(other))return true;
        if(this.key.equals("1&2")&&(other.equals("1p")||other.equals("1&3")||other.equals("2p")||other.equals("2&3"))) return true;
        if(this.key.equals("2&3")&&(other.equals("2p")||other.equals("1&2")||other.equals("3p")||other.equals("1&3"))) return true;
        if(this.key.equals("1&3")&&(other.equals("1p")||other.equals("1&2")||other.equals("3p")||other.equals("1&3"))) return true;
        return false;
    }

}