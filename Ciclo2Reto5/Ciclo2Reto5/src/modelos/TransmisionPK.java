/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author norma
 */
@Embeddable
public class TransmisionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "tra_usuario")
    private String traUsuario;
    @Basic(optional = false)
    @Column(name = "tra_contenido")
    private int traContenido;
    @Basic(optional = false)
    @Column(name = "tra_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date traFechaHora;

    public TransmisionPK() {
    }

    public TransmisionPK(String traUsuario, int traContenido, Date traFechaHora) {
        this.traUsuario = traUsuario;
        this.traContenido = traContenido;
        this.traFechaHora = traFechaHora;
    }

    public String getTraUsuario() {
        return traUsuario;
    }

    public void setTraUsuario(String traUsuario) {
        this.traUsuario = traUsuario;
    }

    public int getTraContenido() {
        return traContenido;
    }

    public void setTraContenido(int traContenido) {
        this.traContenido = traContenido;
    }

    public Date getTraFechaHora() {
        return traFechaHora;
    }

    public void setTraFechaHora(Date traFechaHora) {
        this.traFechaHora = traFechaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traUsuario != null ? traUsuario.hashCode() : 0);
        hash += (int) traContenido;
        hash += (traFechaHora != null ? traFechaHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransmisionPK)) {
            return false;
        }
        TransmisionPK other = (TransmisionPK) object;
        if ((this.traUsuario == null && other.traUsuario != null) || (this.traUsuario != null && !this.traUsuario.equals(other.traUsuario))) {
            return false;
        }
        if (this.traContenido != other.traContenido) {
            return false;
        }
        if ((this.traFechaHora == null && other.traFechaHora != null) || (this.traFechaHora != null && !this.traFechaHora.equals(other.traFechaHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.TransmisionPK[ traUsuario=" + traUsuario + ", traContenido=" + traContenido + ", traFechaHora=" + traFechaHora + " ]";
    }
    
}
