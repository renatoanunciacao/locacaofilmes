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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexv
 */
@Entity
@Table(name = "locacao")
public class Locacao implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_locacao", sequenceName = "seq_locacao_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_locacao", strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @Temporal(TemporalType.DATE)
    @Column(name = "emprestimo", nullable = false)
    private Calendar emprestimo;
    @Temporal(TemporalType.DATE)
    @Column(name = "devolucao", nullable = false)
    private Calendar devolucao;
    @NotNull(message = "O valor não pode ser nulo")
    @Column(name = "valor_total", columnDefinition = "numeric(12,2)")
    private Double valorTotal;
    private Double multa;
    //Referencia a classe pessoa como chave estrangeira
    @NotNull(message = "O campo pessoa não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "codigo", nullable = false,
            foreignKey = @ForeignKey(name = "fk_projeto_pessoa"))
    private Pessoa pessoa;
    //Lista de filmes
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY) //qual atributo aponta para essa classe
    private List<Filme> filme = new ArrayList<>();

    public Locacao() {
        this.valorTotal = 0.0;
        this.multa = 0.0;
    }
    
    public void adicionarFilme(Filme obj) {
        obj.setLocacao(this);
        this.valorTotal += obj.getValor(); //atualiza o valor dos itens adicionados no valor total
        this.filme.add(obj); //adiciona o filme na lista
    }

    public void removerFilme(int index) {
        Filme obj = this.filme.get(index); //instacia o objeto do item para pegar o indice
        this.valorTotal -= obj.getValor(); //atualiza o valor dos itens removidos no valor total
        this.filme.remove(index); //remove o filme da lista
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Calendar getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Calendar emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Calendar getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Calendar devolucao) {
        this.devolucao = devolucao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Filme> getFilme() {
        return filme;
    }

    public void setFilme(List<Filme> filme) {
        this.filme = filme;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }
    
}
