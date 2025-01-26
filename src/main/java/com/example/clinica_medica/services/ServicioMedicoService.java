package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.repository.ServicioMedicoRepository;

@Service
public class ServicioMedicoService {

	@Autowired
	private ServicioMedicoRepository servicioMedicoRepo;


	public ServicioMedico guardarServicioMedico(ServicioMedico servicioMedico) {
		return servicioMedicoRepo.save(servicioMedico);
	}

	public ServicioMedico buscarServicioMedico(Long id_servicio) {
		return servicioMedicoRepo.findById(id_servicio).orElseThrow();
	}

	public ServicioMedico editarServicioMedico(ServicioMedico servicioMedico) {

		ServicioMedico servicioBuscado = buscarServicioMedico(servicioMedico.getCodigo_servicio());

		servicioBuscado.setNombre(servicioMedico.getNombre());
		servicioBuscado.setDescripcion(servicioMedico.getDescripcion());
		servicioBuscado.setPrecio(servicioMedico.getPrecio());
		servicioBuscado.setLista_paquetes(servicioMedico.getLista_paquetes());

		return servicioMedicoRepo.save(servicioBuscado);
	}

	public void eliminarServicioMedico(Long id_servicio) {
		@SuppressWarnings("unused")
		ServicioMedico servicio = buscarServicioMedico(id_servicio);
		servicioMedicoRepo.deleteById(id_servicio);
	}
}
