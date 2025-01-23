package com.example.clinica_medica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_medica.model.ConsultaMedica;

import com.example.clinica_medica.repository.ConsultaMedicaRepository;

@Service
public class ConsultaMedicaService {

	@Autowired
	private ConsultaMedicaRepository consultaMedicaRepo;

	public ConsultaMedica guardarConsultaMedica(ConsultaMedica consulta) {
		return consultaMedicaRepo.save(consulta);
	}

	public ConsultaMedica buscarConsultaMedica(Long id_consulta) {
		return consultaMedicaRepo.findById(id_consulta).orElseThrow();

	}
	
	public ConsultaMedica editarPaciente(ConsultaMedica consulta) {
		ConsultaMedica consultaMedica = buscarConsultaMedica(consulta.getId_consulta_medica());
		consultaMedica.setId_consulta_medica(consulta.getId_consulta_medica());
		consultaMedica.setFecha_consulta(consulta.getFecha_consulta());
		consultaMedica.setHora_consulta(consulta.getHora_consulta());
		consultaMedica.setUn_paciente(consulta.getUn_paciente());
		consultaMedica.setUn_medico(consulta.getUn_medico());
		consultaMedica.setUn_paquete_servicio(consulta.getUn_paquete_servicio());
		consultaMedica.setUn_servicio_medico(consulta.getUn_servicio_medico());
		consultaMedica.setMonto_total(consulta.getMonto_total());
		consultaMedica.setPagado_o_no(consulta.getPagado_o_no());
		return consultaMedicaRepo.save(consultaMedica);
	}

	public void eliminarConsultaMedica(Long id_consulta) {
		// busca, si no existe tira excepcion
	    @SuppressWarnings("unused")
	    ConsultaMedica consultaMedica = buscarConsultaMedica(id_consulta);
	    consultaMedicaRepo.deleteById(id_consulta);
	}

}
