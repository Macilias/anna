/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.grammatik.wortarten;

import annalyse.grammatik.attribute.ressources.WAtt;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


/**
 * @Polski Wykrzyknik
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Interjektion.findAll", query = "SELECT a FROM Interjektion a"),
               @NamedQuery(name = "Interjektion.findByLexem", query = "SELECT a FROM Interjektion a WHERE a.lexem = :lexem")})
public class Interjektion extends Wort{
    private static final long serialVersionUID = 1L;
    private final String[] attribute = {};
    //Die Attribute einer Interjektion:
    // -keine-
    //Die Interjektion:
    private String lexem;
    private String ausdruck;
    public short language;

    public Interjektion(){
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
     * @return the lexem
     */
    @Override
    public String getLexem() {
        return lexem;
    }

    /**
     * @param lexem the lexem to set
     */
    @Override
    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    /**
     * @return the ausdruck
     */
    public String getAusdruck() {
        return ausdruck;
    }

    /**
     * @param ausdruck the ausdruck to set
     */
    public void setAusdruck(String ausdruck) {
        this.ausdruck = ausdruck;
    }

    @Override
    public String toString(){
        return getNationalClassNeme()+" "
                +this.getAusdruck()+" ID: "+this.getId();
    }

    public String toStringCLex(){
        return getNationalClassNeme()+" "+this.getLexem()+" "
                +this.getAusdruck()+" ID: "+this.getId();
    }

    /**
     * @return the attribute
     */
    @Override
    public String[] getAttribute() {
        return attribute;
    }

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Interjektion";
        if(WAtt.getLanguage()==1)return "Wykrzyknik";
        return "Interjektion";
    }
    
}
