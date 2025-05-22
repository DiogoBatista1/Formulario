package com.drDesign.Formulario.service;

import com.drDesign.Formulario.DTO.FormularioDTO;
import com.drDesign.Formulario.model.FormularioModel;
import com.drDesign.Formulario.repository.FormularioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormularioService {

    private final FormularioRepository repository;
    
    public FormularioService(FormularioRepository repository) {
    	this.repository = repository;
    }

    public FormularioModel saveFormulario(FormularioDTO dto) {
    	
    	Optional<FormularioModel> UserExist = repository.findByNomeAndEmail(dto.getNome(), dto.getEmail());
    	
    	if (UserExist.isPresent()) {
    		throw new IllegalStateException("Usuário já existe com este nome e email");
    	}
    	
        FormularioModel model = new FormularioModel();
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setTelefone(dto.getTelefone());
        model.setMensagem(dto.getMensagem());
        return repository.save(model);
    }


    public List<FormularioModel> getAllFormularios(){
        return repository.findAll();
    }
    

    @Transactional
    public void deleteFormulario(Long id) {
        repository.deleteById(id);
    }
}
