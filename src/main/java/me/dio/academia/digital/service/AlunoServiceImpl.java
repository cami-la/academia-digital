package me.dio.academia.digital.service;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService{


    private final AlunoRepository repository;

    public AlunoServiceImpl(AlunoRepository repository) {
        super();
        this.repository = repository;
    }

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
    public Aluno get(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<Aluno> getAll() {
        return repository.findAll();
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) throws Exception {
        Aluno aluno = repository
                .findById(id)
                .orElseThrow(Exception::new);
        aluno.setBairro(formUpdate.getBairro());
        aluno.setNome(formUpdate.getNome());
        aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
        return aluno;
    }

    @Override
    public void delete(Long id) { }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaID(Long id) throws Exception {
        Aluno aluno = repository
                .findById(id)
                .orElseThrow(Exception::new);
        return aluno.getAvaliacoes();
    }
}
