create table tbl_category
(
    id            bigint unsigned not null
        primary key,
    category_name varchar(100)    not null
);

create table tbl_file
(
    id               bigint unsigned auto_increment
        primary key,
    file_type        enum ('image', 'document')         not null,
    file_name        varchar(255)                       not null,
    file_origin_name varchar(255)                       not null,
    file_saved_path  varchar(255)                       not null,
    file_size        varchar(255)                       not null,
    created_datetime datetime default CURRENT_TIMESTAMP null
);

create table tbl_market
(
    id               bigint unsigned                                       not null
        primary key,
    market_region    varchar(100)                                          not null,
    market_name      varchar(255)                                          not null,
    market_location  varchar(255)                                          not null,
    market_state     enum ('active', 'inactive') default 'active'          null,
    created_datetime datetime                    default CURRENT_TIMESTAMP null,
    updated_datetime datetime                    default CURRENT_TIMESTAMP null
);

create table tbl_file_market
(
    id        bigint unsigned not null,
    market_id bigint unsigned not null,
    constraint fk_file_market
        foreign key (id) references tbl_file (id),
    constraint fk_target_market
        foreign key (market_id) references tbl_market (id)
);

create table tbl_report
(
    id                 bigint unsigned auto_increment
        primary key,
    report_type        enum ('market', 'seller', 'report') default 'report'          null,
    report_reporter_id bigint unsigned                                               not null,
    created_datetime   datetime                            default CURRENT_TIMESTAMP null
);

create table tbl_file_report
(
    id        bigint unsigned not null,
    report_id bigint unsigned not null,
    constraint fk_file_report
        foreign key (id) references tbl_file (id),
    constraint fk_target_report
        foreign key (report_id) references tbl_report (id)
);

create table tbl_report_market
(
    report_id    bigint unsigned                                          not null,
    market_id    bigint unsigned                                          not null,
    report_state enum ('pending', 'approved', 'denied') default 'pending' null,
    constraint fk_report_market
        foreign key (report_id) references tbl_report (id),
    constraint fk_report_target_market
        foreign key (market_id) references tbl_market (id)
);

create table tbl_sub_category
(
    id                 bigint unsigned not null
        primary key,
    category_name      varchar(100)    not null,
    parent_category_id bigint unsigned not null,
    constraint fk_parent_category
        foreign key (parent_category_id) references tbl_category (id)
);

create table tbl_user
(
    id                bigint unsigned auto_increment
        primary key,
    user_email        varchar(255)                                          not null,
    user_password     varchar(255)                                          null,
    user_phone        varchar(255)                                          not null,
    user_type         enum ('normal', 'seller', 'admin')                    null,
    user_name         varchar(100)                                          not null,
    user_intro        longtext                                              null,
    user_visit_count  int                         default 1                 null,
    user_latest_login datetime                    default CURRENT_TIMESTAMP null,
    user_state        enum ('active', 'inactive') default 'active'          null,
    created_datetime  datetime                    default CURRENT_TIMESTAMP null,
    updated_datetime  datetime                    default CURRENT_TIMESTAMP null,
    constraint user_email
        unique (user_email),
    constraint user_phone
        unique (user_phone)
);

create table tbl_auth
(
    id            bigint unsigned             not null
        primary key,
    auth_provider enum ('haetssal', 'social') null,
    constraint fk_user_auth
        foreign key (id) references tbl_user (id)
);

create table tbl_cart
(
    id      bigint unsigned auto_increment
        primary key,
    user_id bigint unsigned not null,
    constraint fk_cart_user
        foreign key (user_id) references tbl_user (id)
);

create table tbl_delivery
(
    id                      bigint unsigned auto_increment
        primary key,
    user_id                 bigint unsigned                      not null,
    delivery_address        varchar(255)                         not null,
    delivery_detail_address varchar(255)                         not null,
    delivery_receiver       varchar(100)                         not null,
    delivery_is_main        tinyint(1) default 0                 null,
    receiver_phone          varchar(255)                         not null,
    created_datetime        datetime   default CURRENT_TIMESTAMP null,
    constraint fk_user_delivery
        foreign key (user_id) references tbl_user (id)
);

