package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportSalesProjection;

import java.time.LocalDate;

public class SalesReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String nameSeller;

    public SalesReportDTO(Long id, LocalDate date, Double amount, String nameSeller) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.nameSeller = nameSeller;
    }

    public SalesReportDTO(ReportSalesProjection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.amount = projection.getAmount();
        this.nameSeller = projection.getNameSeller();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNameSeller() {
        return nameSeller;
    }
}
