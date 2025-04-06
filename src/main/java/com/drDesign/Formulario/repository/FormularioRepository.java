package com.drDesign.Formulario.repository;

import com.drDesign.Formulario.model.FormularioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioModel, Long> {
}