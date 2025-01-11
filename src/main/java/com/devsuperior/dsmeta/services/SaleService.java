package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSumaryDTO;
import com.devsuperior.dsmeta.projections.ReportSalesProjection;
import com.devsuperior.dsmeta.projections.SumarySalesProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.util.Tuple;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	//Report Sales(Relatório de Vendas)
	public Page<SalesReportDTO> reportSales(String minDate, String maxDate, String name, Pageable pageable) {

		Tuple<LocalDate,LocalDate> dates = convertDates(minDate, maxDate);

		name = (name == null || name.isBlank()) ? "" : name;

		Page<ReportSalesProjection> result = repository.searchReportSales(dates.getFirst(), dates.getSecond(), name, pageable);
		Page<SalesReportDTO> resultReportDTO = result.map(projection -> new SalesReportDTO(projection));

		return resultReportDTO;
	}



	//Sumary Sales(Sumário de Vendas)
	public List<SalesSumaryDTO> sumarySales(String minDate, String maxDate) {

		Tuple<LocalDate, LocalDate> dates = convertDates(minDate, maxDate);

		List<SumarySalesProjection> result = repository.searchSumarySales(dates.getFirst(), dates.getSecond());
		List<SalesSumaryDTO> resultSumaryDTO =  result.stream().map(projection -> new SalesSumaryDTO(projection))
																						.collect(Collectors.toList());
		return resultSumaryDTO;
	}

	private Tuple<LocalDate, LocalDate> convertDates(String minDate, String maxDate) {

		LocalDate maxDateConverted = (maxDate != null && !maxDate.isBlank()) ? LocalDate.parse(maxDate) : LocalDate.now();
		LocalDate minDateConverted = (minDate != null && !minDate.isBlank()) ? LocalDate.parse(minDate) : maxDateConverted.minusYears(1L);

		return new Tuple<>(minDateConverted, maxDateConverted);
	}

}
