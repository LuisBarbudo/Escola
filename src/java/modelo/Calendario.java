/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author aluno
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "select c from Calendario c"),
    @NamedQuery(name = "Calendario.findFilter", query = "select c from Calendario c" +
            " where UPPER(c.Dia) like :filtro or UPPER(c.Mes) like :filtro "
            + "or UPPER(c.Ano) like :filtro UPPER(c.Descricao) like :filtro" )
})
public class Calendario implements Serializable {
  

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, length = 50)
    private Integer Dia;
    @Column(nullable = false, length = 50)
    private Integer Mes;
    @Column(nullable = false, length = 32)
    private Integer Ano;
    @Column(nullable = false, length = 150)
    private String Descricao;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDia() {
        return Dia;
    }

    public void setDia(Integer Dia) {
        this.Dia = Dia;
    }

    public Integer getMes() {
        return Mes;
    }

    public void setMes(Integer Mes) {
        this.Mes = Mes;
    }

    public Integer getAno() {
        return Ano;
    }

    public void setAno(Integer Ano) {
        this.Ano = Ano;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Calendario[ id=" + id + " ]";
    }
    
}
