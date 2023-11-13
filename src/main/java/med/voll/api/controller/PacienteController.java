package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.dto.DadosCadastroPaciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.dto.DadosListagemPaciente;
import med.voll.api.paciente.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteRepository repository;
    
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        System.out.println(dados);
        repository.save(new Paciente(dados));
        
    }
    
    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
