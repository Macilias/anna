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
 * @Polski Liczebnik
 * (główny: nazwa liczby całkowitej, porządkowy: określający położenie elementu w pewnym szeregu lub zbiorowy: liczebnik określający liczność grupy elementów)
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Numeral.findAll", query = "SELECT a FROM Numeral a"),
               @NamedQuery(name = "Numeral.findByLexem", query = "SELECT a FROM Numeral a WHERE a.lexem = :lexem")})
public class Numeral extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","GENUS","KASUS"};
    //Die Attribute des Numerals:
    private Wortunterart wortunterart;
    private Kasus kasus;
    private Genus genus;
    //Das Numeral:
    private String lexem;
    private int wert;
    public short language;

    public Numeral(){
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
     * @return the wortunterunterart
     */
    public Wortunterart getWortunterartAA() {
        return wortunterart;
    }

    /**
     * @return the wortunterunterart
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
     * @return the kasus
     */
    public Kasus getKasusAA() {
        return kasus;
    }

    /**
     * @return the kasus
     */
    public String getKasus() {
        return kasus.getKey();
    }

    /**
     * @param kasus the kasus to set
     */
    public void setKasus(Kasus kasus) {
        this.kasus = kasus;
    }

    /**
     * @param kasus the kasus to set
     * @throws UnknownKeyException
     */
    public void setKasus(String kasus) throws UnknownKeyException {
        this.kasus = new Kasus(kasus);
    }

    /**
     * @return the genus
     */
    public Genus getGenusAA() {
        return genus;
    }

    /**
     * @return the genus
     */
    public String getGenus() {
        return genus.getKey();
    }

    /**
     * @param genus the genus to set
     */
    public void setGenus(Genus genus) {
        this.genus = genus;
    }

    /**
     * @param genus the genus to set
     * @throws UnknownKeyException
     */
    public void setGenus(String genus) throws UnknownKeyException {
        this.genus = new Genus(genus);
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
     * @return the wert
     */
    public int getWert() {
        return wert;
    }

    /**
     * @param wert the wert to set
     */
    public void setWert(int wert) {
        this.wert = wert;
    }

    @Override
    public String toString(){
        return getNationalClassNeme()+" "+this.getWert()+
                " "+this.getWortunterartAA().getName()+" "+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+" ID: "+this.getId();
    }

    public String toStringCLex(){
        return getNationalClassNeme()+" "+this.getLexem()+" "+this.getWert()+
                " "+this.getWortunterartAA().getName()+" "+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+" ID: "+this.getId();
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Numeral";
        if(WAtt.getLanguage()==1)return "Liczebnik";
        return "Interpunktion";
    }

}
