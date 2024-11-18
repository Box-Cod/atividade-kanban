package com.atividade.kanban.repository;

import com.atividade.kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
