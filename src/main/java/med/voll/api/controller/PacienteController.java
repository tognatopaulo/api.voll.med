package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.dto.DadosDetalhamentoMedico;
import med.voll.api.paciente.dto.DadosAtualizarPaciente;
import med.voll.api.paciente.dto.DadosCadastroPaciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.dto.DadosDetalhamentoPaciente;
import med.voll.api.paciente.dto.DadosListagemPaciente;
import med.voll.api.paciente.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        var dto = new DadosDetalhamentoPaciente(paciente);
        return ResponseEntity.created(uri).body(dto);
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    
}
