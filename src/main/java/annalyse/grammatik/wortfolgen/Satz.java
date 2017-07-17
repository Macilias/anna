/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortfolgen;

import annalyse.grammatik.wortarten.*;
import java.util.LinkedList;

/**
 * @Polski Zdanie
 * @author Maciej Niemczyk
 */
public class Satz {
    //Beide Attribute aus der Menge WFolgenAtt.Satzarten
    private String formalArt;
    private String inhaltlicheArt;
    private LinkedList<Phrase> phrasen;
}
