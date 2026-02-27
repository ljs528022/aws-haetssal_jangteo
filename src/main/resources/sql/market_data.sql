INSERT INTO tbl_market (id, market_region, market_name, market_location, market_state, created_datetime, updated_datetime) VALUES
-- 서울 (4개)
(5,  '서울', '광화문 전통시장', '서울특별시 종로구 광화문광장 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6,  '서울', '마포 나눔장터', '서울특별시 마포구 마포대로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7,  '서울', '동대문 새벽시장', '서울특별시 동대문구 동대문디자인플라자 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8,  '서울', '노량진 활어장터', '서울특별시 동작구 노량진수산시장 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 경기 (4개)
(9,  '경기', '수원 팔달문장터', '경기도 수원시 팔달구 팔달문로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, '경기', '성남 모란민속시장', '경기도 성남시 중원구 모란동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, '경기', '안양 중앙알뜰장터', '경기도 안양시 만안구 안양역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, '경기', '고양 행주장터', '경기도 고양시 덕양구 행주산성 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 인천 (4개)
(13, '인천', '인천 신포국제시장', '인천광역시 중구 신포동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, '인천', '부평 깡시장', '인천광역시 부평구 부평동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, '인천', '강화 풍물장터', '인천광역시 강화군 강화읍 강화산성 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(16, '인천', '송도 주말벼룩시장', '인천광역시 연수구 송도국제도시 센트럴파크 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 강원 (4개)
(17, '강원', '강릉 중앙시장', '강원특별자치도 강릉시 중앙동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, '강원', '춘천 낭만장터', '강원특별자치도 춘천시 명동 닭갈비골목 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, '강원', '속초 관광수산시장', '강원특별자치도 속초시 중앙로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, '강원', '원주 미로예술시장', '강원특별자치도 원주시 중앙동 원주역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 충북 (4개)
(21, '충북', '청주 육거리종합시장', '충청북도 청주시 상당구 육거리 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, '충북', '충주 자유시장', '충청북도 충주시 성내동 충주역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, '충북', '제천 약초장터', '충청북도 제천시 의림동 약초시장 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, '충북', '옥천 전통오일장', '충청북도 옥천군 옥천읍 옥천역 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 충남 (4개)
(25, '충남', '천안 중앙시장', '충청남도 천안시 동남구 중앙동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(26, '충남', '아산 온천장터', '충청남도 아산시 온양온천역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(27, '충남', '공주 산성시장', '충청남도 공주시 산성동 공산성 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(28, '충남', '서산 동부시장', '충청남도 서산시 동문동 서산시청 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 대전 (4개)
(29, '대전', '대전 중앙시장', '대전광역시 동구 중앙로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(30, '대전', '유성 온천장터', '대전광역시 유성구 온천동 유성온천역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(31, '대전', '둔산 열린장터', '대전광역시 서구 둔산동 갤러리아타임월드 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(32, '대전', '대덕 한밭장터', '대전광역시 대덕구 오정동 일대', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 경북 (4개)
(33, '경북', '안동 구시장', '경상북도 안동시 옥동 안동역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(34, '경북', '경주 성동시장', '경상북도 경주시 성동동 황리단길 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(35, '경북', '포항 죽도시장', '경상북도 포항시 북구 죽도동 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(36, '경북', '구미 선산장터', '경상북도 구미시 선산읍 선산공설시장 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 경남 (4개)
(37, '경남', '창원 마산어시장', '경상남도 창원시 마산합포구 어시장로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(38, '경남', '진주 중앙시장', '경상남도 진주시 중앙동 진주성 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(39, '경남', '통영 서호시장', '경상남도 통영시 서호동 통영항 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(40, '경남', '거제 고현장터', '경상남도 거제시 고현동 거제시청 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 부산 (4개)
(41, '부산', '부산 국제시장', '부산광역시 중구 신창동 국제시장로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(42, '부산', '자갈치 수산시장', '부산광역시 중구 자갈치해안로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(43, '부산', '부산진 시장', '부산광역시 부산진구 부전동 서면역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(44, '부산', '기장 미역장터', '부산광역시 기장군 기장읍 기장시장로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 울산 (4개)
(45, '울산', '울산 중앙시장', '울산광역시 중구 성남동 울산중앙시장 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(46, '울산', '태화강 장터', '울산광역시 중구 태화동 태화강 국가정원 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(47, '울산', '울주 언양알프스시장', '울산광역시 울주군 언양읍 언양시장로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(48, '울산', '남구 삼산장터', '울산광역시 남구 삼산동 삼산롯데백화점 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 대구 (4개)
(49, '대구', '서문시장', '대구광역시 중구 대신동 서문시장대로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(50, '대구', '칠성시장', '대구광역시 북구 칠성동 칠성시장로 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(51, '대구', '대구 방천시장', '대구광역시 중구 방천동 김광석다리길 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(52, '대구', '북구 팔거장터', '대구광역시 북구 팔달동 팔거천 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 전북 (4개)
(53, '전북', '전주 남부시장', '전라북도 전주시 완산구 풍남동 한옥마을 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(54, '전북', '군산 공설시장', '전라북도 군산시 중앙로 군산역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(55, '전북', '익산 중앙시장', '전라북도 익산시 중앙동 익산역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(56, '전북', '정읍 샘고을시장', '전라북도 정읍시 수성동 정읍역 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 전남 (4개)
(57, '전남', '여수 수산시장', '전라남도 여수시 교동 여수엑스포역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(58, '전남', '순천 아랫장', '전라남도 순천시 중앙동 순천역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(59, '전남', '목포 항구장터', '전라남도 목포시 항동 목포항 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(60, '전남', '나주 남평장터', '전라남도 나주시 남평읍 남평역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 광주 (4개)
(61, '광주', '광주 양동시장', '광주광역시 서구 양동 광주송정역 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(62, '광주', '광주 대인시장', '광주광역시 동구 대인동 국립아시아문화전당 인근', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(63, '광주', '말바우 전통시장', '광주광역시 북구 문흥동 말바우시장 일대', 'active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(64, '광주', '광산 송정5일장', '광주광역시 광산구 송정동 광주송정역 인근', 'inactive', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;