use haetssal_jangteo;

-- 회원 테이블
create table tbl_user (
                          id bigint unsigned auto_increment PRIMARY KEY,
                          user_email varchar(255) UNIQUE NOT NULL,
                          user_password varchar(255),
                          user_phone varchar(255) UNIQUE NOT NULL,
                          user_type enum('normal', 'seller', 'admin'),
                          user_name varchar(100) NOT NULL,
                          user_intro longtext,
                          user_visit_count int default 1,
                          user_latest_login datetime default current_timestamp(),
                          user_state enum('active', 'inactive') default 'active',
                          created_datetime datetime default current_timestamp,
                          updated_datetime datetime default current_timestamp
);

-- 소셜 회원 테이블
create table tbl_auth (
                          id bigint unsigned PRIMARY KEY,
                          auth_provider enum('haetssal', 'social'),
                          constraint  fk_user_auth foreign key (id)
                              references tbl_user(id)
);

-- 배송지 테이블
create table tbl_delivery (
    id bigint unsigned auto_increment PRIMARY KEY,
    user_id bigint unsigned not null,
    delivery_address varchar(255) not null,
    delivery_detail_address varchar(255) not null,
    delivery_receiver varchar(100) not null,
    delivery_is_main boolean default false,
    receiver_phone varchar(255) not null,
    created_datetime datetime default current_timestamp,
    constraint fk_user_delivery foreign key (user_id)
    references tbl_user(id)
);

-- 주문 테이블
create table tbl_order (
   id bigint unsigned primary key,
   user_id bigint unsigned not null,
   order_delivery_type enum('post', 'take'),
   order_state enum('pending', 'complete') default 'pending',
   order_purchase_date datetime default current_timestamp,
   order_take_date datetime not null,
   constraint fk_payment_user foreign key (user_id)
   references tbl_user(id)
);

-- 결제 테이블
create table tbl_payment (
    id bigint unsigned primary key,
    user_id bigint unsigned not null,
    item_id bigint unsigned not null,
    payment_state enum('pending', 'shipping', 'complete', 'canceled') default 'pending',
    constraint fk_pay_user foreign key (user_id)
    references tbl_user(id),
    constraint fk_pay_item foreign key (item_id)
    references tbl_item(id)
);

# 장바구니 테이블
create table tbl_cart (
    id bigint unsigned auto_increment primary key,
    user_id bigint unsigned not null,
    constraint fk_cart_user foreign key (user_id)
    references tbl_user(id)
);

create table tbl_cart_item (
    id bigint unsigned auto_increment primary key,
    cart_id bigint unsigned not null,
    item_id bigint unsigned not null,
    item_name varchar(255) not null,
    item_option varchar(255) not null,
    item_price varchar(255) not null,
    item_count int default 1,
    constraint fk_cart foreign key (cart_id)
        references tbl_cart(id),
    constraint fk_item foreign key (item_id)
        references tbl_item(id)
);

-- 판매자 테이블
create table tbl_seller (
    id bigint unsigned PRIMARY KEY,
    seller_bank_name varchar(100) NOT NULL,
    seller_depositor varchar(100) NOT NULL,
    seller_account_number varchar(255) UNIQUE NOT NULL,
    seller_state enum('pending', 'approved', 'denied') default 'pending',
    constraint fk_user_id foreign key (id)
    references tbl_user(id)
);

-- 장터 테이블
create table tbl_market (
                            id bigint unsigned PRIMARY KEY,
                            market_region varchar(100) NOT NULL,
                            market_name varchar(255) NOT NULL,
                            market_location varchar(255) NOT NULL,
                            market_state enum('active', 'inactive') default 'active',
                            created_datetime datetime default current_timestamp,
                            updated_datetime datetime default current_timestamp
);


-- 카테고리 테이블
create table tbl_category (
                              id bigint unsigned PRIMARY KEY,
                              category_name varchar(100) NOT NULL
);

