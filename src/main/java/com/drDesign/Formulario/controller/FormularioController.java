package com.drDesign.Formulario.controller;

import com.drDesign.Formulario.model.FormularioModel;
import com.drDesign.Formulario.service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formularios")
@CrossOrigin(origins = "*")
public class FormularioController {

    @Autowired
    private FormularioService service;

    @PostMapping
    public ResponseEntity<FormularioModel> enviarFormulario(@RequestBody FormularioModel formulario){
        FormularioModel salvo = service.salvarFormulario(formulario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<FormularioModel>> listarFormularios(){
        return ResponseEntity.ok(service.listarTodos());
    }
}
