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
@Table(name = "paraphrase")
@NamedQueries({@NamedQuery(name = "Paraphrase.findAll", query = "SELECT p FROM Paraphrase p"), @NamedQuery(name = "Paraphrase.findByParaphraseId", query = "SELECT p FROM Paraphrase p WHERE p.paraphraseId = :paraphraseId"), @NamedQuery(name = "Paraphrase.findByText", query = "SELECT p FROM Paraphrase p WHERE p.text = :text")})
public class Paraphrase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "paraphrase_id")
    private Integer paraphraseId;
    @Column(name = "_text")
    private String text;

    public Paraphrase() {
    }

    public Paraphrase(Integer paraphraseId) {
        this.paraphraseId = paraphraseId;
    }

    public Integer getParaphraseId() {
        return paraphraseId;
    }

    public void setParaphraseId(Integer paraphraseId) {
        this.paraphraseId = paraphraseId;
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
        hash += (paraphraseId != null ? paraphraseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paraphrase)) {
            return false;
        }
        Paraphrase other = (Paraphrase) object;
        if ((this.paraphraseId == null && other.paraphraseId != null) || (this.paraphraseId != null && !this.paraphraseId.equals(other.paraphraseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Paraphrase[paraphraseId=" + paraphraseId + "]";
    }

}
