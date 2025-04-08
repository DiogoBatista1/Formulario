package com.drDesign.Formulario.service;

import com.drDesign.Formulario.model.FormularioModel;
import com.drDesign.Formulario.repository.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository repository;

    public FormularioModel salvarFormulario(FormularioModel formulario){
        return repository.save(formulario);
    }

    public List<FormularioModel> listarTodos(){
        return repository.findAll();
    }
}
