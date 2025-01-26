package com.example.clinica_medica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.model.ServicioMedico;
import com.example.clinica_medica.repository.PaqueteServicioRepository;
import com.example.clinica_medica.repository.ServicioMedicoRepository;

@Service
public class PaqueteServicioService {

	private static final Double DESCUENTO_PAQUETE = 0.15;

	@Autowired
	private PaqueteServicioRepository paqueteServicioRepo;

	@Autowired
	private ServicioMedicoRepository servicioMedicoRepo;
	
	/*
	 * 
	 * se debe crear el paquete pasandole a la lista de servicios, el id de cada servicio medico.
	 * - fijarse que pasa si se pasa un servicio que no existe.
	 * - fijarse el tema de pasar un monto o no... (porque ya se calcula al crearse el paquete)
	 */

	public PaqueteServicio guardarPaqueteServicio(PaqueteServicio paqueteServicio) {
		List<ServicioMedico> serviciosDelPaquete = paqueteServicio.getLista_servicios_incluidos();

		Double precioFinal = Double.valueOf(0);
		for (ServicioMedico servicioMedico : serviciosDelPaquete) {
			Optional<ServicioMedico> servicio_actual = servicioMedicoRepo.findById(servicioMedico.getCodigo_servicio());
			precioFinal += servicio_actual.get().getPrecio();
		}
		precioFinal = precioFinal - precioFinal * DESCUENTO_PAQUETE;
		paqueteServicio.setPrecio_paquete(precioFinal);
		return paqueteServicioRepo.save(paqueteServicio);
	}

	public PaqueteServicio buscarPaqueteServicio(Long id_paquete) {
		return paqueteServicioRepo.findById(id_paquete).orElseThrow();
	}

	public PaqueteServicio editarPaqueteServicio(PaqueteServicio paqueteServicio) {
		PaqueteServicio paqueteBuscado = buscarPaqueteServicio(paqueteServicio.getCodigo_paquete());
		paqueteBuscado.setDescripcion(paqueteServicio.getDescripcion());
		paqueteBuscado.setPrecio_paquete(paqueteServicio.getPrecio_paquete());
		paqueteBuscado.setLista_servicios_incluidos(paqueteServicio.getLista_servicios_incluidos());
		return paqueteServicioRepo.save(paqueteBuscado);
	}

	public void eliminarPaqueteServicio(Long id_paquete) {
		PaqueteServicio paquete = buscarPaqueteServicio(id_paquete);
		paqueteServicioRepo.delete(paquete);
	}

}
