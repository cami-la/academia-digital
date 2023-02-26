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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

  @Autowired
  private AvaliacaoFisicaServiceImpl service;

  @PostMapping
  public ResponseEntity<AvaliacaoFisica> create(@Valid @RequestBody AvaliacaoFisicaForm form) {
    service.create(form);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(form.getAlunoId()).toUri(); 
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<List<AvaliacaoFisica>> getAll(){
    List<AvaliacaoFisica> list = service.getAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AvaliacaoFisica> getById(@PathVariable Long id) {
    AvaliacaoFisica avaliacaoFisica = service.getById(id);
    return ResponseEntity.ok().body(avaliacaoFisica);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody AvaliacaoFisicaUpdateForm formUpdate, @PathVariable Long id) {
    service.update(id, formUpdate);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}