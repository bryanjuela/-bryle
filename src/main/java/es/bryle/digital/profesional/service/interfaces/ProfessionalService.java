package es.bryle.digital.profesional.service.interfaces;

import java.util.List;

import es.bryle.digital.profesional.model.vo.SaleVO;

public interface ProfessionalService {

	Integer createSale(SaleVO saleVO);
	
	Integer deleteSale(Long id);
	
	Integer editSale(SaleVO saleVO);
	
	List<SaleVO> getSales();
	
	SaleVO getOneSale();
	
}//
