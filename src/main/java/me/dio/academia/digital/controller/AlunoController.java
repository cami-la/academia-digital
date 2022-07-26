package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoServiceImpl service;

    public AlunoController(AlunoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Aluno create(@RequestBody AlunoForm form) {
        return service.create(form);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(@PathVariable Long id) throws Exception {
        return service.getAllAvaliacaoFisicaID(id);
    }

    @GetMapping("/{id}")
    public Aluno get(@PathVariable Long id) throws Exception {
        return service.get(id);
    }


    @PutMapping("/{id}")
    public Aluno update(@PathVariable Long id, @RequestBody AlunoUpdateForm updateForm) throws Exception {
        return service.update(id, updateForm);
    }




}
