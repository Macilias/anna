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
@Table(name = "text")
@NamedQueries({@NamedQuery(name = "Text.findAll", query = "SELECT t FROM Text t"), @NamedQuery(name = "Text.findByTextId", query = "SELECT t FROM Text t WHERE t.textId = :textId"), @NamedQuery(name = "Text.findByText", query = "SELECT t FROM Text t WHERE t.text = :text")})
public class Text implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "text_id")
    private Integer textId;
    @Column(name = "_text")
    private String text;

    public Text() {
    }

    public Text(Integer textId) {
        this.textId = textId;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
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
        hash += (textId != null ? textId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Text)) {
            return false;
        }
        Text other = (Text) object;
        if ((this.textId == null && other.textId != null) || (this.textId != null && !this.textId.equals(other.textId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Text[textId=" + textId + "]";
    }

}
