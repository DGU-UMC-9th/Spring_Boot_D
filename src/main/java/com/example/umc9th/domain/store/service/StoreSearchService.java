package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.dto.StoreSearchDto;
import com.example.umc9th.domain.store.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreSearchService {

    private final StoreQueryRepository storeQueryRepository;

    public Page<StoreSearchDto> searchStores(
            List<String> regions,
            String keyword,
            String sortBy,
            Pageable pageable
    ) {
        return storeQueryRepository.searchStores(regions, keyword, sortBy, pageable);
    }
}