package kr.co.lotteon.service.config;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.lotteon.dto.category.MainCategoryDTO;
import kr.co.lotteon.dto.category.SubCategoryDTO;
import kr.co.lotteon.dto.config.BannerDTO;
import kr.co.lotteon.dto.config.ConfigDTO;
import kr.co.lotteon.dto.config.TermsDTO;
import kr.co.lotteon.dto.config.VersionDTO;
import kr.co.lotteon.dto.page.PageRequestDTO;
import kr.co.lotteon.dto.page.PageResponseDTO;
import kr.co.lotteon.entity.category.MainCategory;
import kr.co.lotteon.entity.category.SubCategory;
import kr.co.lotteon.entity.config.Banner;
import kr.co.lotteon.entity.config.Config;
import kr.co.lotteon.entity.config.Terms;
import kr.co.lotteon.entity.config.Version;
import kr.co.lotteon.entity.product.Product;
import kr.co.lotteon.entity.product.QProduct;
import kr.co.lotteon.entity.user.User;
import kr.co.lotteon.repository.category.MainCategoryRepository;
import kr.co.lotteon.repository.category.SubCategoryRepository;
import kr.co.lotteon.repository.config.BannerRepository;
import kr.co.lotteon.repository.config.ConfigRepository;
import kr.co.lotteon.repository.config.TermsRepository;
import kr.co.lotteon.repository.config.VersionRepository;
import kr.co.lotteon.repository.product.ProductRepository;
import kr.co.lotteon.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfigService {

    private final TermsRepository termsRepository;
    private final VersionRepository versionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ConfigRepository configRepository;
    private final BannerRepository bannerRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;

    private final QProduct qProduct = QProduct.product;

    public TermsDTO findTerms() {
        Terms terms = termsRepository.findById(1).get();
        return modelMapper.map(terms, TermsDTO.class);
    }

    public void modify(String cate, String content) {
        Terms terms = termsRepository.findById(1).get();

        if(cate.equals("terms")){
            terms.setTerms(content);
        }else if(cate.equals("tax")){
            terms.setTax(content);
        }else if(cate.equals("finance")){
            terms.setFinance(content);
        }else if(cate.equals("privacy")){
            terms.setPrivacy(content);
        }else{
            terms.setLocation(content);
        }

        termsRepository.save(terms);

    }



    /*
    * 관리자
    * */

    // 버전 등록 (관리자)

    @CacheEvict(value = "version" , allEntries = true)
    public void saveVersion(VersionDTO versionDTO, UserDetails userDetails) {
        Version version = modelMapper.map(versionDTO, Version.class);
        String uid = userDetails.getUsername();

        User user = userRepository.findById(uid).get();
        version.setUser(user);
        versionRepository.save(version);
    }

    // 버전 출력 (관리자)
    public PageResponseDTO selectAll(PageRequestDTO pageRequestDTO) {

        pageRequestDTO.setSize(10);
        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<Tuple> pageVersion = versionRepository.selectAllForList(pageRequestDTO, pageable);

        List<VersionDTO> DTOList = pageVersion.getContent().stream().map(tuple -> {
            Version version = tuple.get(0, Version.class);
            String uid = tuple.get(1,  String.class);

            VersionDTO versionDTO = modelMapper.map(version, VersionDTO.class);
            versionDTO.setUid(uid);

            return versionDTO;
        }).toList();

        int total = (int) pageVersion.getTotalElements();

        log.info("total: {}", total);
        log.info("versionDTOList: {}", DTOList);

        return PageResponseDTO.<VersionDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(DTOList)
                .total(total)
                .build();

    }

    @CacheEvict(value = "version" , allEntries = true)
    public void deleteVersions(List<Integer> deleteVnos) {
        for(int i : deleteVnos){
            versionRepository.deleteById(i);
        }
    }

    @CacheEvict(value = "config" , allEntries = true)
    public void modifyTitleAndSubTitle(String title, String subTitle) {
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            Config config = configOpt.get();
            config.setTitle(title);
            config.setSubTitle(subTitle);
            configRepository.save(config);
        }else{
            Config config = Config.builder()
                    .title(title)
                    .subTitle(subTitle)
                    .build();

            configRepository.save(config);
        }
    }

    /*
    * 환경 설정
    * */
    public ConfigDTO findSite() {
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            Config config = configOpt.get();
            return modelMapper.map(config, ConfigDTO.class);
        }
        return null;
    }

    @CacheEvict(value = "config" , allEntries = true)
    public void modifyCopyright(String copyright) {
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            Config config = configOpt.get();
            config.setCopyright(copyright);
            configRepository.save(config);
        }else{
            Config config = Config.builder()
                    .copyright(copyright)
                    .build();

            configRepository.save(config);
        }
    }

    @CacheEvict(value = "config" , allEntries = true)
    public void modifyCompany(ConfigDTO configDTO) {
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            Config config = configOpt.get();
            config.setCompanyName(configDTO.getCompanyName());
            config.setCeoName(configDTO.getCeoName());
            config.setBusinessNumber(configDTO.getBusinessNumber());
            config.setOnlineSalesNumber(configDTO.getOnlineSalesNumber());
            config.setAddr1(configDTO.getAddr1());
            config.setAddr2(configDTO.getAddr2());
            configRepository.save(config);
        }else{
            Config config = Config.builder()
                    .companyName(configDTO.getCompanyName())
                    .ceoName(configDTO.getCeoName())
                    .businessNumber(configDTO.getBusinessNumber())
                    .onlineSalesNumber(configDTO.getOnlineSalesNumber())
                    .addr1(configDTO.getAddr1())
                    .addr2(configDTO.getAddr2())
                    .build();
            configRepository.save(config);
        }
    }

    @CacheEvict(value = "config" , allEntries = true)
    public void modifyCustomer(ConfigDTO configDTO) {
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            Config config = configOpt.get();
            config.setHp(configDTO.getHp());
            config.setWorkingHours(configDTO.getWorkingHours());
            config.setEmail(configDTO.getEmail());
            config.setEfinDispute(configDTO.getEfinDispute());
            configRepository.save(config);
        }else{
            Config config = Config.builder()
                    .hp(configDTO.getHp())
                    .workingHours(configDTO.getWorkingHours())
                    .email(configDTO.getEmail())
                    .efinDispute(configDTO.getEfinDispute())
                    .build();
            configRepository.save(config);
        }



    }


    // @Cacheable(value = "slide-banners", key = "#cate")

    // 배너 저장
    @CacheEvict(value = "slide-banners", key = "#bannerDTO.cate")
    public void saveBanner(BannerDTO bannerDTO) {
        System.out.println(bannerDTO.getCate());
        Banner banner = modelMapper.map(bannerDTO, Banner.class);
        bannerRepository.save(banner);
    }

    @Cacheable(value = "slide-banners", key = "#cate")
    public List<BannerDTO> findBannerByCate(String cate) {

        if(cate == null){
            cate = "MAIN1";
        }
        
        List<Banner> bannerList = bannerRepository.findByCateAndEndDayGreaterThanOrderByBnoDesc( cate, LocalDate.now());
        List<BannerDTO> bannerDTOList = new ArrayList<>();

        for(Banner banner : bannerList){
            bannerDTOList.add(modelMapper.map(banner, BannerDTO.class));

            if(bannerDTOList.size() == 5){
                break;
            }
        }

        return bannerDTOList;
    }

    public String SelectTitle(String cate) {
        if(cate == null || cate.equals("MAIN1")){
            return "메인 상단배너";
        }else if(cate.equals("MAIN2")){
            return "메인슬라이더 배너";
        }else if(cate.equals("PRODUCT1")){
            return "상품상세보기 상단배너";
        }else if(cate.equals("MEMBER1")){
            return "회원로그인 상단배너";
        }else{
            return "마이페이지";
        }
    }

    // 배너 상태 변경하기(활성/비활성)
    @CacheEvict(value = "slide-banners", key = "#cate")
    public void changeBanner(int bno, String state, String cate) {
        Optional<Banner> bannerOpt = bannerRepository.findById(bno);
        if(bannerOpt.isPresent()){

            log.info("배너 상태 변경: {}", bannerOpt.get());

            Banner banner = bannerOpt.get();
            banner.setState(state);
            bannerRepository.save(banner);

        }
    }

    // 배너 지우기
    @CacheEvict(value = "slide-banners", key = "#cate")
    public void deleteBanner(List<Integer> deleteVnos, String cate) {
        for(int num : deleteVnos){
            bannerRepository.deleteById(num);
        }
    }

    @Cacheable(value = "slide-banners", key = "#cate")
    public List<BannerDTO> findBanner(String cate) {


        List<Banner> bannerList = bannerRepository.findBannerByCate(cate);
        List<BannerDTO> bannerDTOList = new ArrayList<>();
        for(Banner banner : bannerList){
            bannerDTOList.add(modelMapper.map(banner, BannerDTO.class));
        }

        // List<BannerDTO> bannerDTOList = new ArrayList<>();
        return bannerDTOList;
    }

    public void deleteBannerTimeout() {
        bannerRepository.deleteExpiredBanners(LocalDate.now());
    }

    public List<BannerDTO> findConfigBannerByCate(String cate) {
        if(cate == null){
            cate = "MAIN1";
        }

        List<Banner> bannerList = bannerRepository.findByCateAndEndDay(cate);
        List<BannerDTO> bannerDTOList = new ArrayList<>();

        for(Banner banner : bannerList){
            bannerDTOList.add(modelMapper.map(banner, BannerDTO.class));
        }

        return bannerDTOList;
    }

    public List<MainCategoryDTO> findAllCateGory() {

        List<MainCategory> mainCategories = mainCategoryRepository.findByStateOrderByOrderIndexAsc("활성");
        List<MainCategoryDTO> mainCategoryDTOList = new ArrayList<>();
        for(MainCategory mainCategory : mainCategories){
            List<SubCategory> subCategories = subCategoryRepository.findByMainCategoryAndStateOrderByOrderIndexAsc(mainCategory,"활성");
            List<SubCategoryDTO> subCategoryDTOList = new ArrayList<>();
            if(!subCategories.isEmpty()){
                for(SubCategory subCategory : subCategories){
                    subCategoryDTOList.add(modelMapper.map(subCategory, SubCategoryDTO.class));
                }
            }

            MainCategoryDTO mainCategoryDTO = modelMapper.map(mainCategory, MainCategoryDTO.class);
            mainCategoryDTO.setSubCategories(subCategoryDTOList);
            mainCategoryDTOList.add(mainCategoryDTO);
        }
        return mainCategoryDTOList;
    }

    // 순서 정렬하기(메인)
    public int sortOrderMain(List<Integer> mainCateNo) {

        int i = 1;
        if(!mainCateNo.isEmpty()){
            for(int cateNo : mainCateNo){
                Optional<MainCategory>  mainCategoryOpt = mainCategoryRepository.findById(cateNo);
                if(mainCategoryOpt.isPresent()){
                    MainCategory mainCategory = mainCategoryOpt.get();
                    mainCategory.setOrderIndex(i);
                    mainCategoryRepository.save(mainCategory);
                }
                i++;
            }
        }
        return i;
    }

    // 새로운 카테고리 저장하기
    public void saveNewMain(List<String> newCateNos, int index) {
        if(newCateNos != null){
            for(String  cate : newCateNos){
                MainCategory mainCategory = MainCategory.builder()
                        .mainCategoryName(cate)
                        .orderIndex(index)
                        .build();

                mainCategoryRepository.save(mainCategory);
                index++;
            }
        }

    }


    /*
    * 카테고리 판별
    * 관련된 상품이 없을 경우 바로 삭제
    * 관련된 상품이 있을 경우 상태를 비활성 처리
    * */
    @Transactional
    public void deleteMainCategory(int mainCateNo) {

        MainCategory mainCategory = MainCategory.builder()
                .mainCateNo(mainCateNo)
                .build();

        List<SubCategory> existSubCategories = subCategoryRepository.findByMainCategory(mainCategory);

        // 서브카테고리 존재 확인
        if(existSubCategories.isEmpty()){
            mainCategoryRepository.deleteById(mainCateNo);
            return;
        }

        //상품 존재 확인
        for(SubCategory subCategory : existSubCategories){
            List<Product> existProducts = productRepository.findBySubCategory(subCategory);
            if(!existProducts.isEmpty()){
                MainCategory category= mainCategoryRepository.findById(mainCateNo).get();
                category.setState("비활성");
                mainCategoryRepository.save(category);
                return;
            }
        }

        // 서브 카테고리 삭제 후 메인 카테고리 삭제
        subCategoryRepository.deleteByMainCategory(mainCategory);
        mainCategoryRepository.deleteById(mainCateNo);


    }

    // 서브 카테고리 삭제
    public void deleteSubCategory(int subCateNo) {
        SubCategory subCategory = SubCategory.builder()
                .subCateNo(subCateNo)
                .build();
        List<Product> products = productRepository.findBySubCategory(subCategory);
        if(products.isEmpty()){
            subCategoryRepository.deleteById(subCateNo);
            return;
        }

        SubCategory category = subCategoryRepository.findById(subCateNo).get();
        category.setState("비활성");
        subCategoryRepository.save(category);

    }

    public int sortOrderSub(List<Integer> subCateNo) {
        int i = 1;
        if(!subCateNo.isEmpty()){
            for(int cateNo : subCateNo){
                Optional<SubCategory>  CategoryOpt = subCategoryRepository.findById(cateNo);
                if(CategoryOpt.isPresent()){
                    SubCategory category = CategoryOpt.get();
                    category.setOrderIndex(i);
                    subCategoryRepository.save(category);
                }
                i++;
            }
        }
        return i;
    }

    // 새로운 서브 카테고리 추가
    public int saveNewSub(List<String> newSubCateNos, int index) {
        if(!newSubCateNos.isEmpty()){
            for(String  cate : newSubCateNos){
                int mainNo = Integer.parseInt(cate.split(":")[0]);
                String name = cate.split(":")[1];

                MainCategory mainCategory = MainCategory.builder()
                        .mainCateNo(mainNo)
                        .build();

                SubCategory subCategory = SubCategory.builder()
                        .subCategoryName(name)
                        .mainCategory(mainCategory)
                        .orderIndex(index)
                        .build();

                subCategoryRepository.save(subCategory);

                index++;
            }
        }

        return index;
    }

    public void saveNewSubV2(List<String> newSubCateNosV2, int index) {

        if(!newSubCateNosV2.isEmpty()){
            for(String  cate : newSubCateNosV2){
                String mainName = cate.split(":")[0];
                String subname = cate.split(":")[1];

                // 동일 이름 대비
                List<MainCategory> mainCategory = mainCategoryRepository.findByMainCategoryNameOrderByOrderIndexDesc(mainName);

                SubCategory subCategory = SubCategory.builder()
                        .subCategoryName(subname)
                        .mainCategory(mainCategory.get(0))
                        .orderIndex(index)
                        .build();

                subCategoryRepository.save(subCategory);

                index++;
            }
        }
    }

    public BannerDTO randomBanner(List<BannerDTO> banners) {

        List<BannerDTO> activeBanners = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        for(BannerDTO banner : banners){
            LocalDateTime startDateTime = LocalDateTime.of(banner.getStartDay(), banner.getStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(banner.getEndDay(), banner.getEndTime());

            if (startDateTime.isBefore(now) && endDateTime.isAfter(now)) {
                activeBanners.add(banner);
            }
        }

        if(activeBanners.isEmpty()){
            return null;
        }else{
            int size = banners.size();
            int random = (int) (Math.random() * size);
            return activeBanners.get(random);
        }
    }

    @Cacheable(value = "version")
    public VersionDTO findTopByOrderByWdateDesc() {
        log.info("버전 실행!! ");
        Version version = versionRepository.findTopByOrderByWdateDesc();
        return modelMapper.map(version, VersionDTO.class);
    }

    @Cacheable(value = "config")
    public ConfigDTO findById() {
        log.info("환경설정 호출!!");
        Optional<Config> configOpt = configRepository.findById(1);
        if(configOpt.isPresent()){
            return modelMapper.map(configOpt.get(), ConfigDTO.class);
        }
        return null;
    }

    @CacheEvict(value = "category"  , allEntries = true)
    public void deleteCategoryCache() {
        log.info("카테고리 캐시 삭제");
    }


    @Cacheable(value = "autocomplete", key = "#keyword.toLowerCase()")
    public List<String> getAutocompleteSuggestions(String keyword) {
        if (!StringUtils.hasText(keyword) || keyword.length() < 1) {
            return List.of();
        }

        String lowerKeyword = keyword.toLowerCase();

        BooleanBuilder builder = new BooleanBuilder();

        builder.or(qProduct.prodName.lower().contains(lowerKeyword))
                .or(qProduct.prodBrand.lower().contains(lowerKeyword))
                .or(qProduct.subCategory.subCategoryName.lower().contains(lowerKeyword));

        List<Product> products = productRepository.findAll(builder);

        return products.stream()
                .flatMap(product -> {
                    Stream.Builder<String> streamBuilder = Stream.builder();
                    if (product.getProdName() != null) {
                        streamBuilder.add(product.getProdName());
                    }
                    if (product.getProdBrand() != null) {
                        streamBuilder.add(product.getProdBrand());
                    }
                    if (product.getSubCategory() != null && product.getSubCategory().getSubCategoryName() != null) {
                        streamBuilder.add(product.getSubCategory().getSubCategoryName());
                    }
                    return streamBuilder.build();
                })
                .filter(suggestion -> suggestion.toLowerCase().contains(lowerKeyword))
                .distinct()
                .limit(7)
                .collect(Collectors.toList());
    }

}