-- 가게 테이블
create table tbl_store (
                           id bigint unsigned auto_increment PRIMARY KEY,
                           store_market_id bigint unsigned NOT NULL,
                           store_owner_id bigint unsigned NOT NULL,
                           store_category_id bigint unsigned not null,
                           store_name varchar(255) NOT NULL,
                           store_intro longtext NOT NULL,
                           store_address varchar(255) NOT NULL,
                           store_score int default 100,
                           store_state enum('pending', 'denied', 'open', 'close') default 'pending',
                           store_is_confirmed boolean default false,
                           created_datetime datetime default current_timestamp,
                           updated_datetime datetime default current_timestamp,
                           constraint fk_market_store foreign key(store_market_id)
                               references tbl_market (id),
                           constraint fk_owner_user foreign key (store_owner_id)
                               references tbl_user (id)
);

-- 카테고리 테이블
create table tbl_category (
                              id bigint unsigned PRIMARY KEY,
                              category_name varchar(100) NOT NULL
);



create table tbl_sub_category (
                                  id bigint unsigned PRIMARY KEY,
                                  category_name varchar(100) NOT NULL,
                                  parent_category_id bigint unsigned NOT NULL,
                                  constraint fk_parent_category foreign key (parent_category_id)
                                      references tbl_category(id)
);

-- 상품 테이블
create table tbl_item (

    id bigint unsigned auto_increment PRIMARY KEY,
    item_store_id bigint unsigned NOT NULL,
    item_category_id bigint unsigned NOT NULL,
    item_name varchar(255) NOT NULL,
    item_type varchar(100) NOT NULL default 'normal',
    item_stock varchar(255) default '0',
    item_price varchar(255) default '0',
    item_delivery_fee varchar(255) default '0',
    item_content longtext NOT NULL,
    item_state enum('active', 'inactive') default 'active',
    item_view_count int default 0,
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_item_store foreign key (item_store_id)
    references tbl_store(id),
    constraint fk_item_category foreign key (item_category_id)
    references tbl_category(id)

);

-- 상품 옵션 테이블
create table tbl_item_option (
    id bigint unsigned auto_increment PRIMARY KEY,
    option_item_id bigint unsigned NOT NULL,
    option_name varchar(255) NOT NULL,
    option_detail longtext NOT NULL,
    option_price int default 0,
    option_stock int default 0,
    constraint fk_option_item foreign key (option_item_id)
    references tbl_item(id)
);

-- 첨부파일 테이블
create table tbl_file (
    id bigint unsigned auto_increment PRIMARY KEY,
    file_type enum('image', 'document') NOT NULL,
    file_name varchar(255) NOT NULL,
    file_origin_name varchar(255) NOT NULL,
    file_saved_path varchar(255) NOT NULL,
    file_size varchar(255) not null,
    created_datetime datetime default current_timestamp
);

-- 검색어 테이블
create table tbl_keyword (
                             id bigint unsigned auto_increment PRIMARY KEY,
                             content varchar(255) NOT NULL,
                             keyword_member_id bigint unsigned NOT NULL,
                             constraint fk_keyword_user foreign key (keyword_member_id)
                                 references tbl_user(id)
);

-- 후기 테이블
create table tbl_review (
    id bigint unsigned auto_increment PRIMARY KEY,
    review_item_id bigint unsigned NOT NULL,
    review_user_id bigint unsigned NOT NULL,
    review_score_quality int NOT NULL,
    review_score_delivery int NOT NULL,
    review_score_kind int NOT NULL,
    review_content longtext NOT NULL,
    review_state enum('active', 'inactive') default 'active',
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_review_item foreign key (review_item_id)
    references tbl_item(id),
    constraint fk_review_user foreign key (review_user_id)
    references tbl_user(id)
                            
);

