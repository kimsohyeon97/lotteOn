# 3차 프로젝트 - 롯데온 프로젝트 개요

| 항목 | 내용 |
| --- | --- |
| **프로젝트 주제** | **롯데e-커머스 LOTTE ON 쇼핑몰** **개발** (Front Office, Back Office, API 서버) |
| **프로젝트 기간** | 2025/04/14 ~ 2025/05/20 (24일, 192시간) |
| **배경 및 목적** | 본 프로젝트는 쇼핑몰의 기본 기능 개발을 목표로 실제 업무와 유사한 환경에서 설계 및 구현을 함을 목표로 한다.
| **배포 주소** | https://lotteon.store/ |
| **깃허브** | https://github.com/greenlotte6/lotte1-lotteon-project-team2 |

## 아키텍처 및 서비스 정보
![슬라이드1.JPG.png](https://github.com/user-attachments/assets/f4b11f38-3385-4749-b78f-3af00e3aee7f)


| 유형 | 구분 | 서비스 정보 |
| --- | --- | --- |
| SW | OS | Window10 |
|  | Browser | Chrome 137.0.7151.41 |
|  | WAS | Apache Tomcat 10.1.39 |
|  | Framework | Spring Boot 3.0.4 |
|  | Language | Java 17 |
|  | Front | HTML5, CSS3, JavaScript(ES6), jQuery 3.1 |
|  | Library | mysql-connector-j - 8.3.0<br>querydsl-jpa - 5.1.0<br>commons-io - 2.14.0<br>thumbnailator - 0.4.14<br>spring-boot-starter-thymeleaf: 타임리프<br>spring-boot-starter-data-jpa : JPA<br>mybatis-spring-boot-starter:3.0.4 : mybatis<br>spring-boot-starter-data-redis : redis<br>spring-boot-starter-security : 스프링 시큐리티<br>spring-boot-starter-mail : 메일<br>thymeleaf-extras-springsecurity6 : 타임리프 문법<br>lombok : 롬복<br>org.modelmapper:modelmapper:3.2.2 : modelmapper<br>spring-boot-starter-oauth2-client : 소셜로그인<br>jasypt-spring-boot-starter:3.0.5 : 암호화 라이브러리 |
|  | DBMS | MySQL 8.0<br>Redis 8.0.0 |
|  | Tool | ntelliJ IDEA 2024.3.4.1<br>MySQL Workbench 8.0<br>HeidSQL 12.10<br>Git 2.47.1<br>Github<br>AWS |


---

## ERD
![image](https://github.com/user-attachments/assets/1603ea76-11ef-45f5-b52e-116cb11c5b27)


## 상품 URI 구조
```
src/
├── main/
│   └── java/
│       └── kr.co.lotteon/
│           ├── controller/
│           │   └── product/
│           │       ├── ProductListController.java
│           │       ├── ProductViewController.java
│           │       ├── ProductCouponController.java
│           │       ├── ProductCartController.java
│           │       ├── ProductOrderController.java
│           │       └── ProductOrderSubmitController.java
│           ├── service/
│           │   ├── product/
│           │   │   ├── ProductListService.java
│           │   │   ├── ProductViewService.java
│           │   │   ├── ProductCouponService.java
│           │   │   ├── ProductCartService.java
│           │   │   ├── ProductOrderService.java
│           │   │   ├── OrderTransactionService.java
│           │   │   └── ProductOrderSubmitService.java
│           │   └── kakao/
│           │       └── KakaoPayService.java
│           ├── repository/
│           │   ├── impl/
│           │   │   ├── ProductRepositoryImpl.java
│           │   ├── order/
│           │   │   ├── OrderRepository.java
│           │   │   └── OrderItemRepository.java
│           │   ├── product/
│           │   │   └── ProductRepository.java
│           │   ├── coupon/
│           │   │   └── CouponIssueRepository.java
│           │   ├── cart/
│           │   │   └── CartRepository.java
│           │   ├── point/
│           │   │   └── PointRepository.java
│           │   └── user/
│           │       ├── UserRepository.java
│           │       └── UserDetailsRepository.java
│           ├── dto/
│           │   ├── order/
│           │   │   ├── OrderDTO.java
│           │   │   └── OrderItemDTO.java
│           │   ├── product/
│           │   │   ├── ProductDTO.java
│           │   │   └── ProductDetailDTO.java
│           │   ├── coupon/
│           │   │   ├── CouponDTO.java
│           │   │   └── CouponIssueDTO.java
│           │   ├── cart/
│           │   │   └── CartDTO.java
│           │   ├── point/
│           │   │   └── PointDTO.java
│           │   ├── page/
│           │   │   ├── ItemRequestDTO.java
│           │   │   ├── PageRequestDTO.java
│           │   │   └── PageResponseDTO.java
│           │   ├── kakao/
│           │   │   ├── Amount.java
│           │   │   ├── kakaoApporveResponse.java
│           │   │   └── KakaoReadyResponse.java
│           │   └── user/
│           │        ├── UserDTO.java
│           │        └── UserDetailsDTO.java
│           └── entity/
│               ├── order/
│               │   ├── Order.java
│               │   └── OrderItem.java
│               ├── product/
│               │   ├── Product.java
│               │   └── ProductDetail.java
│               ├── coupon/
│               │   ├── Coupon.java
│               │   └── CouponIssue.java
│               ├── cart/
│               │   └── Cart.java
│               ├── point/
│               │   └── Point.java
│               └── user/
│                   ├── User.java
│                   └── UserDetails.java
└── resources/
    ├── templates/
    │   └── product/
    │       ├── list/
    │       │   └── list.html
    │       ├── view/
    │       │   ├── view.html
    │       │   ├── detail.html
    │       │   ├── review.html
    │       │   ├── qna.html
    │       │   └── exchange.html
    │       └── order/
    │           ├── order.html
    │           └── order_completed.html
    └── static/
        └── js/
            └── product/
                ├── list.js
                ├── view.js
                ├── cart.js
                ├── order.js
                └── order_completed.js

```

## 프로젝트 보고서

[프로젝트 보고서](https://fish-fahrenheit-662.notion.site/3-2044ffb4f47b8077815cebd4aa81672e?source=copy_link)


# 내가 구현한 기능
## 1️⃣ 목표: 상품 목록 페이지 구현
![롯데-ON-2조](https://github.com/user-attachments/assets/1959b35c-418d-4bc4-b29c-d1e4c5d9da84)

롯데ON의 상품 목록 페이지는  
**카테고리별 베스트 상품(최근 3개월 판매량 TOP10)** 과  
**정렬 / 필터 / 토글 기능**을 최적화해 사용자 경험을 극대화했습니다.

-  **QueryDSL**로 복잡한 데이터를 제외하고 필요한 필드만 조회  
-  **Redis 캐시**로 초기 페이지 및 TOP10 인기 상품을 빠르게 응답  
-  **AJAX 비동기 처리**로 정렬/필터/페이지 전환을 새로고침 없이 자연스럽게 구현  
-  **URL 파라미터 관리**로 사용자가 선택한 뷰 상태를 일관되게 유지

---

###  사용 기술

| 기술 | 설명 |
|------|------|
| **QueryDSL** | 필요한 필드만 조회하여 **불필요한 데이터 전송 최소화** |
| **Redis 캐시** | 카테고리별 TOP10 상품을 캐싱해 **첫 페이지 응답 속도 향상** |
| **AJAX** | 정렬, 필터, 토글, 페이지 이동을 **비동기 처리로 자연스럽게 구현** |
| **URL 파라미터 관리** | 사용자가 선택한 뷰 상태(리스트/그리드)를 **다음 페이지에서도 유지** |

---

### ✅ 성과

- 정렬, 토글, 페이지 이동을 새로고침 없이 처리해 **몰입도 높은 UX 제공**
- Redis 캐시 + QueryDSL 최소 조회로 **초기 로딩 속도 향상**
- **선택한 뷰 상태와 정렬 방식이 유지**되어 **일관된 사용자 경험** 보장
- DB 조회 횟수 감소 및 통신 데이터 절감으로 **서버 부하 최적화**

