package com.senac.assister.backend.domain.enumeration;

public enum EmailSubjects {
    CREATE_USER("Confirmação da criação do usuário Assister."),
    FORGOT_PASSWORD("Código para recuperação de senha Assister"),
    SERVICE_IN_PROGRESS("Serviço está em progresso");

    private final String text;

    EmailSubjects(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}