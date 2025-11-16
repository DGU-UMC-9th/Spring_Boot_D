package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {
    public static StoreResDTO.CreateStoreDTO toCreateStoreDTO(Store store) {
        return StoreResDTO.CreateStoreDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .detailAddress(store.getDetailAddress())
                .locationId(store.getLocation().getId())
                .locationName(store.getLocation().getName())
                .build();
    }

    public static Store toStore(StoreReqDTO.CreateStoreDTO dto, Location location) {
        return Store.builder()
                .name(dto.name())
                .managerNumber(dto.managerNumber())
                .detailAddress(dto.detailAddress())
                .location(location)
                .build();
    }
}
