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
|  | Library | mysql-connector-j - 8.3.0
querydsl-jpa - 5.1.0
commons-io - 2.14.0
thumbnailator - 0.4.14
spring-boot-starter-thymeleaf: 타임리프
spring-boot-starter-data-jpa : JPA
mybatis-spring-boot-starter:3.0.4 : mybatis
spring-boot-starter-data-redis : redis
spring-boot-starter-security : 스프링 시큐리티
spring-boot-starter-mail : 메일
thymeleaf-extras-springsecurity6 : 타임리프 문법
lombok : 롬복
org.modelmapper:modelmapper:3.2.2 : modelmapper
spring-boot-starter-oauth2-client : 소셜로그인
jasypt-spring-boot-starter:3.0.5 : 암호화 라이브러리 |
|  | DBMS | MySQL 8.0
Redis 8.0.0 |
|  | Tool | ntelliJ IDEA 2024.3.4.1
MySQL Workbench 8.0
HeidSQL 12.10
Git 2.47.1
Github
AWS |

## ERD
![image](https://github.com/user-attachments/assets/1603ea76-11ef-45f5-b52e-116cb11c5b27)


## 상품 URI 구조
src/
│── main/
│   └── java/
│       └── kr.co.lotteon/
│           ├── controller/
│           │   └── product/
│           │       ├── ProductListController.java
│			│	    │	├── ProductViewController.java
│			│		│	├── ProductCouponController.java
│			│		│   ├── ProductCartController.java
│			│		│	├── ProductOrderController.java
│			│		│	└── ProductOrderSubmitController.java
│           ├── service/
│           │   ├── product/
│           │   │   ├── ProductListService.java
│           │   │   ├── ProductViewService.java
│			│	│	├── ProductCouponService.java
│			│	│	├── ProductCartService.java
│			│	│	├── ProductOrderService.java
│			│	│	├── OrderTransactionService.java
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

## 프로젝트 결과 보고서

[프로젝트 결과 보고서](https://www.notion.so/20a4ffb4f47b8062888ed9a5e6e08602?pvs=21)
