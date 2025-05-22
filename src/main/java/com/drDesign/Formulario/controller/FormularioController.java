package com.drDesign.Formulario.controller;

import com.drDesign.Formulario.model.FormularioModel;
import com.drDesign.Formulario.service.FormularioService;
import com.drDesign.Formulario.DTO.FormularioDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/formulario")
public class FormularioController {

    private final FormularioService service;

    public FormularioController(FormularioService service) {
    	this.service = service;
    }
    
    @PostMapping("/json")
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
    
    @PostMapping("/upload")
    public ResponseEntity<String> receberFormulario(
        @RequestParam("nome") String nome,
        @RequestParam("email") String email,
        @RequestParam("mensagem") String mensagem,
        @RequestParam(value = "telefone", required = false) String telefone,
        @RequestParam("arquivo") MultipartFile arquivoPdf,
        @RequestParam(value = "anexos", required = false) MultipartFile[] anexos
    ) {
        try {
        	String basePath = System.getProperty("user.dir") + "/upload/";
            String pastaDestino = basePath + nome.replaceAll("[^a-zA-Z0-9]", "_");
            Path diretorio = Paths.get(pastaDestino);
            Files.createDirectories(diretorio);

            Path pdfPath = diretorio.resolve (arquivoPdf.getOriginalFilename());
            arquivoPdf.transferTo(pdfPath);
            
            if(anexos != null && anexos.length > 0) {
            	for(MultipartFile anexo : anexos) {
            		if(!anexo.isEmpty()) {
            			Path anexoPath = diretorio.resolve(anexo.getOriginalFilename());
            			anexo.transferTo(anexoPath.toFile());
            		}
            	}
            }

            FormularioDTO dto = new FormularioDTO();
            dto.setNome(nome);
            dto.setEmail(email);
            dto.setMensagem(mensagem);
            dto.setTelefone(telefone);

            service.saveFormulario(dto);

            
            return ResponseEntity.ok("Arquivo recebido com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar arquivo.");
        } catch (IllegalStateException e) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
