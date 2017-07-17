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
 * @Polski Przyimek
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Präposition.findAll", query = "SELECT a FROM Präposition a"),
               @NamedQuery(name = "Präposition.findByLexem", query = "SELECT a FROM Präposition a WHERE a.lexem = :lexem")})
public class Präposition extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {"KASUS","UMSTAND"};
    //Die Attribute einer Präposition:
    private Kasus kasus;
    private Umstand umstand;
    //Die Präposition:
    private String lexem;
    public short language;

    public Präposition(){
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
     * @return the umstand
     */
    public Umstand getUmstandAA() {
        return umstand;
    }

    /**
     * @return the umstand
     */
    public String getUmstand() {
        return umstand.getKey();
    }

    /**
     * @param umstand the umstand to set
     */
    public void setUmstand(Umstand umstand) {
        this.umstand = umstand;
    }

    /**
     * @param umstand the umstand to set
     * @throws UnknownKeyException
     */
    public void setUmstand(String umstand) throws UnknownKeyException {
        this.umstand = new Umstand(umstand);
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
        String out = null;
        if(WAtt.getLanguage()==0) out = getNationalClassNeme()+" "+this.getKasusAA().getName()+" "+this.getUmstandAA().getName()+" Bestimmung des Umstands ID: "+this.getId();
        if(WAtt.getLanguage()==1) out = getNationalClassNeme()+" "+this.getKasusAA().getName()+" Okolicznik"+this.getUmstandAA().getName()+" ID: "+this.getId();
        return out;
    }

    public String toStringCLex(){
        String out = null;
        if(WAtt.getLanguage()==0) out = getNationalClassNeme()+" "+this.getLexem()+" "+this.getKasusAA().getName()+" "+this.getUmstandAA().getName()+" Bestimmung des Umstands ID: "+this.getId();
        if(WAtt.getLanguage()==1) out = getNationalClassNeme()+" "+this.getLexem()+" "+this.getKasusAA().getName()+" Okolicznik"+this.getUmstandAA().getName()+" ID: "+this.getId();
        return out;
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Präposition";
        if(WAtt.getLanguage()==1)return "Przyimek";
        return "Präposition";
    }

}