create table tbl_file_user
(
    id      bigint unsigned not null,
    user_id bigint unsigned not null,
    constraint fk_file_user
        foreign key (id) references tbl_file (id),
    constraint fk_target_user
        foreign key (user_id) references tbl_user (id)
);

create table tbl_keyword
(
    id                bigint unsigned auto_increment
        primary key,
    content           varchar(255)    not null,
    keyword_member_id bigint unsigned not null,
    constraint fk_keyword_user
        foreign key (keyword_member_id) references tbl_user (id)
);

create table tbl_order
(
    id                  bigint unsigned                                        not null
        primary key,
    user_id             bigint unsigned                                        not null,
    order_delivery_type enum ('post', 'take')                                  null,
    order_state         enum ('pending', 'complete') default 'pending'         null,
    order_purchase_date datetime                     default CURRENT_TIMESTAMP null,
    order_take_date     datetime                                               not null,
    constraint fk_payment_user
        foreign key (user_id) references tbl_user (id)
);

create table tbl_report_user
(
    report_id      bigint unsigned not null,
    user_id        bigint unsigned not null,
    report_reason  varchar(255)    not null,
    report_content longtext        not null,
    constraint fk_report_target_user
        foreign key (user_id) references tbl_user (id),
    constraint fk_report_user
        foreign key (report_id) references tbl_report (id)
);

create table tbl_seller
(
    id                    bigint unsigned                                          not null
        primary key,
    seller_bank_name      varchar(100)                                             not null,
    seller_depositor      varchar(100)                                             not null,
    seller_account_number varchar(255)                                             not null,
    seller_state          enum ('pending', 'approved', 'denied') default 'pending' null,
    constraint seller_account_number
        unique (seller_account_number),
    constraint fk_user_id
        foreign key (id) references tbl_user (id)
);

create table tbl_report_seller
(
    report_id    bigint unsigned                                          not null,
    seller_id    bigint unsigned                                          not null,
    report_state enum ('pending', 'approved', 'denied') default 'pending' null,
    constraint fk_report_seller
        foreign key (report_id) references tbl_report (id),
    constraint fk_report_target_seller
        foreign key (seller_id) references tbl_seller (id)
);

create table tbl_store
(
    id                 bigint unsigned auto_increment
        primary key,
    store_market_id    bigint unsigned                                                       not null,
    store_owner_id     bigint unsigned                                                       not null,
    store_category_id  bigint unsigned                                                       not null,
    store_name         varchar(255)                                                          not null,
    store_intro        longtext                                                              not null,
    store_address      varchar(255)                                                          not null,
    store_score        int                                         default 100               null,
    store_state        enum ('pending', 'denied', 'open', 'close') default 'pending'         null,
    store_is_confirmed tinyint(1)                                  default 0                 null,
    created_datetime   datetime                                    default CURRENT_TIMESTAMP null,
    updated_datetime   datetime                                    default CURRENT_TIMESTAMP null,
    constraint fk_market_store
        foreign key (store_market_id) references tbl_market (id),
    constraint fk_owner_user
        foreign key (store_owner_id) references tbl_user (id)
);

create table tbl_file_store
(
    id       bigint unsigned not null,
    store_id bigint unsigned not null,
    constraint fk_file_store
        foreign key (id) references tbl_file (id),
    constraint fk_target_store
        foreign key (store_id) references tbl_store (id)
);

