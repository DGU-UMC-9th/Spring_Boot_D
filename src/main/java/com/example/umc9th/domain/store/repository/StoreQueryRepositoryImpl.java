package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.dto.StoreSearchDto;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

@RequiredArgsConstructor
public class StoreQueryRepositoryImpl implements StoreQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<StoreSearchDto> searchStores(
            List<String> regions,
            String keyword,
            String sortBy,
            Pageable pageable
    ) {
        QStore store = QStore.store;

        BooleanBuilder where = new BooleanBuilder();

        // 지역 필터링 (다중 선택 지원)
        if (regions != null && !regions.isEmpty()) {
            where.and(store.region.in(regions));
        }

        // 이름 검색 (공백 포함 여부에 따라 달라짐)
        if (keyword != null && !keyword.isBlank()) {
            if (keyword.contains(" ")) {
                // 공백이 있을 경우 단어별로 OR 검색 (합집합)
                for (String part : keyword.split("\\s+")) {
                    where.or(store.name.containsIgnoreCase(part));
                }
            } else {
                // 공백이 없을 경우 전체 검색어 포함
                where.and(store.name.containsIgnoreCase(keyword));
            }
        }

        // 정렬 조건
        OrderSpecifier<?> orderSpecifier;
        if ("name".equalsIgnoreCase(sortBy)) {
            orderSpecifier = store.name.asc().nullsLast();
        } else { // 기본: 최신순
            orderSpecifier = store.createdAt.desc();
        }

        var queryResult = query
                .select(Projections.constructor(
                        StoreSearchDto.class,
                        store.id,
                        store.name,
                        store.region,
                        store.category,
                        store.address
                ))
                .from(store)
                .where(where)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        var content = queryResult.fetch();

        var countQuery = query
                .select(store.count())
                .from(store)
                .where(where);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}