-- 찜 테이블
create table tbl_like_item (
                               id bigint unsigned auto_increment PRIMARY KEY,
                               like_user_id bigint unsigned NOT NULL,
                               like_item_id bigint unsigned NOT NULL,
                               created_datetime datetime default current_timestamp,
                               constraint fk_like_user foreign key (like_user_id)
                                   references tbl_user(id),
                               constraint fk_like_item foreign key (like_item_id)
                                   references tbl_item(id)
);

-- 신고 테이블
create table tbl_report (
                            id bigint unsigned auto_increment PRIMARY KEY,
                            report_type enum('market', 'seller', 'report') default 'report',
                            report_reporter_id bigint unsigned NOT NULL,
                            created_datetime datetime default current_timestamp
);

# -----------------------------------
# 첨부파일 관련 table
create table tbl_file_market (
                                 file_id bigint unsigned NOT NULL,
                                 market_id bigint unsigned NOT NULL,
                                 constraint fk_file_market foreign key (file_id)
                                     references tbl_file(id),
                                 constraint fk_target_market foreign key (market_id)
                                     references tbl_market(id)
);

create table tbl_file_item (
                               file_id bigint unsigned NOT NULL,
                               item_id bigint unsigned NOT NULL,
                               constraint fk_file_item foreign key (file_id)
                                   references tbl_file(id),
                               constraint fk_target_item foreign key (item_id)
                                   references tbl_item(id)
);

create table tbl_file_user (
                               file_id bigint unsigned NOT NULL,
                               user_id bigint unsigned NOT NULL,
                               constraint fk_file_user foreign key (file_id)
                                   references tbl_file(id),
                               constraint fk_target_user foreign key (user_id)
                                   references tbl_user(id)
);

create table tbl_file_report (
                                 file_id bigint unsigned NOT NULL,
                                 report_id bigint unsigned NOT NULL,
                                 constraint fk_file_report foreign key (file_id)
                                     references tbl_file(id),
                                 constraint fk_target_report foreign key (report_id)
                                     references tbl_report(id)
);

create table tbl_file_review (
                                 file_id bigint unsigned NOT NULL,
                                 review_id bigint unsigned NOT NULL,
                                 constraint fk_file_review foreign key (file_id)
                                     references tbl_file(id),
                                 constraint fk_target_review foreign key (review_id)
                                     references tbl_review(id)
);

# -------------------------------
# 신고 테이블 관련 테이블
create table tbl_report_market (
                                   report_id bigint unsigned NOT NULL,
                                   market_id bigint unsigned NOT NULL,
                                   report_state enum('pending', 'approved', 'denied') default 'pending',
                                   constraint fk_report_market foreign key (report_id)
                                       references tbl_report (id),
                                   constraint fk_report_target_market foreign key (market_id)
                                       references tbl_market (id)
);

create table tbl_report_seller (
                                   report_id bigint unsigned NOT NULL,
                                   seller_id bigint unsigned NOT NULL,
                                   report_state enum('pending', 'approved', 'denied') default 'pending',
                                   constraint fk_report_seller foreign key (report_id)
                                       references tbl_report (id),
                                   constraint fk_report_target_seller foreign key (seller_id)
                                       references tbl_seller (id)
);

create table  tbl_report_item (
                                  report_id bigint unsigned NOT NULL,
                                  item_id bigint unsigned NOT NULL,
                                  report_reason varchar(255) NOT NULL,
                                  report_content longtext NOT NULL,
                                  constraint fk_report_item foreign key (report_id)
                                      references tbl_report (id),
                                  constraint fk_report_target_item foreign key (item_id)
                                      references tbl_item (id)
);

create table  tbl_report_user (
                                  report_id bigint unsigned NOT NULL,
                                  user_id bigint unsigned NOT NULL,
                                  report_reason varchar(255) NOT NULL,
                                  report_content longtext NOT NULL,
                                  constraint fk_report_user foreign key (report_id)
                                      references tbl_report (id),
                                  constraint fk_report_target_user foreign key (user_id)
                                      references tbl_user (id)
);