package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());
    return repository.save(aluno);
  }

  @Override
  public Aluno getById(Long id) {
    Optional<Aluno> aluno = repository.findById(id);
    return aluno.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
  }

  @Override
  public List<Aluno> getAll(String dataDeNascimento) {
    if(dataDeNascimento == null) {
      return repository.findAll();
    } 
    else {
      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
      return repository.findByDataDeNascimento(localDate);
    }
  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm formUpdate) {
    Aluno aluno = getById(id);
    updateData(aluno, formUpdate);
    return repository.save(aluno);
  }

  private void updateData(Aluno aluno, AlunoUpdateForm formUpdate) {
    aluno.setNome(formUpdate.getNome());
    aluno.setBairro(formUpdate.getBairro());
    aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
  }

  @Override
  public void delete(Long id) {
    getById(id);
    repository.deleteById(id);
  }

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
    Aluno aluno = getById(id);
    return aluno.getAvaliacoes();
  }
}