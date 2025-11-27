package com.example.umc9th.domain.store.dto.req;

public class StoreReqDTO {

    public record CreateStoreDTO(
            String name,
            Long managerNumber,
            String detailAddress
    ){}

}
