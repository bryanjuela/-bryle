package es.bryle.digital.profesional.controller.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.bryle.digital.profesional.model.entities.auth.User;
import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.ProfessionalVO;
import es.bryle.digital.profesional.model.vo.SaleVO;
import es.bryle.digital.profesional.service.interfaces.AuthUserService;
import es.bryle.digital.profesional.service.interfaces.ProfessionalService;
import es.bryle.digital.profesional.service.interfaces.SalesService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/controller/sales-operations")
public class SalesControllerMVC {

	@Autowired
	private SalesService salesService;
	@Autowired
	private AuthUserService authUserService;
	
	private static final String ROOT_PATH= "/controller/sales-operations";
	private static final String REDIRECT= "redirect:";
	
	
	@ApiOperation(value = "Recuperación de todos los coches",
			notes = "Recupera un listado con todos los coches de la BD")
	@RequestMapping(value = "/car-list", method= RequestMethod.GET)
	public String getCars(Map<String, Object> model){
		List<CarVO> cars= salesService.getCars();
		if(cars== null ) 
			cars= new ArrayList<>();
		
		//Ruta para el boton crear de index.html para los coches
		//createButton -> nombre de la variable
		//ROOT_PATH+"/create-car" -> valor de la variable 
		model.put("cars", cars);
		model.put("createButton", ROOT_PATH+"/create-car");
		model.put("tabFragment", "coches");
		return "/index";
	}
	
	@ApiOperation(value = "Formulario de coche",
			notes = "Redirecciona a la vista para crear un nuevo coche")
	@RequestMapping(value = "/create-car", method= RequestMethod.GET)
	public String createCar(Map<String, Object> model) {
		CarVO carVO= new CarVO();
		model.put("carVO", carVO);
		model.put("titulo", "Nuevo Coche");
		return "/coche";
	}
	
	
	@ApiOperation(value = "Creación o edición de un nuevo coche",
			notes = "Crea o edita un coche en la BD")
	@RequestMapping(value = "/car", method= RequestMethod.POST)
	public String saveCar(@Valid CarVO carVO, Model model){
		
		if(carVO!= null) {
			Integer result; 
			String redirectPage;
			
			if(carVO.getId()== null) {//crear
				result= salesService.createCar(carVO);
				redirectPage= "/create-car";
			}else {//editar
				result= salesService.editCar(carVO);
				redirectPage= "/edit-car/"+carVO.getId();
			}
			
			if(result== 1)
				return REDIRECT+ROOT_PATH+"/car-list";
			if(result== -1 || result== -2)
				return REDIRECT+ROOT_PATH+ redirectPage;
		}
		model.addAttribute("mensaje", "CAR ERROR");
		model.addAttribute("redirectPage", ROOT_PATH+"/car-list");
		return "/error_404";
	}
	
	
	
	@ApiOperation(value = "Eliminacion de un coche",
			notes = "Elimina el coche de la BD ")
	@RequestMapping(value = "/delete-car/{id}")
	public String deleteCar(@PathVariable("id")Long id, Model model){
		
		if(id!= null && id> 0) {
			Integer result= salesService.deleteCar(id);
			if(result== 1)
				return REDIRECT+ROOT_PATH+"/car-list";
			
			//PAGINA DE ERROR
			if(result== -1) {
				model.addAttribute("mensaje", "CAR ID NOT FOUND");
				 model.addAttribute("redirectPage", ROOT_PATH+"/car-list");
				return "/error_404";
			}	
		}
		return REDIRECT+ROOT_PATH+"/car-list";
	}
	
	
	@ApiOperation(value = "Edición de un coche",
			notes = "Redirecciona a la vista de coches con los datos del coche seleccionado")
	@RequestMapping(value = "/edit-car/{id}")
	public String editCar(@PathVariable("id")Long id, Model model){
		
		if(id!= null && id> 0) {
			CarVO carVO= salesService.getOneCar(id);
			if(carVO!= null) {
				model.addAttribute("carVO", carVO);
				model.addAttribute("titulo", "Editar coche");
				return "/coche";
			}
		}
		 model.addAttribute("mensaje", "CAR ID NOT FOUND");
		 model.addAttribute("redirectPage", ROOT_PATH+"/car-list");
		return "/error_404";
	}