create table tbl_item
(
    id                  bigint unsigned auto_increment
        primary key,
    item_store_id       bigint unsigned                                       not null,
    item_category_id    bigint unsigned                                       not null,
    item_subcategory_id bigint unsigned                                       not null,
    item_name           varchar(255)                                          not null,
    item_type           varchar(100)                default 'normal'          not null,
    item_stock          varchar(255)                default '0'               null,
    item_price          varchar(255)                default '0'               null,
    item_delivery_fee   varchar(255)                default '0'               null,
    item_content        longtext                                              not null,
    item_state          enum ('active', 'inactive') default 'active'          null,
    item_view_count     int                         default 0                 null,
    created_datetime    datetime                    default CURRENT_TIMESTAMP null,
    updated_datetime    datetime                    default CURRENT_TIMESTAMP null,
    constraint fk_item_category
        foreign key (item_category_id) references tbl_category (id),
    constraint fk_item_store
        foreign key (item_store_id) references tbl_store (id),
    constraint fk_item_subcategory
        foreign key (item_subcategory_id) references tbl_sub_category (id)
);

create table tbl_cart_item
(
    id          bigint unsigned auto_increment
        primary key,
    cart_id     bigint unsigned not null,
    item_id     bigint unsigned not null,
    item_name   varchar(255)    not null,
    item_option varchar(255)    not null,
    item_price  varchar(255)    not null,
    item_count  int default 1   null,
    constraint fk_cart
        foreign key (cart_id) references tbl_cart (id),
    constraint fk_item
        foreign key (item_id) references tbl_item (id)
);

create table tbl_file_item
(
    id             bigint unsigned                                     not null,
    item_id        bigint unsigned                                     not null,
    file_item_type enum ('thumbnail', 'desc', 'seller-info', 'refund') not null,
    constraint fk_file_item
        foreign key (id) references tbl_file (id),
    constraint fk_target_item
        foreign key (item_id) references tbl_item (id)
);

create table tbl_item_option
(
    id             bigint unsigned auto_increment
        primary key,
    option_item_id bigint unsigned not null,
    option_name    varchar(255)    not null,
    option_detail  longtext        not null,
    option_price   int default 0   null,
    option_stock   int default 0   null,
    constraint fk_option_item
        foreign key (option_item_id) references tbl_item (id)
);

create table tbl_like_item
(
    id               bigint unsigned auto_increment
        primary key,
    like_user_id     bigint unsigned                    not null,
    like_item_id     bigint unsigned                    not null,
    created_datetime datetime default CURRENT_TIMESTAMP null,
    constraint fk_like_item
        foreign key (like_item_id) references tbl_item (id),
    constraint fk_like_user
        foreign key (like_user_id) references tbl_user (id)
);

create table tbl_payment
(
    id            bigint unsigned                                                         not null
        primary key,
    user_id       bigint unsigned                                                         not null,
    item_id       bigint unsigned                                                         not null,
    payment_state enum ('pending', 'shipping', 'complete', 'cancelled') default 'pending' null,
    constraint fk_pay_item
        foreign key (item_id) references tbl_item (id),
    constraint fk_pay_user
        foreign key (user_id) references tbl_user (id)
);

create table tbl_report_item
(
    report_id      bigint unsigned not null,
    item_id        bigint unsigned not null,
    report_reason  varchar(255)    not null,
    report_content longtext        not null,
    constraint fk_report_item
        foreign key (report_id) references tbl_report (id),
    constraint fk_report_target_item
        foreign key (item_id) references tbl_item (id)
);

create table tbl_review
(
    id                    bigint unsigned auto_increment
        primary key,
    review_item_id        bigint unsigned                                       not null,
    review_user_id        bigint unsigned                                       not null,
    review_score_quality  int                                                   not null,
    review_score_delivery int                                                   not null,
    review_score_kind     int                                                   not null,
    review_content        longtext                                              not null,
    review_state          enum ('active', 'inactive') default 'active'          null,
    created_datetime      datetime                    default CURRENT_TIMESTAMP null,
    updated_datetime      datetime                    default CURRENT_TIMESTAMP null,
    constraint fk_review_item
        foreign key (review_item_id) references tbl_item (id),
    constraint fk_review_user
        foreign key (review_user_id) references tbl_user (id)
);

create table tbl_file_review
(
    id        bigint unsigned not null,
    review_id bigint unsigned not null,
    constraint fk_file_review
        foreign key (id) references tbl_file (id),
    constraint fk_target_review
        foreign key (review_id) references tbl_review (id)
);

