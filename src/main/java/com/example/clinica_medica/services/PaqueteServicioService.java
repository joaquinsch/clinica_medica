package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.PaqueteServicio;
import com.example.clinica_medica.repository.PaqueteServicioRepository;

@Service
public class PaqueteServicioService {
	@Autowired
	private PaqueteServicioRepository paqueteServicioRepo;
	
	public PaqueteServicio guardarPaqueteServicio(PaqueteServicio paqueteServicio) {
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

