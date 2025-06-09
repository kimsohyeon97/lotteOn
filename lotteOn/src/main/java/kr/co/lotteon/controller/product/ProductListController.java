package kr.co.lotteon.controller.product;

import kr.co.lotteon.dto.page.PageRequestDTO;
import kr.co.lotteon.dto.page.PageResponseDTO;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.service.config.ConfigService;
import kr.co.lotteon.service.product.ProductListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductListController {

    private final ProductListService productListService;
    private final RedisTemplate<String, String> redisTemplate;
    private final ConfigService configService;


    // 상품 목록 - 첫 페이지 진입용
    @GetMapping("/product/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        List<ProductDTO> productDTOList = productListService.selectBestAllForList(pageRequestDTO.getSubCateNo());
        model.addAttribute(productDTOList);
        return "/product/list/list";
    }


    // 상품 목록 데이터 (Ajax 요청 처리)
    @GetMapping("/product/ajaxList")
    @ResponseBody
    public PageResponseDTO ajaxList(PageRequestDTO pageRequestDTO, @RequestParam(value = "view", defaultValue = "list") String view) {
        PageResponseDTO pageResponseDTO = productListService.sortedProducts(pageRequestDTO);
        pageResponseDTO.setSortType(pageRequestDTO.getSortType());
        pageResponseDTO.setPeriod(pageRequestDTO.getPeriod());
        pageResponseDTO.setView(view);
        return pageResponseDTO;
    }


    // 상품 목록 검색 - 첫 페이지 진입용
    @GetMapping("/product/searchList")
    public String searchList(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "/product/list/searchList";
    }


    // 상품 목록 데이터 (Ajax 요청 처리)
    @GetMapping("/product/ajaxSearchList")
    @ResponseBody
    public PageResponseDTO ajaxSearchList(PageRequestDTO pageRequestDTO, @RequestParam(value = "view", defaultValue = "list") String view) {
        PageResponseDTO pageResponseDTO = productListService.sortedSearchProducts(pageRequestDTO);
        pageResponseDTO.setSortType(pageRequestDTO.getSortType());
        pageResponseDTO.setPeriod(pageRequestDTO.getPeriod());
        pageResponseDTO.setView(view);
        return pageResponseDTO;
    }


    // Redis 연관검색 캐싱 처리
    @GetMapping("/product/autocomplete")
    @ResponseBody
    public List<String> autocomplete(@RequestParam String keyword) {
        List<String> results =  configService.getAutocompleteSuggestions(keyword);
        return results;
    }


}