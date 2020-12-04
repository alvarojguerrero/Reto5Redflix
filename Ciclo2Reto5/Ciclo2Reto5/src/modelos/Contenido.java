/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author norma
 */
@Entity
@Table(name = "contenido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenido.findAll", query = "SELECT c FROM Contenido c")
    , @NamedQuery(name = "Contenido.findByConId", query = "SELECT c FROM Contenido c WHERE c.conId = :conId")
    , @NamedQuery(name = "Contenido.findByConNombre", query = "SELECT c FROM Contenido c WHERE c.conNombre = :conNombre")})
public class Contenido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "con_id")
    private Integer conId;
    @Column(name = "con_nombre")
    private String conNombre;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contenido", fetch = FetchType.EAGER)
    private Serie serie;

    public Contenido() {
    }

    public Contenido(Integer conId) {
        this.conId = conId;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public String getConNombre() {
        return conNombre;
    }

    public void setConNombre(String conNombre) {
        this.conNombre = conNombre;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conId != null ? conId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenido)) {
            return false;
        }
        Contenido other = (Contenido) object;
        if ((this.conId == null && other.conId != null) || (this.conId != null && !this.conId.equals(other.conId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Contenido[ conId=" + conId + " ]";
    }
    
}
