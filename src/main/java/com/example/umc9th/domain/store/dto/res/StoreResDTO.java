package com.example.umc9th.domain.store.dto.res;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record CreateStoreDTO(
            Long storeId,
            String name,
            String detailAddress,
            Long locationId,
            String locationName
    ){}
}