	@ApiOperation(value = "Recuperación de todas las ventas",
			notes = "Recupera un listado con todas las ventas de la BD")
	@RequestMapping(value = "/sale-list", method= RequestMethod.GET)
	public String getSales(Map<String, Object> model){
		List<SaleVO> sales= salesService.getAllSales();
		if(sales== null) 
			sales= new ArrayList<SaleVO>();
		User user= authUserService.getCurrentUser();
		model.put("role", user.getRoles().get(0));
		model.put("sales", sales);
		model.put("createButton", ROOT_PATH+"/create-sale");
		model.put("tabFragment", "ventas");
		return "/index";
	}
	
	@ApiOperation(value = "Formulario de venta",
			notes = "Redirecciona a la vista para crear una nueva venta")
	@RequestMapping(value = "/create-sale", method= RequestMethod.GET)
	public String createSale(Model model) {
		SaleVO saleVO= new SaleVO();
		model.addAttribute("saleVO", saleVO);
		model.addAttribute("titulo", "Nueva venta");
		
		//pasar el listado de coches no vendidos
		List<CarVO> cars= salesService.getCars();
		List<CarVO> totalCars= new ArrayList<CarVO>();
		
		for(int i= 0; i< cars.size(); i++) {
			CarVO element= cars.get(i);
			String estado= element.getEstado();
			if(!estado.equalsIgnoreCase("Vendido"))
				totalCars.add(element);
		}
		
		model.addAttribute("cars", totalCars);
		return "/venta";
	}
	
	@ApiOperation(value = "Creación o edición de una venta",
			notes = "Crea o edita una venta en la BD")
	@RequestMapping(value = "/sale", method= RequestMethod.POST)
	public String saveSale(@Valid SaleVO saleVO, Model model){
		
		if(saleVO!= null) {
			System.out.println(saleVO.getSaleDate());
			Integer result;
			String redirectPage;
			if(saleVO.getId()== null) { //nueva venta;
				result= salesService.createSale(saleVO);
				redirectPage= "/create-sale";
			}else {                      //editar venta
				result= salesService.editSale(saleVO);
				redirectPage= "/edit-sale/"+saleVO.getId();
			}
			
			if(result== 1) //todo correcto
				return REDIRECT+ROOT_PATH+"/sale-list";
			if(result== -1 || result== -2) //volver a cargar la página
				return REDIRECT+ROOT_PATH+ redirectPage;
			
		}
		
		//error
		model.addAttribute("mensaje", "SALE ERROR");
		model.addAttribute("redirectPage", ROOT_PATH+"/sale-list");
		return "/error_404";
	}
	
	
	@ApiOperation(value = "Edición de una venta",
			notes = "Redirecciona a la vista de ventas con los datos de la Venta seleccionado")
	@RequestMapping(value = "/edit-sale/{id}")
	public String editSale(@PathVariable("id")Long id, Model model){
		
		if(id!= null && id> 0) {
			SaleVO saleVO= salesService.getOneSale(id);
			if(saleVO!= null) {
				model.addAttribute("saleVO", saleVO);
				model.addAttribute("titulo", "Editar Venta");
				//pasar el listado de coches no vendidos
				List<CarVO> cars= salesService.getCars();
				for(CarVO element: cars) {
					if(element.getEstado().equalsIgnoreCase("vendido"))
						cars.remove(element);
				}
				model.addAttribute("cars", cars);
				return "/venta";
			}
		}
		 model.addAttribute("mensaje", "SALE ID NOT FOUND");
		 model.addAttribute("redirectPage", ROOT_PATH+"/sale-list");
		return "/error_404";
	}
	
	
	@ApiOperation(value = "Eliminacion de una venta",
			notes = "Elimina ls venta de la BD y vuelve a cargar la página de ventas")
	@RequestMapping(value = "/delete-sale/{id}")
	public String deleteSale(@PathVariable("id")Long id, Model model){
		
		if(id!= null && id> 0) {
			Integer result= salesService.deleteSale(id);
			if(result== 1)
				return REDIRECT+ROOT_PATH+"/sale-list";
			
			//PAGINA DE ERROR
			 if(result== -1) {
				 model.addAttribute("mensaje", "SALE ID NOT FOUND");
				 model.addAttribute("redirectPage", ROOT_PATH+"/sale-list");
				 return "/error_404";
			 }	 
		}
		return REDIRECT+ROOT_PATH+"/sale-list";
	}
	
}
