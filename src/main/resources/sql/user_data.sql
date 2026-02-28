INSERT INTO tbl_user (id, user_email, user_phone, user_name, user_intro)
VALUES
    ( 1, 'freshmarket_j@gmail.com',  '01012345678', '신선한하루',     '매일 아침 신선한 재료로 요리하는 걸 좋아해요.'),
    ( 2, 'greentable_s@naver.com',   '01023456789', '초록밥상',       '가족을 위해 건강한 밥상을 차리고 싶어요.'),
    ( 3, 'cookinglove_m@outlook.com','01034567890', '주방의달인',     '요리가 취미인 평범한 주부예요.'),
    ( 4, 'organic_life@gmail.com',   '01045678901', '유기농생활자',   '먹거리만큼은 꼼꼼하게 따지는 편이에요.'),
    ( 5, 'homemeal_k@naver.com',     '01056789012', '집밥요정',       '외식보다 집밥이 훨씬 좋아요.'),
    ( 6, 'seafoodlvr@gmail.com',     '01067890123', '해산물마니아',   '싱싱한 수산물이라면 뭐든 환영이에요.'),
    ( 7, 'meatmaster_h@naver.com',   '01078901234', '고기굽는남자',   '주말엔 꼭 바베큐를 해야 직성이 풀려요.'),
    ( 8, 'veggiefan_e@gmail.com',    '01089012345', '채소예찬론자',   '텃밭 가꾸는 게 요즘 가장 큰 즐거움이에요.'),
    ( 9, 'healthyfood_p@naver.com',  '01090123456', '건강밥상연구가', '몸에 좋은 건강식품 정보를 모으고 있어요.'),
    (10, 'kimchi_queen@outlook.com', '01011223344', '김치요정',       '직접 담근 김치가 제일 맛있더라고요.'),
    (11, 'spicylover_r@gmail.com',   '01022334455', '매운맛탐험가',   '얼큰하고 칼칼한 음식이라면 무조건 좋아요.'),
    (12, 'kitchenlab_i@naver.com',   '01033445566', '주방실험실',     '새로운 주방용품 써보는 게 취미예요.'),
    (13, 'simplelife_o@hotmail.com', '01044556677', '심플라이프',     '좋은 재료로 간단하게 만드는 요리가 좋아요.'),
    (14, 'ricecooker_u@gmail.com',   '01055667788', '밥솥지킴이',     '따뜻한 밥 한 공기면 하루가 행복해요.'),
    (15, 'saucecollector@naver.com', '01066778899', '소스수집가',     '전국의 장류와 소스를 모으는 게 취미예요.'),
    (16, 'morningmarket_w@gmail.com','01077889900', '새벽시장단골',   '새벽에 나온 신선한 것들을 제일 좋아해요.'),
    (17, 'nutritionist_q@outlook.com','01088990011','영양균형러',     '영양 균형 맞춘 식단을 짜는 게 일상이에요.'),
    (18, 'localfarm_z@naver.com',    '01099001122', '로컬팜지지자',   '우리 지역 농산물 소비에 관심이 많아요.'),
    (19, 'snackaddict_x@hotmail.com','01010011223', '간식탐정',       '맛있는 가공식품 신상은 꼭 먹어봐야 해요.'),
    (20, 'cleanhouse_c@gmail.com',   '01021132334', '청결생활러',     '주방과 집을 항상 깔끔하게 유지하고 싶어요.');

-- ✅ 인증 정보 삽입
INSERT INTO tbl_auth (id, auth_provider)
VALUES
    ( 1, 'haetssal'), ( 2, 'haetssal'), ( 3, 'haetssal'), ( 4, 'haetssal'), ( 5, 'haetssal'),
    ( 6, 'haetssal'), ( 7, 'haetssal'), ( 8, 'haetssal'), ( 9, 'haetssal'), (10, 'haetssal'),
    (11, 'haetssal'), (12, 'haetssal'), (13, 'haetssal'), (14, 'haetssal'), (15, 'haetssal'),
    (16, 'haetssal'), (17, 'haetssal'), (18, 'haetssal'), (19, 'haetssal'), (20, 'haetssal');