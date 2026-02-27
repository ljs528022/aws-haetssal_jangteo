package com.app.haetssal_jangteo.controller.item;

import com.app.haetssal_jangteo.common.search.Search;
import com.app.haetssal_jangteo.dto.ItemDTO;
import com.app.haetssal_jangteo.dto.ItemDescImageDTO;
import com.app.haetssal_jangteo.dto.ItemDetailDTO;
import com.app.haetssal_jangteo.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item/**")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/write")
    public String goToWriteForm() {
        return "item/item-write";
    }

    @PostMapping("/write")
    public RedirectView write(ItemDTO itemDTO,
                              @RequestParam("itemThumbnail") ArrayList<MultipartFile> itemThumbnails,
                              @RequestParam("itemDescImages") ArrayList<MultipartFile> itemDescImages,
                              @RequestParam("itemSellerImages") ArrayList<MultipartFile> itemSellerImages,
                              @RequestParam("itemRefundImages") ArrayList<MultipartFile> itemRefundImages,
                              RedirectAttributes redirectAttributes) {
        itemService.save(itemDTO, itemThumbnails, itemDescImages, itemSellerImages, itemRefundImages);
        redirectAttributes.addAttribute("id", itemDTO.getId());
        return new RedirectView("/item/detail");
    }

    @GetMapping("/detail")
    public String goToDetail(Long id, Model model) {
        model.addAttribute("item", itemService.detail(id));
        model.addAttribute("sameCategoryItems", itemService.getSameCategoryItems(id));
        // 추후에 다른 상품도 받아오기
        return "/item/item-detail";
    }

    @GetMapping("/update")
    public String goToUpdate(Long id, Model model) {
        ItemDetailDTO item = itemService.detail(id); // 상품 상세 정보
        ItemDescImageDTO descImages = itemService.getItemDescImages(id); // 상품 설명 이미지들
        model.addAttribute("item", item);
        model.addAttribute("descImages", descImages);
        return "item/item-update";
    }

    @PostMapping("/update")
    public RedirectView update(ItemDTO itemDTO,
                               @RequestParam("itemThumbnail") ArrayList<MultipartFile> itemThumbnails,
                               @RequestParam("itemDescImages") ArrayList<MultipartFile> itemDescImages,
                               @RequestParam("itemSellerImages") ArrayList<MultipartFile> itemSellerImages,
                               @RequestParam("itemRefundImages") ArrayList<MultipartFile> itemRefundImages,
                               RedirectAttributes redirectAttributes
                               ) {
        itemService.update(itemDTO, itemThumbnails, itemDescImages, itemSellerImages, itemRefundImages);
        redirectAttributes.addAttribute("id", itemDTO.getId());
        return new RedirectView("/item/detail");
    }

    @GetMapping("delete")
    public RedirectView delete(Long id) {
        itemService.delete(id);
        return new RedirectView("/home");
    }
}