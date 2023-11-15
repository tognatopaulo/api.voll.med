package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.DadosEndereco;

public record DadosAtualizarPaciente(@NotNull Long id, String nome, String email, @Valid DadosEndereco endereco) {
}
