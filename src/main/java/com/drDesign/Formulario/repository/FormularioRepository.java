package com.drDesign.Formulario.repository;

import com.drDesign.Formulario.model.FormularioModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioModel, Long> {

	Optional<FormularioModel> findByNomeAndEmail(String nome, String email);
}