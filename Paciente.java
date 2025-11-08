package br.unifafire.connectnutri.modelo;

import java.time.LocalDate;

public class Paciente {
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private String email;
    private String motivoConsulta;

    public Paciente(String nome, String telefone, LocalDate dataNascimento, String email, String motivoConsulta) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.motivoConsulta = motivoConsulta;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }
}