package me.dio.academia.digital.entity.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AlunoUpdateForm {

  private String nome;
  private String bairro;
  private LocalDate dataDeNascimento;
}