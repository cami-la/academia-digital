package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaForm {

  @NotNull(message = "Preencha o campo corretamente.")
  @Positive(message = "O Id do aluno precisa ser positivo.")
  private Long alunoId;
}
