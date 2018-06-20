/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Renato
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
     @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "pes_nome", length = 50, nullable = false) 
    private String nome;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "O data nascimento não pode ser nulo")    
    @Column(name = "pes_nascimento",  nullable = false) 
    private Calendar data_nascimento;
     @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço não pode ser em branco")
    @Length(max = 50, message = "O endereço não pode ter mais que {max} caracteres")
    @Column(name = "pes_endereco", length = 50, nullable = false) 
    private String endereco;
    @NotNull(message = "O nickname não pode ser nulo")
    @NotBlank(message = "O nickname não pode ser em branco")
    @Length(max = 20, message = "O nickname não pode ter mais que {max} caracteres")        
    @Column(name = "nickname", length = 20, nullable = false, unique = true)
    private String nickname;
    @NotNull(message = "A chave de acesso não pode ser nulo")
    @NotBlank(message = "A chave de acesso não pode ser em branco")
    @Length(max = 20, message = "A chave de acesso não pode ter mais que {max} caracteres")    
    @Column(name = "chave_acesso", length = 20, nullable = false)
    private String chaveAcesso;
    @ManyToMany
    @JoinTable(name = "autorizacoes",
            joinColumns
            = @JoinColumn(name = "pessoa", referencedColumnName = "nickname", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "autorizacao", referencedColumnName = "apelido", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_autorizacoes",
                        columnNames = {"pessoa", "autorizacao"})})
    private List<Autorizacao> autorizacoes = new ArrayList<>();

    public Pessoa(){
        
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

    public Calendar getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.getCodigo());
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

    
    
}
