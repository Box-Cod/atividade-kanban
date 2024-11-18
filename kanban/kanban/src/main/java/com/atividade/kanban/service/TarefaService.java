package com.atividade.kanban.service;

import com.atividade.kanban.enums.Prioridade;
import com.atividade.kanban.enums.Status;
import com.atividade.kanban.model.Tarefa;
import com.atividade.kanban.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa insertTarefa(Tarefa tarefa) {
        tarefa.setDataCriacao();
        tarefa.setPrioridade();
        tarefa.setStatus();
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> selectAllTarefaSort() {
        List<Tarefa> listTf = tarefaRepository.findAll();

        listTf.sort(new Comparator<Tarefa>() {

            @Override
            public int compare(Tarefa o1, Tarefa o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });

        return listTf;
    }

    public Tarefa selectTarefaById(int id) {
        Optional<Tarefa> optf = tarefaRepository.findById(id);

        if (optf.isPresent()) {
            return optf.get();
        } else {
            throw new RuntimeException("Tarefa não encontrada");
        }
    }

    public Tarefa mudarStatusTarefa(int id) {
        Tarefa tf = selectTarefaById(id);

        switch (tf.getStatus()) {
            case TODO -> {
                tf.setStatus(Status.INPROGRESS);
            }

            case INPROGRESS -> {
                tf.setStatus(Status.DONE);
            }
        }

        return tarefaRepository.save(tf);
    }

    public Tarefa mudarTarefa(int id, Tarefa tf) {
        Tarefa taf = selectTarefaById(id);
        taf.setTitulo(tf.getTitulo());
        taf.setDescricao(tf.getDescricao());
        taf.setPrioridade(tf.getPrioridade());

        return tarefaRepository.save(taf);
    }

    public void deletarTarefa(int id) {
        tarefaRepository.deleteById(id);
    }

}
