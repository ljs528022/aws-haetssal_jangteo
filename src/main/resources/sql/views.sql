use haetssal_jangteo;

# 상품 이미지를 조회하는 view
# create view vw_file_item as
# select
#     f.id,
#     f.file_type,
#     f.file_name,
#     f.file_origin_name,
#     f.file_saved_path,
#     f.file_size,
#     f.created_datetime,
#     fi.item_id,
#     fi.file_item_type
# from tbl_file_item fi
# join tbl_file f on fi.id = f.id;

# 유저 프로필을 조회하는 view
create view vw_file_user as
select
    f.id,
    f.file_type,
    f.file_name,
    f.file_origin_name,
    f.file_saved_path,
    f.file_size,
    f.created_datetime,
    fu.user_id
from tbl_file_user fu
join tbl_file f on fu.id = f.id;

# 가게 프로필 이미지을 조회하는 view
create view vw_file_store as
select
    f.id,
    f.file_type,
    f.file_name,
    f.file_origin_name,
    f.file_saved_path,
    f.file_size,
    f.created_datetime,
    fs.store_id
from tbl_file_store fs
join tbl_file f on fs.id = f.id;

# 상품의 세부 정보를 조회하는 뷰
create view vw_item_detail as
select
    i.id,
    i.item_name,
    i.item_price,
    i.item_stock,
    i.item_delivery_fee,
    i.item_content,
    i.item_view_count,

    c.id as itemCategoryId,
    c.category_name as itemCategoryName,
    sc.id as itemSubCategoryId,
    sc.category_name as itemSubCategoryName,

    s.id as storeId,
    s.store_name,
    s.store_score,

    u.user_latest_login as ownerLatestLogin,

    f.file_saved_path as storeProfilePath,
    f.file_name as storeProfileName,
    f.file_origin_name as storeProfileOriginName,

    (select count(*)
    from tbl_item i2
    where i2.item_store_id = s.id
    and i2.item_state = 'active') as storeItemCount
from tbl_item i
inner join tbl_category c on i.item_category_id = c.id
left join tbl_sub_category sc on i.item_subcategory_id = sc.id
inner join tbl_store s on i.item_store_id = s.id
inner join tbl_user u on s.store_owner_id = u.id
left join tbl_file_store fs on fs.store_id = s.id
left join tbl_file f on fs.id = f.id;

drop view vw_item_detail;

# 가게의 상세 정보를 조회하는 view
create view vw_store_detail as
select
    s.id,
    s.store_market_id,
    s.store_owner_id,
    s.store_name,
    s.store_intro,
    s.store_address,
    s.store_score,

    s.store_category_id,
    c.category_name as storeCategoryName,

    u.user_latest_login as ownerLatestLogin,

    f.id as fileId,
    f.file_name as fileName,
    f.file_origin_name as fileOriginName,
    f.file_saved_path as fileSavedPath
from tbl_store s
join tbl_user u on s.store_owner_id = u.id
inner join tbl_category c on s.store_category_id = c.id
left join tbl_file_store fs on fs.store_id = s.id
left join tbl_file f on f.id = fs.id;

drop view vw_store_detail;

# 가게 id 해당 가게의 상품의 후기 조회
create view vw_store_review as
select
    r.id,
    r.review_item_id      as reviewItemId,
    r.review_user_id      as reviewUserId,
    r.review_score_quality as reviewScoreQuality,
    r.review_score_delivery as reviewScoreDelivery,
    r.review_score_kind   as reviewScoreKind,
    r.review_content      as reviewContent,
    r.review_state        as reviewState,
    r.created_datetime    as createdDatetime,
    r.updated_datetime    as updatedDatetime,
    i.item_name           as itemName,
    i.item_price          as itemPrice,
    s.id                  as storeId,
    s.store_name          as storeName,
    u.user_name           as userName
from tbl_review r
join tbl_item i on r.review_item_id = i.id
join tbl_store s on i.item_store_id = s.id
join tbl_user u on r.review_user_id = u.id;

drop view vw_store_review;

# 장바구니의 상품 상세 정보 조회
create view vw_cart_item as
select
    ci.id,
    ci.cart_id as cartId,
    c.user_id as userId,
    ci.item_id as itemId,
    ci.item_name as itemName,
    ci.item_option as itemOption,
    ci.item_price as itemPrice,
    ci.item_count as itemCount,
    i.item_delivery_fee as itemDeliveryFee,

    ct.category_name as categoryName,
    sct.category_name as subCategoryName,

    s.store_name as storeName,

    f.file_name as fileName,
    f.file_saved_path as fileSavedPath
from tbl_cart_item ci
join tbl_cart c on ci.cart_id = c.id
left join tbl_item i on ci.item_id = i.id
inner join tbl_category ct on ct.id = i.item_category_id
inner join tbl_sub_category sct on sct.id = i.item_subcategory_id
inner join tbl_store s on s.id = i.item_store_id
left join (
    select fi.item_id, min(fi.id) as file_id
    from tbl_file_item fi
    group by fi.item_id
) fi_first on fi_first.item_id = i.id
left join tbl_file f on f.id = fi_first.file_id;


drop view vw_cart_item;
