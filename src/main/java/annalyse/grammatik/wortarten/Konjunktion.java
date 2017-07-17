/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortarten;

import annalyse.grammatik.attribute.*;
import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import annalyse.grammatik.attribute.ressources.WAtt;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * @Polski Łącznik/Spójnik (copula)
 * W języku polskim łącznikiem może być dowolna forma czasowników „być”, „bywać”, „stać się”, „zostać” oraz partykuły „to”.
 * Spójniki można podzielić na proste, jak "i", złożone, jak "podczas gdy", oraz zestawieniowe, jak "jeżeli... to".
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Konjunktion.findAll", query = "SELECT a FROM Konjunktion a"), @NamedQuery(name = "Konjunktion.findByLexem", query = "SELECT a FROM Konjunktion a WHERE a.lexem = :lexem")})
public class Konjunktion extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","ANDERERTEILID"};
    //Die Attribute einer Konjunktion:
    private Wortunterart wortunterart;
    //Die Konjunktion:
    private String lexem;
    private int andererTeilID;
    public short language;

    public Konjunktion(){
        this.setLanguage(WAtt.getLanguage());
    }

    /**
     * @return the id
     */
    @Override
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getGlobalID(){
        return globalID;
    }

    @Override
    public void setGlobalID(int globalID){
        this.globalID = globalID;
    }

    @Override
    @Transient
    public short getLanguage(){
        return this.language;
    }

    @Override
    public void setLanguage(short language){
        this.language =language;
    }

    /**
     * @return the wortunterart
     */
    public Wortunterart getWortunterartAA() {
        return wortunterart;
    }

    /**
     * @return the wortunterart
     */
    public String getWortunterart() {
        return wortunterart.getKey();
    }

    /**
     * @param wortunterart the wortunterart to set
     */
    public void setWortunterart(Wortunterart wortunterart) {
        this.wortunterart = wortunterart;
    }

    /**
     * @param wortunterart the wortunterart to set
     * @throws UnknownKeyException
     */
    public void setWortunterart(String wortunterart) throws UnknownKeyException {
        this.wortunterart = new Wortunterart(wortunterart, this.getClass().getSimpleName());
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

    /**
     * @return the andererTeilID
     */
    public int getAndererTeilID() {
        return andererTeilID;
    }

    /**
     * @param andererTeilID the andererTeilID to set
     */
    public void setAndererTeilID(int andererTeilID) {
        this.andererTeilID = andererTeilID;
    }

    @Override
    public String toString(){
        return this.getWortart()+
                " "+this.getWortunterartAA().getName()+" ID: "+this.getId();
    }

    public String toStringCLex(){
        return this.getWortart()+" "+this.getLexem()+
                " "+this.getWortunterartAA().getName()+" ID: "+this.getId();
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Konjunktion";
        if(WAtt.getLanguage()==1)return "Spójnik";
        return "Interpunktion";
    }

}
