package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface ReportSalesProjection {

    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getNameSeller();

}
