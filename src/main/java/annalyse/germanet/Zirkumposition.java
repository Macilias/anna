/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package annalyse.germanet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Maciej Niemczyk
 */
@Entity
@Table(name = "zirkumposition")
@NamedQueries({@NamedQuery(name = "Zirkumposition.findAll", query = "SELECT z FROM Zirkumposition z"), @NamedQuery(name = "Zirkumposition.findById", query = "SELECT z FROM Zirkumposition z WHERE z.id = :id"), @NamedQuery(name = "Zirkumposition.findByGlobalID", query = "SELECT z FROM Zirkumposition z WHERE z.globalID = :globalID"), @NamedQuery(name = "Zirkumposition.findByPräposition", query = "SELECT z FROM Zirkumposition z WHERE z.präposition = :präposition"), @NamedQuery(name = "Zirkumposition.findByPostposition", query = "SELECT z FROM Zirkumposition z WHERE z.postposition = :postposition"), @NamedQuery(name = "Zirkumposition.findByKasus", query = "SELECT z FROM Zirkumposition z WHERE z.kasus = :kasus"), @NamedQuery(name = "Zirkumposition.findByUmstand", query = "SELECT z FROM Zirkumposition z WHERE z.umstand = :umstand")})
public class Zirkumposition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "globalID")
    private int globalID;
    @Basic(optional = false)
    @Column(name = "präposition")
    private int präposition;
    @Basic(optional = false)
    @Column(name = "postposition")
    private int postposition;
    @Basic(optional = false)
    @Column(name = "kasus")
    private String kasus;
    @Basic(optional = false)
    @Column(name = "umstand")
    private String umstand;

    public Zirkumposition() {
    }

    public Zirkumposition(Integer id) {
        this.id = id;
    }

    public Zirkumposition(Integer id, int globalID, int präposition, int postposition, String kasus, String umstand) {
        this.id = id;
        this.globalID = globalID;
        this.präposition = präposition;
        this.postposition = postposition;
        this.kasus = kasus;
        this.umstand = umstand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGlobalID() {
        return globalID;
    }

    public void setGlobalID(int globalID) {
        this.globalID = globalID;
    }

    public int getPräposition() {
        return präposition;
    }

    public void setPräposition(int präposition) {
        this.präposition = präposition;
    }

    public int getPostposition() {
        return postposition;
    }

    public void setPostposition(int postposition) {
        this.postposition = postposition;
    }

    public String getKasus() {
        return kasus;
    }

    public void setKasus(String kasus) {
        this.kasus = kasus;
    }

    public String getUmstand() {
        return umstand;
    }

    public void setUmstand(String umstand) {
        this.umstand = umstand;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zirkumposition)) {
            return false;
        }
        Zirkumposition other = (Zirkumposition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Zirkumposition[id=" + id + "]";
    }

}
