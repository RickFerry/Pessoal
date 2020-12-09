
package com.mycompany.agenda.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    
    @Column(name = "endereco", length = 100, nullable = true)
    private String endereco;
    
    @Column(name = "numero_residencia", length = 15, nullable = true)
    private int numero;
    
    @OneToOne
    private Cidade cidade;
    
    @Column(name = "email", length = 50, nullable = true)
    private String email;
    
    @Column(name = "data_nascimento", nullable = true)
    private LocalDate nascimento;
    
    @Column(name = "telefone1", length = 11, nullable = true)
    private Long telefone1;
    
    @Column(name = "telefone2", length = 11, nullable = true)
    private Long telefone2;
    
    @OneToOne
    private TipoContato tipoContato;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @Column(name = "sexo", length = 1)
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Long getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(Long telefone1) {
        this.telefone1 = telefone1;
    }

    public Long getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(Long telefone2) {
        this.telefone2 = telefone2;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
