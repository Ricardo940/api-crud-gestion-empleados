package com.gestion.empleados.comgestionempleados.controlador;

import com.gestion.empleados.comgestionempleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.comgestionempleados.modelo.Empleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.comgestionempleados.repositorio.EmpleadoRepositorio;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        return new ResponseEntity<>(empleadoRepositorio.findAll(), HttpStatus.OK);
    }

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado){
        Empleado empleadoBBDD = empleadoRepositorio.save(empleado);
        return new ResponseEntity<Empleado>(empleadoBBDD, HttpStatus.OK);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long id){

        Empleado empleado = empleadoRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el Id " + id));

        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }
    
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado){
        
        Empleado empleadoBBDD = empleadoRepositorio.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el Id " + id));


        empleadoBBDD.setNombre(empleado.getNombre());
        empleadoBBDD.setApellido(empleado.getApellido());
        empleadoBBDD.setEmail(empleado.getEmail());

        return new ResponseEntity<Empleado>(empleadoRepositorio.save(empleadoBBDD), HttpStatus.OK);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id){
        empleadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el Id " + id));
        empleadoRepositorio.deleteById(id);
        return new ResponseEntity<>("{ \"message\":\"Eliminado con exito\"}", HttpStatus.OK);
    }
    
}
