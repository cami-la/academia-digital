package me.dio.academia.digital.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoServiceImpl service;

  @PostMapping
  public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm form) {
    Aluno aluno = service.create(form);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(aluno.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/avaliacoes/{id}")
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
    return service.getAllAvaliacaoFisicaId(id);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Aluno> getById(@PathVariable Long id) {
    Aluno aluno = service.getById(id);
    return ResponseEntity.ok().body(aluno);
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNacimento) {
    List<Aluno> alunos = service.getAll(dataDeNacimento);
    return ResponseEntity.ok().body(alunos);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody AlunoUpdateForm formUpdate, @PathVariable Long id) {
    service.update(id, formUpdate);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}