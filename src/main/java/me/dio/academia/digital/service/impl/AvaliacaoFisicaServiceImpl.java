package me.dio.academia.digital.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import me.dio.academia.digital.service.exception.ObjectNotFoundException;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoServiceImpl alunoService;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoService.getById(form.getAlunoId());
    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());
    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica getById(Long id) {
    Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
    return avaliacaoFisica.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));
  }

  @Override
  public List<AvaliacaoFisica> getAll() {
    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    AvaliacaoFisica avaliacaoFisica = getById(id);
    update(avaliacaoFisica, formUpdate);
    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  private void update(AvaliacaoFisica avaliacaoFisica, AvaliacaoFisicaUpdateForm formUpdate) {
    avaliacaoFisica.setAltura(formUpdate.getAltura());
    avaliacaoFisica.setPeso(formUpdate.getPeso());
  }

  @Override
  public void delete(Long id) {
    getById(id);
    avaliacaoFisicaRepository.deleteById(id);
  } 
}