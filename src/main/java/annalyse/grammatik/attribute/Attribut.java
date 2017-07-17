/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.attribute;

import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import javax.persistence.Id;

/**
 *
 * @author Maciej Niemczyk
 */
public class Attribut {
    @Id
    String key;

    //Beim unbekannten Attributen findet in wirklichkeit
    //keine Prüfung des Schlüssels statt, diese gilt nur
    //für die erbenden Klassen
    public void setKey(String key) throws UnknownKeyException{
        if(key.equals("")) throw new UnknownKeyException();
        this.key = key;
    }

    public String getKey(){
        return this.key;
    }

    public String getName(){
        return "unbenannter Attribut";
    }

    public boolean equals(Attribut other){
        if(this.key.equals(other.getKey())&&this.getClass().equals(other.getClass()))return true;
        return false;
    }

}
