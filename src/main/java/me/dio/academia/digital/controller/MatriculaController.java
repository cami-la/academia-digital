package me.dio.academia.digital.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

  @Autowired
  private MatriculaServiceImpl service;

  @PostMapping
  public ResponseEntity<Matricula> create(@Valid @RequestBody MatriculaForm form) {
    Matricula matricula = service.create(form);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(matricula.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<List<Matricula>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
    List<Matricula> matriculas = service.getAll(bairro);
    return ResponseEntity.ok().body(matriculas);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Matricula> getById(@PathVariable long id) {
    Matricula matricula = service.getById(id);
    return ResponseEntity.ok().body(matricula);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}