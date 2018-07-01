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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alexv
 */
@Entity
@Table(name = "filme")
public class Filme implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_filme", sequenceName = "seq_filme_id", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_filme", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 100, message = "O nome não pode ter mais que {max} caracteres")
    private String nome;
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode ser em branco")
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 1)
    @Column(name = "valor", columnDefinition = "numeric(12,2)" )
    private Double valor;
    private Boolean locado = false;
    //Faz relação do projeto
    @ManyToOne
    @JoinColumn(name="locacao", referencedColumnName = "codigo", nullable = true,
            foreignKey = @ForeignKey(name = "fk_locacao_id"))        
    private Locacao locacao;

    public Filme() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    
    public Boolean getLocado() {
        return locado;
    }

    public void setLocado(Boolean locado) {
        this.locado = locado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
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
        final Filme other = (Filme) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
