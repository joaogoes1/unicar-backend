package com.unicar.auth.domain;

public class DomainNotAllowedException extends Exception {
    public DomainNotAllowedException() {
        super("Hoje apenas alunos da PUC-Campinas podem se cadastrar. Se você for aluno, utilize seu email PUC-Campinas.");
    }
}
