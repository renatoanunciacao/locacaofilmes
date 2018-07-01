/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexv
 */
@Entity
@Table(name = "itensLocacao")
public class ItensLocacao implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_itensLocacao", sequenceName = "seq_itensLocacao_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_itensLocacao", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    //Relação com Filme
    @NotNull(message = "O filme não pode ser nulo")
    @ManyToOne
    @JoinColumn(name="filme", referencedColumnName = "codigo",nullable = false,
            foreignKey = @ForeignKey(name = "fk_filme_id"))    
    private Filme filme;
    //Faz relação da Locação
    @NotNull(message = "A locação não pode ser nulo")
    @ManyToOne
    @JoinColumn(name="locacao", referencedColumnName = "codigo", nullable = false,
            foreignKey = @ForeignKey(name = "fk_locacao_id"))
    private Locacao locacao;

    public ItensLocacao() {
        
    }  

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.codigo);
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
        final ItensLocacao other = (ItensLocacao) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
