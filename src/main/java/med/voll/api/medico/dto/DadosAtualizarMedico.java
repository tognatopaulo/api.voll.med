package med.voll.api.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.dto.DadosEndereco;
import med.voll.api.endereco.entity.Endereco;

public record DadosAtualizarMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
