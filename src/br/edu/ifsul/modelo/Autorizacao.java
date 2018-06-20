/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "autorizacao")
public class Autorizacao implements Serializable {

    @Id
    @Column(name = "apelido", length = 30, nullable = false)
    private String apelido;
    @NotBlank(message = "Os detalhes devem ser informados")
    @Length(max = 40, message = "Os detalhes não deve ter mais que {max} caracteres")
    @Column(name = "descricao", length = 40, nullable = false)
    private String detalhes;

    public Autorizacao() {

    }
    
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.apelido);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autorizacao other = (Autorizacao) obj;
        if (!Objects.equals(this.apelido, other.apelido)) {
            return false;
        }
        return true;
    }
    
    

}
