package com.example.projetosemestralloja;

import android.widget.Button;
import android.widget.EditText;

public class Cliente {
    private String nomeText;
    private String senhaText;
    private String cpfText;
    private String dataNascText;
    private String emailText;
    private String usuarioText;

    public Cliente() {
    }

    public String getNomeText() {
        return nomeText;
    }

    public void setNomeText(String nomeText) {
        this.nomeText = nomeText;
    }

    public String getSenhaText() {
        return senhaText;
    }

    public void setSenhaText(String senhaText) {
        this.senhaText = senhaText;
    }

    public String getCpfText() {
        return cpfText;
    }

    public void setCpfText(String cpfText) {
        this.cpfText = cpfText;
    }

    public String getDataNascText() {
        return dataNascText;
    }

    public void setDataNascText(String dataNascText) {
        this.dataNascText = dataNascText;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String getUsuarioText() {
        return usuarioText;
    }

    public void setUsuarioText(String usuarioText) {
        this.usuarioText = usuarioText;
    }
}
