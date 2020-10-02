package com.canemreayar.foreignexchange.service;

import com.canemreayar.foreignexchange.exception.ConversionListRequestBadParametersException;
import com.canemreayar.foreignexchange.persistence.entity.ConversionHistory;
import com.canemreayar.foreignexchange.persistence.repository.ConversionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Log4j2
@Transactional
@RequiredArgsConstructor
@Service
public class ConversionHistoryCrudService {

    private final ConversionHistoryRepository conversionHistoryRepository;

    private int pageCounter = 0;

    @Value("${numberOfRecordOnPageableRequest}")
    private static int NUMBER_0F_RECORD_ON_EACH_PAGE;


    public ConversionHistory createConversionHistory(ConversionHistory conversionHistory){
        Objects.requireNonNull(conversionHistory, "Object conversionHistory cannot be null!");
        log.info("[(createConversionHistory) Creating new conversion history with date: {}", conversionHistory.getTransactionDate());

        return this.conversionHistoryRepository.save(conversionHistory);
    }

    public List<ConversionHistory> getConversionsByTransactionIdOrDate(ConversionHistory conversionHistory){

        if(conversionHistory.getTransactionId().equals(0l) && conversionHistory.getTransactionDate() == null)
            throw new ConversionListRequestBadParametersException();

        Pageable pageable = PageRequest.of(pageCounter, NUMBER_0F_RECORD_ON_EACH_PAGE);

        Page<ConversionHistory> conversionHistories = this.conversionHistoryRepository.findByTransactionIdOrDate(conversionHistory.getTransactionId(), conversionHistory.getTransactionDate(), pageable);

        pageCounter ++;

        return conversionHistories.getContent();
    }




}
