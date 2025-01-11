package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SumarySalesProjection;

public class SalesSumaryDTO {

    private Double sumAmount;
    private String nameSeller;

    public SalesSumaryDTO(Long id, Double sumAmount, String nameSeller) {
        this.sumAmount = sumAmount;
        this.nameSeller = nameSeller;

    }

    public SalesSumaryDTO(SumarySalesProjection projectionSumary){
        this.sumAmount = projectionSumary.getSumAmount();
        this.nameSeller = projectionSumary.getNameSeller();
    }


    public Double getSumAmount() {
        return sumAmount;
    }

    public String getNameSeller() {
        return nameSeller;
    }


}
