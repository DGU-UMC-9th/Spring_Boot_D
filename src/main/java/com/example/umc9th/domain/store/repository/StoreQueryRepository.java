package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.dto.StoreSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreQueryRepository {
    Page<StoreSearchDto> searchStores(
            List<String> regions,
            String keyword,
            String sortBy,
            Pageable pageable
    );
}