package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.ItemDto;
import com.busanit.jpashop.dto.ItemFormDto;
import com.busanit.jpashop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "/item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto,
                          BindingResult bindingResult,                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList ) {
        // 유효성 검증 : 통과하지 못한 경우 폼으로
        if (bindingResult.hasErrors()) {
            return "/item/itemForm";
        }
        // 아이템 DB에 저장
        itemService.saveItem(itemFormDto, itemImgFileList);
        // 리다이렉트
        return "redirect:/";
    }

    // 아이템 수정 Get 요청
    @GetMapping("/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {

        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("itemFormDto", itemFormDto);

        return "item/itemForm";
    }
}
