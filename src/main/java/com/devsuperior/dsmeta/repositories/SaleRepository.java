package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.ReportSalesProjection;
import com.devsuperior.dsmeta.projections.SumarySalesProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    /*JPQL*/
    @Query(" SELECT sa.id as id, sa.date as date, sa.amount as amount," +
            " sa.seller.name as nameSeller FROM Sale sa" +
            " WHERE sa.date BETWEEN :minDate AND :maxDate " +
            " AND UPPER(sa.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<ReportSalesProjection> searchReportSales(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);



    /*SQL PURO*/
    @Query(nativeQuery = true, value =  " SELECT  SE.NAME as nameseller, SUM(SA.AMOUNT) as sumAmount FROM TB_SELLER SE "+
                                        " INNER JOIN TB_SALES SA ON SE.ID = SA.SELLER_ID " +
                                        " WHERE SA.DATE BETWEEN :minDate AND :maxDate "+
                                        " GROUP BY SE.NAME ")
    List<SumarySalesProjection> searchSumarySales(LocalDate minDate, LocalDate maxDate);
}
