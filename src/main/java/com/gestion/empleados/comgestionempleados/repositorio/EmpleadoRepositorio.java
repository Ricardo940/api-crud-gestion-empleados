package com.gestion.empleados.comgestionempleados.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.empleados.comgestionempleados.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
    
}
