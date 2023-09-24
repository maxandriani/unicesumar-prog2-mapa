/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.unicesumar.ads.prog2.mapa.entities;

import java.util.Objects;

/**
 *
 * @author max.andriani
 */
public class Usuario {
    private Long Id;
    private String nome;
    private String login;
    private String hashSenha;
    private String email;

    public Long getId() {
        return Id;
    }

    public Usuario setId(Long Id) {
        this.Id = Id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Usuario setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public Usuario setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.Id, other.Id);
    }
}
