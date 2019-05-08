package es.bryle.digital.profesional.controller.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.model.vo.SaleVO;
import es.bryle.digital.profesional.service.interfaces.AdminService;
import es.bryle.digital.profesional.service.interfaces.SalesService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/controller/sales-operations")
public class SalesControllerMVC {

	@Autowired
	private SalesService salesService;
	
	
	@ApiOperation(value = "Recuperación de todos los coches",
			notes = "Recupera un listado con todos los coches de la BD")
	@RequestMapping(value = "/car-list", method= RequestMethod.GET)
	public String getCars(Map<String, Object> model){
		List<CarVO> cars= salesService.getCars();
		if(!cars.isEmpty() || cars!= null)
			model.put("cars", cars);
		return "/admin";
	}
	
	@ApiOperation(value = "Recuperación de todas las ventas",
			notes = "Recupera un listado con todas las ventas de la BD")
	@RequestMapping(value = "/sale-list", method= RequestMethod.GET)
	public String getSales(Map<String, Object> model){
		List<SaleVO> sales= salesService.getSales();
		if(!sales.isEmpty() || sales!= null)
			model.put("sales", sales);
		return "/admin";
	}
	
	
	@ApiOperation(value = "Formulario de coche",
			notes = "Redirecciona a la vista para crear un nuevo coche")
	@RequestMapping(value = "/create-car", method= RequestMethod.GET)
	public String createCar(Map<String, Object> model) {
		CarVO carVO= new CarVO();
		model.put("carVO", carVO);
		
		return "/comercial";
	}
	
	
	@ApiOperation(value = "Creación de un nuevo coche",
			notes = "Crea un nuevo coche si no existe ya en la BD")
	@RequestMapping(value = "/car", method= RequestMethod.POST)
	public String crateCar(@Valid CarVO carVO){
		
		if(carVO!= null) {
			Integer result= salesService.createCar(carVO);
			if(result== 1)
				return "redirect:/controller/admin-profile/car-list";
			if(result== -1 || result== -2)
				return "redirect:/controller/admin-profile/create-car";
		}
		return "redirect:/controller/admin-profile/create-car";
	}
	
	
	
	@ApiOperation(value = "Eliminacion de un coche",
			notes = "Elimina el coche de la BD ")
	@RequestMapping(value = "/delete-car/{id}")
	public String deleteCar(@PathVariable("id")Long id){
		
		if(id!= null && id> 0) {
			Integer result= salesService.deleteCar(id);
			if(result== 1)
				return "redirect:/controller/admin-profile/car-list";
			
			/*PAGINA DE ERROR
			 * if(result== -1)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
		}
		return "redirect:/controller/admin-profile/car-list";
	}
	
	
}
