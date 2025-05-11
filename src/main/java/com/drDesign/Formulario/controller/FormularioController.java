package com.drDesign.Formulario.controller;

import com.drDesign.Formulario.model.FormularioModel;
import com.drDesign.Formulario.service.FormularioService;
import com.drDesign.Formulario.DTO.FormularioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/formulario")
public class FormularioController {

    private final FormularioService service;

    public FormularioController(FormularioService service) {
    	this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<FormularioModel> enviarFormulario(@RequestBody FormularioDTO dto){
        FormularioModel savedForm = service.saveFormulario(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedForm.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedForm);
    }


    @GetMapping
    public ResponseEntity<List<FormularioModel>> listarFormularios(){
        return ResponseEntity.ok(service.getAllFormularios());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormulario(@PathVariable Long id) {
        service.deleteFormulario(id);
        return ResponseEntity.noContent().build();
    }
}
