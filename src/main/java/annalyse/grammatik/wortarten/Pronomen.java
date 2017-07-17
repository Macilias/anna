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
 * @Polski Zaimek
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Pronomen.findAll", query = "SELECT a FROM Pronomen a"),
               @NamedQuery(name = "Pronomen.findByLexem", query = "SELECT a FROM Pronomen a WHERE a.lexem = :lexem")})
public class Pronomen extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","GENUS","KASUS","PERSON","NUMERUS"};
    //Die Attribute des Pronomens:
    private Wortunterart wortunterart;
    private Person person;
    private Numerus numerus;
    private Genus genus;
    //Die zusätzlichen Attribute eines Reflexivpronomens:
    private Kasus kasus;
    //Der Pronomen:
    private String lexem;
    public short language;

    public Pronomen(){
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
        this.wortunterart = new Wortunterart(wortunterart,this.getClass().getSimpleName());
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
        return getNationalClassNeme()+" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                " ID: "+this.getId();
    }

    public String toStringCLex(){
        return getNationalClassNeme()+" "+this.getLexem()+" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                " ID: "+this.getId();
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Pronomen";
        if(WAtt.getLanguage()==1)return "Zaimek";
        return "Pronomen";
    }

}
