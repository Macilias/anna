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
@Table(name = "orthform")
@NamedQueries({@NamedQuery(name = "Orthform.findAll", query = "SELECT o FROM Orthform o"), @NamedQuery(name = "Orthform.findByOrthFormid", query = "SELECT o FROM Orthform o WHERE o.orthFormid = :orthFormid"), @NamedQuery(name = "Orthform.findByText", query = "SELECT o FROM Orthform o WHERE o.text = :text")})
public class Orthform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orthForm_id")
    private Integer orthFormid;
    @Column(name = "_text")
    private String text;

    public Orthform() {
    }

    public Orthform(Integer orthFormid) {
        this.orthFormid = orthFormid;
    }

    public Integer getOrthFormid() {
        return orthFormid;
    }

    public void setOrthFormid(Integer orthFormid) {
        this.orthFormid = orthFormid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orthFormid != null ? orthFormid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orthform)) {
            return false;
        }
        Orthform other = (Orthform) object;
        if ((this.orthFormid == null && other.orthFormid != null) || (this.orthFormid != null && !this.orthFormid.equals(other.orthFormid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Orthform[orthFormid=" + orthFormid + "]";
    }

}
