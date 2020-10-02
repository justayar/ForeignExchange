package com.canemreayar.foreignexchange.persistence.repository;

import com.canemreayar.foreignexchange.persistence.entity.ConversionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory,Long> {

    Optional<ConversionHistory> findById(Long id);

    Optional<ConversionHistory> findByTransactionDate(LocalDate transactionDate);

    @Query(value = "select c from ConversionHistory c where c.transactionId = ?1 or c.transactionDate= ?2 ORDER BY c.transactionDate desc")
    Page<ConversionHistory> findByTransactionIdOrDate(long transactionId,String transactionDate, Pageable pageable);


}
