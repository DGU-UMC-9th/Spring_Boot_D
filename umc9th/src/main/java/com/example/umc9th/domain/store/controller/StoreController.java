package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.status.StoreSuccessCode;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/stores")
@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/locations/{locationId}")
    public ApiResponse<StoreResDTO.CreateStoreDTO> createStore(
            @PathVariable Long locationId,
            @RequestBody StoreReqDTO.CreateStoreDTO dto
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, storeCommandService.createStore(locationId, dto));
    }
}
