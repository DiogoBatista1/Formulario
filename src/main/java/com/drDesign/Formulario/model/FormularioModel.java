package com.drDesign.Formulario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "formularios")
public class FormularioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    private String arquivo_url;  

    public FormularioModel(){
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

	public String getArquivo_url() {
		return arquivo_url;
	}

	public void setArquivo_url(String arquivo_url) {
		this.arquivo_url = arquivo_url;
	}

}
