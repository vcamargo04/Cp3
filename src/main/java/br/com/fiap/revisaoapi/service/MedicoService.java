package br.com.fiap.revisaoapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.revisaoapi.dto.MedicoDTO;
import br.com.fiap.revisaoapi.model.Medico;
import br.com.fiap.revisaoapi.repository.MedicoRepository;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private static final Pageable customPageable = PageRequest.of(0, 3, Sort.by("nome").ascending());

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<MedicoDTO> findAll() {
        return medicoRepository.findAll(customPageable).map(this::toDTO);
    }

    public MedicoDTO findById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(this::toDTO).orElse(null);
    }

    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico update(Long id, Medico medico) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()) {
            Medico medicoUpdate = medicoOptional.get();
            medicoUpdate.setNome(medico.getNome());
            medicoUpdate.setCrm(medico.getCrm());
            medicoUpdate.setEspecialidade(medico.getEspecialidade());
            medicoUpdate.setTelefone(medico.getTelefone());
            medico = medicoRepository.save(medicoUpdate);
            return medico;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        medicoOptional.ifPresent(medicoRepository::delete);
    }

    private MedicoDTO toDTO(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setCrm(medico.getCrm());
        medicoDTO.setEspecialidade(medico.getEspecialidade());
        medicoDTO.setTelefone(medico.getTelefone());
        return medicoDTO;
    }
}
