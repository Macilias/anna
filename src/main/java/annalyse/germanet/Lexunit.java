/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.germanet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Maciej Niemczyk
 */
@Entity
@Table(name = "lexunit")
@NamedQueries({@NamedQuery(name = "Lexunit.findAll", query = "SELECT l FROM Lexunit l"), @NamedQuery(name = "Lexunit.findByLexUnitid", query = "SELECT l FROM Lexunit l WHERE l.lexUnitid = :lexUnitid"), @NamedQuery(name = "Lexunit.findByEigenname", query = "SELECT l FROM Lexunit l WHERE l.eigenname = :eigenname"), @NamedQuery(name = "Lexunit.findByArtificial", query = "SELECT l FROM Lexunit l WHERE l.artificial = :artificial"), @NamedQuery(name = "Lexunit.findById", query = "SELECT l FROM Lexunit l WHERE l.id = :id"), @NamedQuery(name = "Lexunit.findByOrthVar", query = "SELECT l FROM Lexunit l WHERE l.orthVar = :orthVar"), @NamedQuery(name = "Lexunit.findBySense", query = "SELECT l FROM Lexunit l WHERE l.sense = :sense"), @NamedQuery(name = "Lexunit.findByStatus", query = "SELECT l FROM Lexunit l WHERE l.status = :status"), @NamedQuery(name = "Lexunit.findByStilMarkierung", query = "SELECT l FROM Lexunit l WHERE l.stilMarkierung = :stilMarkierung"), @NamedQuery(name = "Lexunit.findByOrthFormid", query = "SELECT l FROM Lexunit l WHERE l.orthFormid = :orthFormid")})
public class Lexunit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lexUnit_id")
    private Integer lexUnitid;
    @Column(name = "Eigenname")
    private String eigenname;
    @Column(name = "artificial")
    private String artificial;
    @Column(name = "id")
    private String id;
    @Column(name = "orthVar")
    private String orthVar;
    @Column(name = "sense")
    private String sense;
    @Column(name = "status")
    private String status;
    @Column(name = "stilMarkierung")
    private String stilMarkierung;
    @Column(name = "orthForm_id")
    private Integer orthFormid;

    public Lexunit() {
    }

    public Lexunit(Integer lexUnitid) {
        this.lexUnitid = lexUnitid;
    }

    public Integer getLexUnitid() {
        return lexUnitid;
    }

    public void setLexUnitid(Integer lexUnitid) {
        this.lexUnitid = lexUnitid;
    }

    public String getEigenname() {
        return eigenname;
    }

    public void setEigenname(String eigenname) {
        this.eigenname = eigenname;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrthVar() {
        return orthVar;
    }

    public void setOrthVar(String orthVar) {
        this.orthVar = orthVar;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStilMarkierung() {
        return stilMarkierung;
    }

    public void setStilMarkierung(String stilMarkierung) {
        this.stilMarkierung = stilMarkierung;
    }

    public Integer getOrthFormid() {
        return orthFormid;
    }

    public void setOrthFormid(Integer orthFormid) {
        this.orthFormid = orthFormid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lexUnitid != null ? lexUnitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lexunit)) {
            return false;
        }
        Lexunit other = (Lexunit) object;
        if ((this.lexUnitid == null && other.lexUnitid != null) || (this.lexUnitid != null && !this.lexUnitid.equals(other.lexUnitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Lexunit[lexUnitid=" + lexUnitid + "]";
    }

}
