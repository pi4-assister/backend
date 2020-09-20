package com.senac.assister.backend.domain.enumeration;

public enum EmailSubjects {
    CREATE_USER("Confirmação da criação do usuário Assister.");

    private final String text;

    EmailSubjects(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}