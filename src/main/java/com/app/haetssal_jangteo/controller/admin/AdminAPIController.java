package com.app.haetssal_jangteo.controller.admin;

import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/**")
@RequiredArgsConstructor
@Slf4j
public class AdminAPIController {

    private final AdminService adminService;

    @GetMapping("items/{page}")
    public ItemWithPagingDTO getItems(@PathVariable int page, Search search) {
        log.info("관리자 상품 목록 조회 - 페이지: {}, 검색조건: {}", page, search);
        return adminService.list(page, search);
    }

    @PutMapping("items/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        log.info("상품 수정 - ID: {}, 상품정보: {}", id, itemDTO);
        itemDTO.setId(id);
        adminService.update(itemDTO, null);
    }

    @GetMapping("items/categories")
    public List<String> getCategories() {
        log.info("카테고리 목록 조회");
        return adminService.findCategories();
    }

    @GetMapping("stores/{page}")
    public StoreWithPagingDTO getStores(@PathVariable int page, Search search) {
        log.info("관리자 상점 목록 조회 - 페이지: {}, 검색조건: {}", page, search);
        return adminService.storeList(page, search);
    }

    @PutMapping("stores/{id}")
    public void updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        log.info("상점 수정 - ID: {}, 상점정보: {}", id, storeDTO);
        storeDTO.setId(id);
        adminService.updateStore(storeDTO);
    }

    @GetMapping("stores/regions")
    public List<String> getStoreRegions() {
        log.info("상점 지역 목록 조회");
        return adminService.findStoreRegions();
    }

    @GetMapping("users/{page}")
    public UserWithPagingDTO getUsers(@PathVariable int page, Search search) {
        log.info("관리자 사용자 목록 조회 - 페이지: {}, 검색조건: {}", page, search);
        return adminService.userList(page, search);
    }

    @PutMapping("users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("사용자 수정 - ID: {}, 사용자정보: {}", id, userDTO);
        userDTO.setId(id);
        adminService.updateUser(userDTO);
    }
}
