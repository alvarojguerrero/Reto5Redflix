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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author norma
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuAlias", query = "SELECT u FROM Usuario u WHERE u.usuAlias = :usuAlias")
    , @NamedQuery(name = "Usuario.findByUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre = :usuNombre")
    , @NamedQuery(name = "Usuario.findByUsuApellido", query = "SELECT u FROM Usuario u WHERE u.usuApellido = :usuApellido")
    , @NamedQuery(name = "Usuario.findByUsuEmail", query = "SELECT u FROM Usuario u WHERE u.usuEmail = :usuEmail")
    , @NamedQuery(name = "Usuario.findByUsuCelular", query = "SELECT u FROM Usuario u WHERE u.usuCelular = :usuCelular")
    , @NamedQuery(name = "Usuario.findByUsuFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.usuFechaNacimiento = :usuFechaNacimiento")
    , @NamedQuery(name = "Usuario.findByUsuContrasegna", query = "SELECT u FROM Usuario u WHERE u.usuContrasegna = :usuContrasegna")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usu_alias")
    private String usuAlias;
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Column(name = "usu_apellido")
    private String usuApellido;
    @Column(name = "usu_email")
    private String usuEmail;
    @Column(name = "usu_celular")
    private String usuCelular;
    @Column(name = "usu_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date usuFechaNacimiento;
    @Column(name = "usu_contrasegna")
    private String usuContrasegna;

    public Usuario() {
    }

    public Usuario(String usuAlias) {
        this.usuAlias = usuAlias;
    }

    public String getUsuAlias() {
        return usuAlias;
    }

    public void setUsuAlias(String usuAlias) {
        this.usuAlias = usuAlias;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellido() {
        return usuApellido;
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido = usuApellido;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(String usuCelular) {
        this.usuCelular = usuCelular;
    }

    public Date getUsuFechaNacimiento() {
        return usuFechaNacimiento;
    }

    public void setUsuFechaNacimiento(Date usuFechaNacimiento) {
        this.usuFechaNacimiento = usuFechaNacimiento;
    }

    public String getUsuContrasegna() {
        return usuContrasegna;
    }

    public void setUsuContrasegna(String usuContrasegna) {
        this.usuContrasegna = usuContrasegna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuAlias != null ? usuAlias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuAlias == null && other.usuAlias != null) || (this.usuAlias != null && !this.usuAlias.equals(other.usuAlias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Usuario[ usuAlias=" + usuAlias + " ]";
    }
    
}
