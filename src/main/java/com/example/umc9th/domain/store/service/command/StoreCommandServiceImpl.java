package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.LocationRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.apiPayload.code.status.StoreErrorCode;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;

    @Override
    public StoreResDTO.CreateStoreDTO createStore(
            Long locationId,
            StoreReqDTO.CreateStoreDTO dto
    ) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        Store store = StoreConverter.toStore(dto, location);

        Store savedStore = storeRepository.save(store);

        return StoreConverter.toCreateStoreDTO(savedStore);
    }
}
