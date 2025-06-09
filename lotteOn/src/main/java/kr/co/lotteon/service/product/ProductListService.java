package kr.co.lotteon.service.product;

import com.querydsl.core.Tuple;
import kr.co.lotteon.dto.page.PageRequestDTO;
import kr.co.lotteon.dto.page.PageResponseDTO;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.entity.product.Product;
import kr.co.lotteon.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductListService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    //  베스트 상품 조회
    @Cacheable(value = "bestProductList", key = "#subCateNo")
    public List<ProductDTO> selectBestAllForList(int subCateNo) {
        Page<Tuple> pageProduct = productRepository.selectBestAllForList(subCateNo);

        List<ProductDTO> productDTOList = pageProduct.getContent().stream().map(tuple -> {
            Product product = tuple.get(0, Product.class);
            String company = tuple.get(1, String.class);
            String sNameList = tuple.get(2, String.class);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCompany(company);
            productDTO.setSNameList(sNameList);

            return productDTO;
        }).toList();

        return productDTOList;
    }


    //  정렬된 상품 목록 조회
    @Cacheable(value = "sortedProductList", key = "#pageRequestDTO.toString()")
    public PageResponseDTO sortedProducts(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageProduct = productRepository.sortedProducts(pageRequestDTO, pageable);

        List<ProductDTO> productDTOList = pageProduct.getContent().stream().map(tuple -> {
            Product product = tuple.get(0, Product.class);
            String company = tuple.get(1, String.class);
            String rank = tuple.get(2, String.class);
            String sNameList = tuple.get(3, String.class);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCompany(company);
            productDTO.setRank(rank);
            productDTO.setSNameList(sNameList);

            return productDTO;
        }).toList();

        int total = (int) pageProduct.getTotalElements();

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(productDTOList)
                .total(total)
                .build();
    }


    //  상품 검색 목록 조회
    public PageResponseDTO sortedSearchProducts(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageProduct = productRepository.sortedSearchProducts(pageRequestDTO, pageable);

        List<ProductDTO> productDTOList = pageProduct.getContent().stream().map(tuple -> {
            Product product = tuple.get(0, Product.class);
            String company = tuple.get(1, String.class);
            String rank = tuple.get(2, String.class);
            String sNameList = tuple.get(3, String.class);

            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTO.setCompany(company);
            productDTO.setRank(rank);
            productDTO.setSNameList(sNameList);

            return productDTO;
        }).toList();

        int total = (int) pageProduct.getTotalElements();

        return PageResponseDTO.<ProductDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(productDTOList)
                .total(total)
                .build();
    }





}

