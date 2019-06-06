package es.bryle.digital.profesional.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import es.bryle.digital.profesional.model.vo.CarVO;
import es.bryle.digital.profesional.model.vo.SaleVO;

@Service("salesService")
public interface SalesService {

	Integer createSale(SaleVO saleVO);
	
	Integer deleteSale(Long id);
	
	Integer editSale(SaleVO saleVO);
	
	List<SaleVO> getSales();
	
	List<SaleVO> getAllSales();
	
	List<SaleVO> getCurrentUserSales();
	
	SaleVO getOneSale(Long id);
	
	Integer createCar(CarVO carVO);
	
	Integer deleteCar(Long id);
	
	Integer editCar(CarVO carVO);
	
	List<CarVO> getCars();
	
	CarVO getOneCar(Long id);
	
}//
