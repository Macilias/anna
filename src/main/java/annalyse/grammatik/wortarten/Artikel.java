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
 * @Polski Rodzajnik/Przedimek
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"), 
               @NamedQuery(name = "Artikel.findByLexem", query = "SELECT a FROM Artikel a WHERE a.lexem = :lexem"),
               @NamedQuery(name = "Artikel.findByLexemAndNumeral", query = "SELECT a FROM Artikel a WHERE a.lexem = :lexem AND a.numerus = :numerus")})
public class Artikel extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"WORTUNTERART","GENUS","KASUS","PERSON","NUMERUS"};
    //Atributte des Artikels:
    private Wortunterart wortunterart;
    private Genus genus;
    private Kasus kasus;
    private Person person;
    private Numerus numerus;
    //Der Artikel:
    private String lexem;
    public short language;

    public Artikel(){
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

    @Override
    public String toString(){
        return this.getWortart()+" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                " ID: "+this.getId();
    }

    public String toStringCLex(){
        return this.getWortart()+" "+this.getLexem()+" "+this.getWortunterartAA().getName()+
                " "+this.getGenusAA().getName()+" "+this.getKasusAA().getName()+
                " "+this.getPersonAA().getName()+" "+this.getNumerusAA().getName()+
                " ID: "+this.getId();
    }

    public boolean fitts(Nomen n){
        if(this.getKasusAA().equals(n.getKasusAA())&&this.getGenusAA().equals(n.getGenusAA())&&this.getNumerusAA().equals(n.getNumerusAA())) return true;
        return false;
    }

    /**
     *
     * @param a
     * @return
     */
    public boolean fitts(Adjektiv a){
        if(this.getKasusAA().equals(a.getKasusAA())&&this.getGenusAA().equals(a.getGenusAA())&&this.getNumerusAA().equals(a.getNumerusAA())) return true;
        return false;
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Artikel";
        if(WAtt.getLanguage()==1)return "Rodzajnik";
        return "Artikel";
    }
  
}
