/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortarten;

import annalyse.grammatik.attribute.ressources.WAtt;
import java.io.Serializable;

/**
 * @Polski Słowo
 * @author Maciej Niemczyk
 */
public class Wort implements Serializable{

    private final String[] attribute = {};
    public short language;
    public int id;
    public int globalID = 1;
    public String lexem;

    public Wort(){
//        this.setLanguage(WAtt.getLanguage());
    }
    /**
     * @return the wortart
     */
    public String getWortart() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the lexem
     */
    public String getLexem() {
        return lexem;
    }

    /**
     * @param lexem the lexem to set
     */
    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    @Override
    public String toString(){
        return this.getWortart()+" "+this.getLexem()+" ID: "+this.getId();
    }

    /**
     * @return the attribue
     */
    public String[] getAttribute() {
        return attribute;
    }

    /**
     * @return the language
     */
    public short getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(short language) {
        this.language = language;
    }

    /**
     * @return the idWN
     */
    public int getGlobalID() {
        return globalID;
    }

    /**
     * @param idWN the idWN to set
     */
    public void setGlobalID(int globalID) {
        if(globalID>0)
            this.globalID = globalID;
        else
            this.globalID = 0;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Wort";
        if(WAtt.getLanguage()==1)return "Słowo";
        return "Wort";
    }
}
