package com.app.haetssal_jangteo.controller.admin;

import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.*;
import com.app.haetssal_jangteo.mapper.CategoryMapper;
import com.app.haetssal_jangteo.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/items")
    public String items(@RequestParam(defaultValue = "1") int page, Search search, Model model) {
        ItemWithPagingDTO itemWithPagingDTO = adminService.list(page, search);
        model.addAttribute("itemWithPagingDTO", itemWithPagingDTO);
        model.addAttribute("search", search);
        model.addAttribute("categories", adminService.findCategories());
        return "admin/items";
    }

    @GetMapping("/stores")
    public String stores(@RequestParam(defaultValue = "1") int page, Search search, Model model) {
        StoreWithPagingDTO storeWithPagingDTO = adminService.storeList(page, search);
        model.addAttribute("storeWithPagingDTO", storeWithPagingDTO);
        model.addAttribute("search", search);
        model.addAttribute("regions", adminService.findStoreRegions());
        return "admin/stores";
        
    }

    @GetMapping("/users")
    public String users(@RequestParam(defaultValue = "1") int page, Search search, Model model) {
        UserWithPagingDTO userWithPagingDTO = adminService.userList(page, search);
        model.addAttribute("userWithPagingDTO", userWithPagingDTO);
        model.addAttribute("search", search);
        return "admin/users";
    }
}
