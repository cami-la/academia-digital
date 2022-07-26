package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    private final AvaliacaoFisicaRepository repository;
    private final AlunoRepository alunoRepository;


    public AvaliacaoFisicaServiceImpl(AvaliacaoFisicaRepository repository, AlunoRepository alunoRepository) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();

        Optional<Aluno> alunoOptional = alunoRepository.findById(form.getAlunoId());
        Aluno aluno = alunoOptional.orElseThrow(RuntimeException::new);
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());

        return repository.save(avaliacaoFisica);
    }

    @Override
    public AvaliacaoFisica get(Long id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) throws Exception {
        AvaliacaoFisica avaliacaoFisica = repository
                .findById(id)
                .orElseThrow(Exception::new);
        avaliacaoFisica.setPeso(formUpdate.getPeso());
        avaliacaoFisica.setAltura(formUpdate.getAltura());
        return avaliacaoFisica;
    }

    @Override
    public void delete(Long id) {    }
}
