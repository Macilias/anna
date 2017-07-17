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
@Table(name = "synsets")
@NamedQueries({@NamedQuery(name = "Synsets.findAll", query = "SELECT s FROM Synsets s"), @NamedQuery(name = "Synsets.findBySynsetsId", query = "SELECT s FROM Synsets s WHERE s.synsetsId = :synsetsId")})
public class Synsets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "synsets_id")
    private Integer synsetsId;

    public Synsets() {
    }

    public Synsets(Integer synsetsId) {
        this.synsetsId = synsetsId;
    }

    public Integer getSynsetsId() {
        return synsetsId;
    }

    public void setSynsetsId(Integer synsetsId) {
        this.synsetsId = synsetsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (synsetsId != null ? synsetsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Synsets)) {
            return false;
        }
        Synsets other = (Synsets) object;
        if ((this.synsetsId == null && other.synsetsId != null) || (this.synsetsId != null && !this.synsetsId.equals(other.synsetsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Synsets[synsetsId=" + synsetsId + "]";
    }

}
