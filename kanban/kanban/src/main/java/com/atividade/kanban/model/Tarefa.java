package com.atividade.kanban.model;

import com.atividade.kanban.enums.Prioridade;
import com.atividade.kanban.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private String dataCriacao;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Prioridade prioridade;

    // private Date dataLimite;

    public Tarefa() { }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade() {
        this.prioridade = Prioridade.BAIXA;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus() {
        this.status = Status.TODO;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dataCriacao = dateNow.format(formatDate);
    }
}
