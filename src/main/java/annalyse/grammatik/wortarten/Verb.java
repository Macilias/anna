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
 * @Polski Czasownik
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Verb.findAll", query = "SELECT a FROM Verb a"),
               @NamedQuery(name = "Verb.findByLexem", query = "SELECT a FROM Verb a WHERE a.lexem = :lexem"),
               @NamedQuery(name = "Verb.findByInfinitiv", query = "SELECT a FROM Verb a WHERE a.infinitiv = :infinitiv")})
public class Verb extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","MODUS","TEMPUS","ALTUS","PERSON","NUMERUS","GENUS"};
    //Die Attribute des Verben:
    private Wortunterart wortunterart;
    private Modus modus;
    private Tempus tempus;
    private Altus altus;
    private Genus genus;  //nur im polischen zutreffend (dafür kein artikel)
    private Person person;
    private Numerus numerus;
    //Das Verb in seiner und Infinitivform:
    private String lexem;
    //@Polski bezokolicznik
    private String infinitiv;
    private int hilfsverb;
    public short language;

    public Verb(){
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
     * @return the modus
     */
    public Modus getModusAA() {
        return modus;
    }

    /**
     * @return the modus
     */
    public String getModus() {
        return modus.getKey();
    }

    /**
     * @param modus the modus to set
     */
    public void setModus(Modus modus) {
        this.modus = modus;
    }

    /**
     * @param modus the modus to set
     * @throws UnknownKeyException
     */
    public void setModus(String modus) throws UnknownKeyException {
        this.modus = new Modus(modus);
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
     * @return the tempus
     */
    public Tempus getTempusAA() {
        return tempus;
    }

    /**
     * @return the tempus
     */
    public String getTempus() {
        return tempus.getKey();
    }

    /**
     * @param tempus the tempus to set
     */
    public void setTempus(Tempus tempus) {
        this.tempus = tempus;
    }

    /**
     * @param tempus the tempus to set
     * @throws UnknownKeyException
     */
    public void setTempus(String tempus) throws UnknownKeyException {
        this.tempus = new Tempus(tempus);
    }

    /**
     * @return the altus
     */
    public Altus getAltusAA() {
        return altus;
    }

    /**
     * @return the altus
     */
    public String getAltus() {
        return altus.getKey();
    }

    /**
     * @param altus the altus to set
     */
    public void setAltus(Altus altus) {
        this.altus = altus;
    }

    /**
     * @param altus the altus to set
     * @throws UnknownKeyException
     */
    public void setAltus(String altus) throws UnknownKeyException {
        this.altus = new Altus(altus);
    }

    /**
     * @return the person
     */
    public Person getPersonAA() {
        return person;
    }

    /**
     * @return the person
     */
    public String getPerson() {
        return person.getKey();
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @param person the person to set
     * @throws UnknownKeyException
     */
    public void setPerson(String person) throws UnknownKeyException {
        this.person = new Person(person);
    }

    /**
     * @return the numerus
     */
    public Numerus getNumerusAA() {
        return numerus;
    }

    /**
     * @return the numerus
     */
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
     * @return the infinitiv
     */
    public String getInfinitiv() {
        return infinitiv;
    }

    /**
     * @param infinitiv the infinitiv to set
     */
    public void setInfinitiv(String infinitiv) {
        this.infinitiv = infinitiv;
    }

    /**
     * @return the hilfsverb
     */
    public int getHilfsverb() {
        return hilfsverb;
    }

    /**
     * @param hilfsverb the hilfsverb to set
     */
    public void setHilfsverb(int hilfsverb) {
        this.hilfsverb = hilfsverb;
    }

    @Override
    public String toString(){
        String hilfsv="";
        if(this.hilfsverb>0) hilfsv= " ID des Hilfsverbs "+String.valueOf(this.getHilfsverb());
        return getNationalClassNeme()+" infi: \""+this.getInfinitiv()+
                "\" "+this.getWortunterartAA().getName()+" "+this.getModusAA().getName()+
                " "+this.getTempusAA().getName()+" "+this.getAltusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                hilfsv+" ID: "+this.getId();
    }

    public String toStringCLex(){
        String hilfsv="";
        if(this.hilfsverb>0) hilfsv= " ID des Hilfsverbs "+String.valueOf(this.getHilfsverb());
        return getNationalClassNeme()+" "+this.getLexem()+" infi: \""+this.getInfinitiv()+
                "\" "+this.getWortunterartAA().getName()+" "+this.getModusAA().getName()+
                " "+this.getTempusAA().getName()+" "+this.getAltusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                hilfsv+" ID: "+this.getId();
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Verb";
        if(WAtt.getLanguage()==1)return "Czasownik";
        return "Verb";
    }

}
