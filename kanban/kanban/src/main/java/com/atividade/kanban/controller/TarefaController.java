package com.atividade.kanban.controller;

import com.atividade.kanban.model.Tarefa;
import com.atividade.kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tf) {
        return tarefaService.insertTarefa(tf);
    }

    @GetMapping
    public List<Tarefa> listarTarefar() {
        return tarefaService.selectAllTarefaSort();
    }

    @PutMapping("/{id}/move")
    public Tarefa mudarStatusTarefa(@PathVariable int id) {
        return tarefaService.mudarStatusTarefa(id);
    }

    @PutMapping("/{id}")
    public Tarefa mudarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        return tarefaService.mudarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        tarefaService.deletarTarefa(id);
    }


}
