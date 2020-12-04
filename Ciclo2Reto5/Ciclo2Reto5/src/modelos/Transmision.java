/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author norma
 */
@Entity
@Table(name = "transmision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transmision.findAll", query = "SELECT t FROM Transmision t")
    , @NamedQuery(name = "Transmision.findByTraUsuario", query = "SELECT t FROM Transmision t WHERE t.transmisionPK.traUsuario = :traUsuario")
    , @NamedQuery(name = "Transmision.findByTraContenido", query = "SELECT t FROM Transmision t WHERE t.transmisionPK.traContenido = :traContenido")
    , @NamedQuery(name = "Transmision.findByTraFechaHora", query = "SELECT t FROM Transmision t WHERE t.transmisionPK.traFechaHora = :traFechaHora")})
public class Transmision implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransmisionPK transmisionPK;

    public Transmision() {
    }

    public Transmision(TransmisionPK transmisionPK) {
        this.transmisionPK = transmisionPK;
    }

    public Transmision(String traUsuario, int traContenido, Date traFechaHora) {
        this.transmisionPK = new TransmisionPK(traUsuario, traContenido, traFechaHora);
    }

    public TransmisionPK getTransmisionPK() {
        return transmisionPK;
    }

    public void setTransmisionPK(TransmisionPK transmisionPK) {
        this.transmisionPK = transmisionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transmisionPK != null ? transmisionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transmision)) {
            return false;
        }
        Transmision other = (Transmision) object;
        if ((this.transmisionPK == null && other.transmisionPK != null) || (this.transmisionPK != null && !this.transmisionPK.equals(other.transmisionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Transmision[ transmisionPK=" + transmisionPK + " ]";
    }
    
}
