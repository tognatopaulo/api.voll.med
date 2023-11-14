package med.voll.api.medico.dto;

import med.voll.api.endereco.entity.Endereco;
import med.voll.api.medico.Especialidade;
import med.voll.api.medico.entity.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    
    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
