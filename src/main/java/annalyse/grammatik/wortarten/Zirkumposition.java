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
 * @Polski -niema-
 * @author Maciej Niemczyk
 */
@Entity
@NamedQueries({@NamedQuery(name = "Zirkumposition.findAll", query = "SELECT a FROM Zirkumposition a"),
               @NamedQuery(name = "Zirkumposition.findByPräposition", query = "SELECT a FROM Zirkumposition a WHERE a.präposition = :präposition")})
public class Zirkumposition extends Wort {
    private int präposition;
    private int postposition;
    public short language;

    public Zirkumposition(){
        this.setLanguage(WAtt.getLanguage());
    }

    /**
     * @return the präposition
     */
    public int getPräposition() {
        return präposition;
    }

    /**
     * @param präposition the präposition to set
     */
    public void setPräposition(int präposition) {
        this.präposition = präposition;
    }

    /**
     * @return the postposition
     */
    public int getPostposition() {
        return postposition;
    }

    /**
     * @param postposition the postposition to set
     */
    public void setPostposition(int postposition) {
        this.postposition = postposition;
    }

    /**
     * @return the id
     */
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
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

    public static String getNationalClassNeme(){
        if(WAtt.getLanguage()==0)return "Zirkumposition";
        if(WAtt.getLanguage()==1)return "otaczyjący za- i przyimek";
        return "Zirkumposition";
    }

}
