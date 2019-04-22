package es.bryle.digital.profesional.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.vo.ProfessionalVO;

@Service("adminService")
public interface AdminService {

	/* devuleve
	 * Listado de todos los profesionales*/
	List<ProfessionalVO> getProfessionals();
	
	/* devuelve
	 * -2 si ya existe un usuario con el mismo email
	 * -1 si hay algun error en los datos
	 * 1 si el usuario se ha creado correctamente*/
	Integer createProfessional(ProfessionalVO professionalVO);
	
	/* devuelve
	 * 1 si se ha borrado correctamente
	 * -1 si el profesional no existe en la BD
	 * -2 si hay algun error en los datos*/
	Integer deleteProfessional(Long id);
	
	/* devuelve
	 * 1 si se ha editado correctamente
	 * -1 si el profesional no existe en la BD
	 * -2 si hay algun error en los datos*/
	Integer editProfessional(ProfessionalVO professionalVO);
}
