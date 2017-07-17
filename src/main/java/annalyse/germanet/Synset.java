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
@Table(name = "synset")
@NamedQueries({@NamedQuery(name = "Synset.findAll", query = "SELECT s FROM Synset s"), @NamedQuery(name = "Synset.findBySynsetId", query = "SELECT s FROM Synset s WHERE s.synsetId = :synsetId"), @NamedQuery(name = "Synset.findById", query = "SELECT s FROM Synset s WHERE s.id = :id"), @NamedQuery(name = "Synset.findByWordClass", query = "SELECT s FROM Synset s WHERE s.wordClass = :wordClass")})
public class Synset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "synset_id")
    private Integer synsetId;
    @Column(name = "id")
    private String id;
    @Column(name = "wordClass")
    private String wordClass;

    public Synset() {
    }

    public Synset(Integer synsetId) {
        this.synsetId = synsetId;
    }

    public Integer getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(Integer synsetId) {
        this.synsetId = synsetId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWordClass() {
        return wordClass;
    }

    public void setWordClass(String wordClass) {
        this.wordClass = wordClass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (synsetId != null ? synsetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Synset)) {
            return false;
        }
        Synset other = (Synset) object;
        if ((this.synsetId == null && other.synsetId != null) || (this.synsetId != null && !this.synsetId.equals(other.synsetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Synset[synsetId=" + synsetId + "]";
    }

}
