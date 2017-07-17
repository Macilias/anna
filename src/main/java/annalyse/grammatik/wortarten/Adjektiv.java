/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortarten;

import annalyse.grammatik.attribute.*;
import annalyse.grammatik.attribute.ressources.UnknownKeyException;
import annalyse.grammatik.attribute.ressources.WAtt;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


/**
 * @Polski Przymiotnik
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Adjektiv.findAll", query = "SELECT a FROM Adjektiv a"),
               @NamedQuery(name = "Adjektiv.findByLexem", query = "SELECT a FROM Adjektiv a WHERE a.lexem = :lexem"), 
               @NamedQuery(name = "Adjektiv.findByGrundform", query = "SELECT a FROM Adjektiv a WHERE a.grundform = :grundform"),
               @NamedQuery(name = "Adjektiv.findBySteigerung", query = "SELECT a FROM Adjektiv a WHERE a.steigerung = :steigerung"),
               @NamedQuery(name = "Adjektiv.findByKasus", query = "SELECT a FROM Adjektiv a WHERE a.kasus = :kasus"),
               @NamedQuery(name = "Adjektiv.findByGenus", query = "SELECT a FROM Adjektiv a WHERE a.genus = :genus"),
               @NamedQuery(name = "Adjektiv.findByNumerus", query = "SELECT a FROM Adjektiv a WHERE a.numerus = :numerus")})
public class Adjektiv extends Wort {
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","STEIGERUNG","GENUS","KASUS","NUMERUS"};
    //Die Attribute des Adjektivs:
    private Wortunterart wortunterart;
    private Steigerung steigerung;
    private Kasus kasus;
    private Genus genus;
    private Numerus numerus;
    //Der Adjektiv in seiner und grundformform:
    private String lexem;
    private String grundform;
    private short language;

    public Adjektiv(){
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
     */
    public void setWortunterart(String wortunterart) throws UnknownKeyException {
        this.wortunterart = new Wortunterart(wortunterart, this.getClass().getSimpleName());
    }

    @Transient
    public Steigerung getSteigerungAA() {
        return steigerung;
    }

    /**
     * @return the steigerung
     */
    @Column(name="steigerung")
    public String getSteigerung() {
        return steigerung.getKey();
    }

    /**
     * @param steigerung the steigerung to set
     */
    public void setSteigerung(Steigerung steigerung) {
        this.steigerung = steigerung;
    }

    /**
     * @param steigerung the steigerung to set
     * @throws UnknownKeyException
     */
    public void setSteigerung(String steigerung) throws UnknownKeyException {
        this.steigerung = new Steigerung(steigerung);
    }

    /**
     * @return the kasus as Attribut
     */
    @Transient
    public Kasus getKasusAA() {
        return kasus;
    }

    /**
     * @return the kasus
     */
    @Column(name="kasus")
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
     * @return the genus as Attribut
     */
    @Transient
    public Genus getGenusAA() {
        return genus;
    }

    /**
     * @return the genus
     */
    @Column(name="genus")
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
     * @return the numerus as Attribut
     */
    @Transient
    public Numerus getNumerusAA() {
        return numerus;
    }

    /**
     * @return the numerus
     */
    @Column(name="numerus")
    public String getNumerus() {
        return numerus.getKey();
    }

    /**
     * @param numerus the numerus to set
     */
    public void setNumerus(Numerus numerus) {
        this.numerus = numerus;
    }

    /**
     * @param numerus the numerus to set
     * @throws UnknownKeyException
     */
    public void setNumerus(String numerus) throws UnknownKeyException {
        this.numerus = new Numerus(numerus);
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
     * @return the grundform
     */
    public String getGrundform() {
        return grundform;
    }

    /**
     * @param grundform the grundform to set
     */
    public void setGrundform(String grundform) {
        this.grundform = grundform;
    }

    @Override
    public String toString(){
        return getNationalClassNeme()+" \""+this.getGrundform()+ "\" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getNumerusAA().getName()+
                " "+this.getSteigerungAA().getName()+" ID: "+this.getId();
    }

    public String toStringCLex(){
        return getNationalClassNeme()+" "+this.getLexem()+" \""+this.getGrundform()+ "\" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getNumerusAA().getName()+
                " "+this.getSteigerungAA().getName()+" ID: "+this.getId();
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Adjektiv";
        if(WAtt.getLanguage()==1)return "Przymiotnik";
        return "Adjektiv";
    }

}
