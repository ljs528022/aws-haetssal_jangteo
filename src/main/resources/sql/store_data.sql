INSERT INTO tbl_store (id, store_market_id, store_owner_id, store_category_id, store_name, store_intro, store_address, store_score, store_state, store_is_confirmed, created_datetime, updated_datetime) VALUES
-- market_id 5 (서울 - 광화문 전통시장)
(8,   5, 2, 300, '광화문 쌀국수', '신선한 재료로 만든 베트남식 쌀국수 전문점입니다.', '서울 종로구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9,   5, 1, 100, '종로 순대국밥', '진한 육수로 우려낸 전통 순대국밥을 판매합니다.', '서울 종로구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 6 (서울 - 마포 나눔장터)
(10,  6, 3, 200, '마포 떡볶이집', '매콤달콤한 국물 떡볶이와 튀김을 함께 즐기세요.', '', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11,  6, 4, 500, '마포 빈티지샵', '1980~2000년대 빈티지 의류와 소품을 판매합니다.', '서울 마포구', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 7 (서울 - 동대문 새벽시장)
(12,  7, 1, 400, '동대문 원단가게', '다양한 종류의 원단과 부자재를 저렴하게 판매합니다.', '서울 동대문구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13,  7, 2, 700, '동대문 핸드메이드', '직접 제작한 핸드메이드 액세서리와 소품을 판매합니다.', '서울 동대문구', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 8 (서울 - 노량진 활어장터)
(14,  8, 3, 100, '노량진 횟집', '매일 새벽 들어오는 신선한 활어회를 합리적인 가격에 제공합니다.', '서울 동작구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15,  8, 4, 100, '노량진 조개구이', '싱싱한 조개와 해산물을 직접 구워 먹을 수 있는 가게입니다.', '서울 동작구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 9 (경기 - 수원 팔달문장터)
(16,  9, 1, 200, '수원 왕갈비구이', '수원 명물 왕갈비를 숯불에 구워 제공하는 전문점입니다.', '경기 수원시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17,  9, 2, 600, '팔달 공방', '도자기와 전통 공예품을 직접 만들고 판매하는 공방입니다.', '경기 수원시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 10 (경기 - 성남 모란민속시장)
(18, 10, 3, 800, '모란 약재상', '전통 한약재와 건강식품을 전문으로 취급합니다.', '경기 성남시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 10, 4, 300, '모란 국밥집', '얼큰하고 진한 국물의 뼈해장국 전문점입니다.', '경기 성남시', 100, 'denied', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 11 (경기 - 안양 중앙알뜰장터)
(20, 11, 1, 500, '안양 중고서점', '다양한 분야의 중고 도서를 저렴하게 구매할 수 있습니다.', '경기 안양시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(21, 11, 2, 400, '안양 청과물상회', '매일 아침 신선하게 들어오는 제철 과일과 채소를 판매합니다.', '경기 안양시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 12 (경기 - 고양 행주장터)
(22, 12, 3, 100, '행주 두부전골', '직접 만든 손두부로 끓인 얼큰한 두부전골 전문점입니다.', '경기 고양시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 12, 4, 900, '행주 화훼농원', '계절마다 다양한 꽃과 식물을 직접 재배하여 판매합니다.', '경기 고양시', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 13 (인천 - 신포국제시장)
(24, 13, 1, 200, '신포 닭강정', '인천 명물 신포 닭강정을 바삭하게 튀겨 판매합니다.', '인천 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(25, 13, 2, 300, '신포 만두가게', '얇은 피에 꽉 찬 속재료로 만든 수제 만두 전문점입니다.', '인천 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 14 (인천 - 부평 깡시장)
(26, 14, 3, 500, '부평 군복상회', '다양한 밀리터리 의류와 용품을 취급하는 전문 상점입니다.', '인천 부평구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(27, 14, 4, 700, '부평 액세서리샵', '트렌디한 패션 액세서리와 주얼리를 판매합니다.', '인천 부평구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 15 (인천 - 강화 풍물장터)
(28, 15, 1, 800, '강화 인삼가게', '강화도 특산품인 고려인삼과 인삼 관련 제품을 판매합니다.', '인천 강화군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(29, 15, 2, 400, '강화 순무김치', '강화도 특산 순무로 담근 전통 김치를 판매합니다.', '인천 강화군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 16 (인천 - 송도 주말벼룩시장)
(30, 16, 3, 600, '송도 플리마켓', '다양한 핸드메이드 작품과 중고 물품을 판매합니다.', '인천 연수구', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(31, 16, 4, 500, '송도 빈티지 의류', '엄선된 빈티지 의류와 소품을 합리적인 가격에 판매합니다.', '인천 연수구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 17 (강원 - 강릉 중앙시장)
(32, 17, 1, 100, '강릉 초당순두부', '강릉 명물 초당순두부를 사용한 정통 순두부찌개 전문점입니다.', '강원 강릉시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(33, 17, 2, 300, '강릉 커피농장', '강릉 핸드드립 커피와 로스팅 원두를 판매하는 카페입니다.', '강원 강릉시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 18 (강원 - 춘천 낭만장터)
(34, 18, 3, 200, '춘천 닭갈비집', '숯불에 구운 춘천식 닭갈비와 막국수를 함께 즐기세요.', '강원 춘천시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(35, 18, 4, 900, '춘천 화훼가게', '춘천 근교에서 재배한 신선한 화훼류를 판매합니다.', '강원 춘천시', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 19 (강원 - 속초 관광수산시장)
(36, 19, 1, 100, '속초 오징어순대', '속초 명물 오징어순대와 각종 수산물을 판매합니다.', '강원 속초시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(37, 19, 2, 100, '속초 아바이마을 젓갈', '전통 방식으로 담근 다양한 종류의 젓갈을 판매합니다.', '강원 속초시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 20 (강원 - 원주 미로예술시장)
(38, 20, 3, 600, '원주 공예품상점', '지역 작가들의 다양한 공예 작품을 전시하고 판매합니다.', '강원 원주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(39, 20, 4, 700, '원주 도예공방', '직접 빚은 도자기 그릇과 소품을 판매하는 도예 공방입니다.', '강원 원주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 21 (충북 - 청주 육거리종합시장)
(40, 21, 1, 400, '청주 올갱이국밥', '청주 전통 올갱이(다슬기) 해장국 전문점입니다.', '충북 청주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(41, 21, 2, 500, '청주 한복가게', '전통 한복과 생활 한복을 맞춤 제작하여 판매합니다.', '충북 청주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 22 (충북 - 충주 자유시장)
(42, 22, 3, 400, '충주 사과상회', '충주 명물 사과와 사과즙, 사과잼 등 가공식품을 판매합니다.', '충북 충주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(43, 22, 4, 300, '충주 칼국수집', '충주식 손칼국수와 수제비를 판매하는 전통 면 요리 전문점입니다.', '충북 충주시', 100, 'denied', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 23 (충북 - 제천 약초장터)
(44, 23, 1, 800, '제천 한방약초', '제천 특산 한방 약초와 건강 보조식품을 전문으로 판매합니다.', '충북 제천시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(45, 23, 2, 800, '제천 산야초가게', '제천 청정 자연에서 채취한 다양한 산야초를 판매합니다.', '충북 제천시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 24 (충북 - 옥천 전통오일장)
(46, 24, 3, 400, '옥천 대추가게', '옥천 특산 대추와 대추 관련 건강 식품을 판매합니다.', '충북 옥천군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(47, 24, 4, 300, '옥천 장터국밥', '오일장 스타일 투박하고 푸짐한 전통 장터 국밥 전문점입니다.', '충북 옥천군', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 25 (충남 - 천안 중앙시장)
(48, 25, 1, 300, '천안 호두과자', '천안 명물 수제 호두과자를 직접 구워 판매합니다.', '충남 천안시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(49, 25, 2, 200, '천안 병천순대', '천안 명물 병천 순대와 순대국밥을 전문으로 판매합니다.', '충남 천안시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 26 (충남 - 아산 온천장터)
(50, 26, 3, 900, '아산 온천화훼', '아산 온천 인근에서 재배한 신선한 꽃과 식물을 판매합니다.', '충남 아산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(51, 26, 4, 800, '아산 건강원', '아산 지역 특산 약재를 이용한 건강즙과 건강식품을 판매합니다.', '충남 아산시', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 27 (충남 - 공주 산성시장)
(52, 27, 1, 400, '공주 밤가게', '공주 특산 알밤과 밤 가공식품(밤양갱, 밤빵 등)을 판매합니다.', '충남 공주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(53, 27, 2, 300, '공주 국밥마당', '공주 전통 시장식 푸짐한 국밥과 수육을 판매합니다.', '충남 공주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 28 (충남 - 서산 동부시장)
(54, 28, 3, 100, '서산 어리굴젓', '서산 명물 어리굴젓과 각종 해산물 젓갈을 판매합니다.', '충남 서산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(55, 28, 4, 400, '서산 마늘상회', '서산 육쪽마늘과 마늘 가공식품을 전문으로 판매합니다.', '충남 서산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 29 (대전 - 대전 중앙시장)
(56, 29, 1, 200, '대전 성심당 분점', '대전 명물 튀김소보로와 각종 빵을 판매하는 베이커리입니다.', '대전 동구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(57, 29, 2, 300, '대전 칼국수', '대전식 얼큰한 들깨 칼국수와 만두를 판매합니다.', '대전 동구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 30 (대전 - 유성 온천장터)
(58, 30, 3, 600, '유성 온천 공방', '온천 테마의 핸드메이드 비누와 화장품을 제작·판매합니다.', '대전 유성구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(59, 30, 4, 300, '유성 두부요리', '유성 두부 두루치기와 순두부찌개를 전문으로 하는 두부 요리 전문점입니다.', '대전 유성구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 31 (대전 - 둔산 열린장터)
(60, 31, 1, 500, '둔산 중고명품샵', '검증된 중고 명품 의류와 잡화를 합리적인 가격에 판매합니다.', '대전 서구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(61, 31, 2, 700, '둔산 공예방', '지역 작가들이 만든 다양한 공예 작품을 전시·판매합니다.', '대전 서구', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 32 (대전 - 대덕 한밭장터)
(62, 32, 3, 400, '대덕 채소가게', '대덕 근교 농가에서 직접 재배한 신선한 채소를 판매합니다.', '대전 대덕구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(63, 32, 4, 100, '대덕 생선구이', '신선한 제철 생선을 직화로 구워 제공하는 생선구이 전문점입니다.', '대전 대덕구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 33 (경북 - 안동 구시장)
(64, 33, 1, 200, '안동 찜닭집', '안동 명물 간장 찜닭을 전통 방식으로 조리하여 판매합니다.', '경북 안동시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(65, 33, 2, 800, '안동 헛제삿밥', '안동 전통 헛제삿밥과 간고등어를 판매합니다.', '경북 안동시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 34 (경북 - 경주 성동시장)
(66, 34, 3, 300, '경주 황리단길 찰보리빵', '경주 명물 찰보리빵과 전통 간식을 판매합니다.', '경북 경주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(67, 34, 4, 600, '경주 기념품샵', '경주 역사 문화 테마 기념품과 전통 공예품을 판매합니다.', '경북 경주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 35 (경북 - 포항 죽도시장)
(68, 35, 1, 100, '포항 물회집', '포항 명물 신선한 회와 물회를 판매하는 전문 횟집입니다.', '경북 포항시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(69, 35, 2, 100, '포항 과메기상회', '포항 특산 과메기와 각종 건어물을 판매합니다.', '경북 포항시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 36 (경북 - 구미 선산장터)
(70, 36, 3, 400, '구미 참외가게', '구미 근교에서 재배한 달콤한 참외와 제철 과일을 판매합니다.', '경북 구미시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(71, 36, 4, 300, '선산 장터국밥', '구미 선산 전통 오일장 스타일 장터 국밥을 판매합니다.', '경북 구미시', 100, 'denied', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 37 (경남 - 창원 마산어시장)
(72, 37, 1, 100, '마산 아구찜', '마산 명물 아구찜과 아구탕을 전통 방식으로 조리하여 판매합니다.', '경남 창원시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(73, 37, 2, 100, '마산 멍게비빔밥', '신선한 멍게와 해산물로 만든 멍게비빔밥을 전문으로 판매합니다.', '경남 창원시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 38 (경남 - 진주 중앙시장)
(74, 38, 3, 200, '진주 냉면집', '진주 전통 육전 냉면을 맑은 육수와 함께 판매합니다.', '경남 진주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(75, 38, 4, 300, '진주 비빔밥', '진주 전통 비빔밥과 헛제삿밥을 정갈하게 차려 제공합니다.', '경남 진주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 39 (경남 - 통영 서호시장)
(76, 39, 1, 100, '통영 굴요리', '통영 명물 생굴과 굴밥, 굴국밥을 전문으로 판매합니다.', '경남 통영시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(77, 39, 2, 300, '통영 꿀빵', '통영 명물 꿀빵과 전통 간식을 직접 만들어 판매합니다.', '경남 통영시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 40 (경남 - 거제 고현장터)
(78, 40, 3, 100, '거제 대구탕', '거제 청정 바다에서 잡은 신선한 대구로 끓인 대구탕 전문점입니다.', '경남 거제시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(79, 40, 4, 500, '거제 수산물상회', '거제 인근 해역에서 당일 잡은 싱싱한 수산물을 판매합니다.', '경남 거제시', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 41 (부산 - 국제시장)
(80, 41, 1, 500, '부산 씨앗호떡', '부산 명물 씨앗호떡과 각종 분식을 판매합니다.', '부산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(81, 41, 2, 500, '국제시장 포목점', '다양한 원단과 한복 소재를 판매하는 전통 포목점입니다.', '부산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 42 (부산 - 자갈치 수산시장)
(82, 42, 3, 100, '자갈치 활어회', '자갈치 시장의 신선한 활어회와 해산물 요리를 제공합니다.', '부산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(83, 42, 4, 100, '자갈치 건어물', '자갈치 시장 특산 건어물과 젓갈류를 다양하게 판매합니다.', '부산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 43 (부산 - 부산진 시장)
(84, 43, 1, 500, '부산진 한복점', '전통 한복과 현대적 생활한복을 맞춤 제작 판매합니다.', '부산 부산진구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(85, 43, 2, 700, '부산진 액세서리', '트렌디한 패션 액세서리와 주얼리를 도매 가격에 판매합니다.', '부산 부산진구', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 44 (부산 - 기장 미역장터)
(86, 44, 3, 100, '기장 미역상회', '기장 특산 자연산 미역과 다시마를 판매합니다.', '부산 기장군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(87, 44, 4, 100, '기장 멸치상회', '기장 앞바다에서 잡은 싱싱한 멸치와 건어물을 판매합니다.', '부산 기장군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 45 (울산 - 울산 중앙시장)
(88, 45, 1, 300, '울산 언양불고기', '언양식 얇게 썬 한우 불고기를 전통 방식으로 구워 판매합니다.', '울산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(89, 45, 2, 400, '울산 야채가게', '울산 근교 농가에서 매일 신선하게 들여오는 채소를 판매합니다.', '울산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 46 (울산 - 태화강 장터)
(90, 46, 3, 900, '태화강 화훼원', '태화강 인근에서 재배한 계절 꽃과 화분을 판매합니다.', '울산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(91, 46, 4, 600, '태화강 공방', '태화강 자연 테마의 천연 염색 제품과 공예품을 판매합니다.', '울산 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 47 (울산 - 언양알프스시장)
(92, 47, 1, 200, '언양 한우정육점', '언양 지역 한우를 직접 도축하여 신선하게 판매하는 정육점입니다.', '울산 울주군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(93, 47, 2, 300, '언양 갈비집', '언양식 양념 갈비와 불고기를 숯불에 구워 제공합니다.', '울산 울주군', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 48 (울산 - 남구 삼산장터)
(94, 48, 3, 700, '삼산 소품샵', '감각적인 인테리어 소품과 라이프스타일 제품을 판매합니다.', '울산 남구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(95, 48, 4, 500, '삼산 중고옷가게', '상태 좋은 중고 의류와 잡화를 저렴하게 판매합니다.', '울산 남구', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 49 (대구 - 서문시장)
(96, 49, 1, 500, '서문 포목상회', '서문시장 전통 포목과 다양한 원단을 도매가로 판매합니다.', '대구 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(97, 49, 2, 300, '서문 칼국수', '서문시장 50년 전통의 납작만두 칼국수를 판매합니다.', '대구 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 50 (대구 - 칠성시장)
(98, 50, 3, 400, '칠성 청과도매', '대구 칠성시장 청과물 도매상으로 신선한 과일과 채소를 판매합니다.', '대구 북구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(99, 50, 4, 800, '칠성 한약방', '칠성시장 전통 한약방으로 한방 약재와 건강식품을 취급합니다.', '대구 북구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 51 (대구 - 방천시장)
(100, 51, 1, 700, '방천 아트샵', '대구 방천시장 골목 예술가들의 작품과 굿즈를 판매합니다.', '대구 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(101, 51, 2, 600, '방천 빈티지샵', '방천시장 골목의 감성 가득한 빈티지 소품과 의류를 판매합니다.', '대구 중구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 52 (대구 - 북구 팔거장터)
(102, 52, 3, 400, '팔거 로컬푸드', '대구 북구 팔거 지역 농가에서 생산한 신선 채소를 직거래로 판매합니다.', '대구 북구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(103, 52, 4, 100, '팔거 생선가게', '매일 아침 들여오는 신선한 생선과 해산물을 판매합니다.', '대구 북구', 100, 'denied', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 53 (전북 - 전주 남부시장)
(104, 53, 1, 200, '전주 비빔밥집', '전주 전통 비빔밥과 콩나물국밥을 정갈하게 제공합니다.', '전북 전주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(105, 53, 2, 300, '전주 한과가게', '전주 전통 방식으로 만든 한과와 전통 과자를 판매합니다.', '전북 전주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 54 (전북 - 군산 공설시장)
(106, 54, 3, 300, '군산 이성당 빵집', '군산 명물 앙금빵과 야채빵을 전통 방식으로 구워 판매합니다.', '전북 군산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(107, 54, 4, 100, '군산 박대가게', '군산 명물 박대(서대)와 각종 건어물을 판매합니다.', '전북 군산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 55 (전북 - 익산 중앙시장)
(108, 55, 1, 500, '익산 한복상회', '전통 한복과 폐백 용품을 전문으로 취급하는 한복 상점입니다.', '전북 익산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(109, 55, 2, 400, '익산 딸기농장직판', '익산 특산 딸기와 딸기 가공식품을 농장 직거래로 판매합니다.', '전북 익산시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 56 (전북 - 정읍 샘고을시장)
(110, 56, 3, 400, '정읍 복분자상회', '정읍 특산 복분자와 복분자 가공식품을 판매합니다.', '전북 정읍시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(111, 56, 4, 300, '정읍 장터순대', '정읍식 전통 순대와 순대국밥을 푸짐하게 제공합니다.', '전북 정읍시', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 57 (전남 - 여수 수산시장)
(112, 57, 1, 100, '여수 돌산갓김치', '여수 명물 돌산 갓으로 담근 전통 갓김치를 판매합니다.', '전남 여수시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(113, 57, 2, 100, '여수 게장가게', '여수 앞바다에서 잡은 꽃게로 담근 간장·양념 게장을 판매합니다.', '전남 여수시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 58 (전남 - 순천 아랫장)
(114, 58, 3, 400, '순천 짱뚱어탕', '순천만 갯벌의 짱뚱어로 끓인 전통 짱뚱어탕 전문점입니다.', '전남 순천시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(115, 58, 4, 900, '순천만 화훼원', '순천만 인근에서 재배한 습지 식물과 야생화를 판매합니다.', '전남 순천시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 59 (전남 - 목포 항구장터)
(116, 59, 1, 100, '목포 홍어상회', '목포 명물 삭힌 홍어와 홍어 요리 재료를 전문으로 판매합니다.', '전남 목포시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(117, 59, 2, 300, '목포 세발낙지', '목포 특산 세발낙지 요리와 낙지 관련 식재료를 판매합니다.', '전남 목포시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 60 (전남 - 나주 남평장터)
(118, 60, 3, 300, '나주 곰탕집', '나주 전통 방식으로 끓인 진하고 맑은 나주곰탕 전문점입니다.', '전남 나주시', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(119, 60, 4, 400, '나주 배상회', '나주 특산 황금배와 배 가공식품(배즙, 배잼)을 판매합니다.', '전남 나주시', 100, 'pending', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 61 (광주 - 양동시장)
(120, 61, 1, 200, '양동 떡방앗간', '광주 전통 방식으로 만든 각종 떡과 한과를 판매합니다.', '광주 서구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(121, 61, 2, 100, '양동 생선가게', '광주 양동시장 대표 생선가게로 신선한 제철 생선을 판매합니다.', '광주 서구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 62 (광주 - 대인시장)
(122, 62, 3, 700, '대인 아트마켓', '광주 대인시장 예술가들의 작품과 독립 굿즈를 판매합니다.', '광주 동구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(123, 62, 4, 600, '대인 빈티지소품', '대인시장 감성 빈티지 소품과 리빙 인테리어 제품을 판매합니다.', '광주 동구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 63 (광주 - 말바우 전통시장)
(124, 63, 1, 400, '말바우 채소상회', '광주 북구 지역 농가의 신선한 채소와 나물류를 직거래로 판매합니다.', '광주 북구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(125, 63, 2, 300, '말바우 한식뷔페', '저렴한 가격에 다양한 전통 한식 반찬을 뷔페식으로 즐길 수 있습니다.', '광주 북구', 100, 'close', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- market_id 64 (광주 - 송정5일장)
(126, 64, 3, 200, '송정 닭요리', '광주 송정 5일장 명물 닭요리와 닭한마리를 전통 방식으로 제공합니다.', '광주 광산구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(127, 64, 4, 800, '송정 약초상회', '광주 근교 산지에서 채취한 각종 약초와 건강 식품을 판매합니다.', '광주 광산구', 100, 'open', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);