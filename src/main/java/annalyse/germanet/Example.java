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
@Table(name = "example")
@NamedQueries({@NamedQuery(name = "Example.findAll", query = "SELECT e FROM Example e"), @NamedQuery(name = "Example.findByExampleId", query = "SELECT e FROM Example e WHERE e.exampleId = :exampleId"), @NamedQuery(name = "Example.findByTextId", query = "SELECT e FROM Example e WHERE e.textId = :textId")})
public class Example implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "example_id")
    private Integer exampleId;
    @Column(name = "text_id")
    private Integer textId;

    public Example() {
    }

    public Example(Integer exampleId) {
        this.exampleId = exampleId;
    }

    public Integer getExampleId() {
        return exampleId;
    }

    public void setExampleId(Integer exampleId) {
        this.exampleId = exampleId;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exampleId != null ? exampleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Example)) {
            return false;
        }
        Example other = (Example) object;
        if ((this.exampleId == null && other.exampleId != null) || (this.exampleId != null && !this.exampleId.equals(other.exampleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "annalyse.germanet.Example[exampleId=" + exampleId + "]";
    }

}
