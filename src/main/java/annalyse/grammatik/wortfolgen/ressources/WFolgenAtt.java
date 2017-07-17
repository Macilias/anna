/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortfolgen.ressources;

import annalyse.grammatik.attribute.ressources.*;
import java.util.HashMap;

/**
 *
 * @author Maciej Niemczyk
 */
public class WFolgenAtt {

    static HashMap<String,String> satzarten;

   

    //Initialisierung beim ersten Aufruf
    public static void initSatzarten(){
        satzarten = new HashMap<String,String>();
        satzarten.put("auss", "Aussagesatz");
        satzarten.put("frag", "Fragesatz");
        satzarten.put("auff", "Aufforderungssatz");
        satzarten.put("wuns", "Wunschsatz");
        satzarten.put("ausr", "Ausrufesatz");
        satzarten.put("unde", "");
    }

    public static String getSatzarten(String key) throws UnknownKeyException{
        if(satzarten==null){
           initSatzarten();
        }
        if(satzarten.get(key)==null) throw new UnknownKeyException();
        return satzarten.get(key);
    }

    public static HashMap<String,String> getKasuse(){
        if(satzarten==null){
           initSatzarten();
        }
        return satzarten;
    }

}
