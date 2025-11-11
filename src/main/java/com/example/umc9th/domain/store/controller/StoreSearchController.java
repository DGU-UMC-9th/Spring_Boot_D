package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreSearchDto;
import com.example.umc9th.domain.store.service.StoreSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreSearchController {

    private final StoreSearchService storeSearchService;

    /**
     * 가게 검색 API
     * /api/stores/search?regions=강남구,서초구&keyword=민트 초코&sortBy=name&page=0&size=10
     */
    @GetMapping("/search")
    public Page<StoreSearchDto> searchStores(
            @RequestParam(required = false) List<String> regions,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sortBy,
            Pageable pageable
    ) {
        return storeSearchService.searchStores(regions, keyword, sortBy, pageable);
    }
}