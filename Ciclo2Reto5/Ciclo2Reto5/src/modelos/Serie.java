/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author norma
 */
@Entity
@Table(name = "serie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
    , @NamedQuery(name = "Serie.findBySerId", query = "SELECT s FROM Serie s WHERE s.serId = :serId")
    , @NamedQuery(name = "Serie.findBySerEpisodios", query = "SELECT s FROM Serie s WHERE s.serEpisodios = :serEpisodios")
    , @NamedQuery(name = "Serie.findBySerTemporadas", query = "SELECT s FROM Serie s WHERE s.serTemporadas = :serTemporadas")})
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ser_id")
    private Integer serId;
    @Column(name = "ser_episodios")
    private Integer serEpisodios;
    @Column(name = "ser_temporadas")
    private Integer serTemporadas;
    @JoinColumn(name = "ser_id", referencedColumnName = "con_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Contenido contenido;

    public Serie() {
    }

    public Serie(Integer serId) {
        this.serId = serId;
    }

    public Integer getSerId() {
        return serId;
    }

    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    public Integer getSerEpisodios() {
        return serEpisodios;
    }

    public void setSerEpisodios(Integer serEpisodios) {
        this.serEpisodios = serEpisodios;
    }

    public Integer getSerTemporadas() {
        return serTemporadas;
    }

    public void setSerTemporadas(Integer serTemporadas) {
        this.serTemporadas = serTemporadas;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serId != null ? serId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.serId == null && other.serId != null) || (this.serId != null && !this.serId.equals(other.serId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Serie[ serId=" + serId + " ]";
    }
    
}
