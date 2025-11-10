 속성별 제약조건 정리

##  member

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| member_id | BIGINT(20) | PK, NOT NULL | 회원 고유 ID |
| name | VARCHAR(50) | NOT NULL | 회원 이름 |
| nickname | VARCHAR(50) | NOT NULL | 닉네임 |
| gender | VARCHAR(10) | NOT NULL | 성별 |
| birth | DATE | NOT NULL | 생년월일 |
| address | VARCHAR(255) | NOT NULL | 주소 |
| status | VARCHAR(15) | NOT NULL | 회원 상태 (정상/탈퇴 등) |
| withdrawal_date | DATETIME | NULL | 탈퇴일 |
| email | VARCHAR(255) | NULL | 이메일 |
| phone | VARCHAR(20) | NULL | 전화번호 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |
| point | INT(20) | NOT NULL | 보유 포인트 |

---

##  food

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| category_id | BIGINT(20) | PK, NOT NULL | 음식 카테고리 ID |
| category_name | VARCHAR(50) | NOT NULL | 음식 카테고리명 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  member_food

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| id | BIGINT(20) | PK, NOT NULL | 고유 ID |
| member_id | BIGINT(20) | FK → member.member_id | 회원 ID |
| category_id | BIGINT(20) | FK → food.category_id | 음식 카테고리 ID |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  mission

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| mission_id | BIGINT(20) | PK, NOT NULL | 미션 고유 ID |
| store_id | BIGINT(20) | FK → store.store_id | 가게 ID |
| mission_condition | VARCHAR(50) | NOT NULL | 미션 조건 |
| mission_deadline | DATE | NOT NULL | 미션 기한 |
| point | INT(20) | NOT NULL | 포인트 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  member_mission

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| id | BIGINT(20) | PK, NOT NULL | 고유 ID |
| member_id | BIGINT(20) | FK → member.member_id | 회원 ID |
| mission_id | BIGINT(20) | FK → mission.mission_id | 미션 ID |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  store

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| store_id | BIGINT(20) | PK, NOT NULL | 가게 ID |
| region_id | BIGINT(20) | FK → region.region_id | 지역 ID |
| name | VARCHAR(50) | NOT NULL | 가게 이름 |
| address | VARCHAR(255) | NOT NULL | 가게 주소 |
| rating | DECIMAL(2,1) | NOT NULL | 평점 |
| status | VARCHAR(50) | NOT NULL | 영업 상태 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  region

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| region_id | BIGINT(20) | PK, NOT NULL | 지역 고유 ID |
| name | VARCHAR(50) | NOT NULL | 지역명 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  review

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| review_id | BIGINT(20) | PK, NOT NULL | 리뷰 고유 ID |
| store_id | BIGINT(20) | FK → store.store_id | 가게 ID |
| member_id | BIGINT(20) | FK → member.member_id | 작성자 ID |
| content | VARCHAR(255) | NOT NULL | 리뷰 내용 |
| rating | DECIMAL(2,1) | NOT NULL | 평점 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  review_reply

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| reply_id | BIGINT(20) | PK, NOT NULL | 댓글 ID |
| review_id | BIGINT(20) | FK → review.review_id | 리뷰 ID |
| content | VARCHAR(255) | NOT NULL | 댓글 내용 |
| created_at | DATETIME(6) | NOT NULL | 생성일 |
| updated_at | DATETIME(6) | NOT NULL | 수정일 |

---

##  review_photo

| 컬럼명 | 데이터 타입 | 제약조건 | 설명 |
|--------|--------------|-----------|------|
| photo_id | BIGINT(20) | PK, NOT NULL | 사진 ID |
| review_id | BIGINT(20) | FK → review.review_id | 리뷰 ID |
| photo_url | VARCHAR(255) | NULL | 사진 URL |

---

#  연관관계 매핑 

| 관계 | 매핑 방향 | 관계 유형 
|------|------------|------------|
| Member – Review | 양방향 | 1:N | 
| Member – MemberFood | 양방향 | 1:N | 
| Member – MemberMission | 단방향 | 1:N | 
| Mission – MemberMission | 양방향 | 1:N |
| Mission – Store | 양방향 | N:1 | 
| Store – Region | 양방향 | N:1 | 
| Review – ReviewReply | 양방향 | 1:1 | 
| Review – ReviewPhoto | 양방향 | 1:N | 
| Food – MemberFood | 단방향 | 1:N | 
