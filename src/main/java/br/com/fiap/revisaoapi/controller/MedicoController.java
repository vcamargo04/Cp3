package br.com.fiap.revisaoapi.controller;

import br.com.fiap.revisaoapi.dto.MedicoDTO;
import br.com.fiap.revisaoapi.model.Medico;
import br.com.fiap.revisaoapi.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/medicos", produces = {"application/json"})
@Tag(name = "api-medico")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @Operation(summary = "Retorna todos os médicos em páginas de 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum médico encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> findAll() {
        Page<MedicoDTO> medicosDTO = medicoService.findAll();
        if (medicosDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (MedicoDTO medicoDTO : medicosDTO) {
                Long id = medicoDTO.getId();
                medicoDTO.add(linkTo(methodOn(MedicoController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(medicosDTO);
    }

    @Operation(summary = "Retorna um médico específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum médico encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable("id") Long id) {  
        MedicoDTO medicoDTO = medicoService.findById(id);
        if (medicoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            medicoDTO.add(linkTo(methodOn(MedicoController.class)
                    .findAll()).withRel("Lista de Médicos"));
        }
        return ResponseEntity.ok(medicoDTO);
    }

    @Operation(summary = "Grava um médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Medico> save(@Valid @RequestBody Medico medico) {
        Medico medicoSalvo = medicoService.save(medico);
        return new ResponseEntity<>(medicoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um médico com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable("id") Long id, @Valid @RequestBody Medico medico) { 
        Medico medicoSalvo = medicoService.update(id, medico);
        return new ResponseEntity<>(medicoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um médico com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {  
    	medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
