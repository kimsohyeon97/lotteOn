# 3차 프로젝트 - 롯데온 프로젝트 개요

| 항목 | 내용 |
| --- | --- |
| **프로젝트 주제** | **롯데e-커머스 LOTTE ON 쇼핑몰** **개발** (Front Office, Back Office, API 서버) |
| **프로젝트 기간** | 2025/04/14 ~ 2025/05/20 (24일, 192시간) |
| **배경 및 목적** | [배경]
본 프로젝트는 쇼핑몰의 기본 기능 개발을 목표로 실제 업무와 유사한 환경에서 설계 및 구현을 함을 목표로 한다.
 
추가로 SNS 기능, 전시(보여지는 화면)관리 기능을 추가하여 SNS을 통해서는 사용자와 판매자 사이의 소통의 장과 홍보효과까지 누리게 하며, 전시관리를 통하여서는 개발자 및 이용자들에게 편의성을 부여한다.
 
[목표]
1. 업무와 유사한 환경인 MSA 환경을 기반으로 설계 및 구현함을 목표로 한다.
2. Token을 활용하여 회원을 구분하고, 이를 통해 구분된 사용자, 관리자는 부여된 권한에 따른 화면 및 기능을 볼 수 있어야 한다.
3. 전시 모듈화를 통하여 상품 전시와 편성에 소요되는 직접적인 개발, 운영 시간을 효율화 시킨다. |
| **주요기능** | 1. BO(Back-Office)                                                                                                                **사용자 :** 회원가입, 로그인, 아이디찾기/비밀번호 찾기/ 비밀번호 재설정 등 사용자 프론트 엔드 화면 구현에 필요한 API 구축                                                                                                                 **판매자 :** 상품관리(상품등록, 상품수정), 배송 현황 관리, 리뷰관리, QnA관리(QnA답변, QnA조회) 환불 내역 관리(취소 상세 내역, 환불 상세 내역) 등 판매자 프론트 엔드 화면 구현에 필요한 API 구축                                                                                                                                                       2. FO(Front-Office)                                                                                                                **사용자 :** 메인페이지, 회원가입, 로그인, 로그아웃, 아이디찾기/비밀번호 찾기/ 비밀번호 재설정, 주문하기, 찜 목록, 상품리스트, 상품 상세보기, 상품 리뷰, 문의 작성, 장바구니, 마이페이지 – 회원정보수정, 내글 관리(작성 가능한 리뷰, 리뷰목록, 문의 목록), 주문관리(주문내역, 취소내역), 주문 상세보기 등 사용자 화면 개발                                                                                                                       **판매자 :** 메인 DASHBOARD 화면, 판매 지표 화면, 상품 등록, 상품 수정, 상품 리스트, 리뷰 관리, QnA 관리 등 판매자 화면 개발                                                                                                                                                                                                                                                     3. API 서버 관리                                                                                                                   BO, FO를 통신하는 API 서버 구현 |
| **배포 주소** | https://lotteon.store/ |
| **깃허브** | https://github.com/greenlotte6/lotte1-lotteon-project-team2 |

## 아키텍처 및 서비스 정보

> 애플리케이션 아키텍처 구조 작성, 서비스 정보 작성
> 

![슬라이드1.JPG.png](attachment:2b33fd8f-28e4-48c5-9368-4a533ad2d933:슬라이드1.JPG.png)

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

> ERD 설계도
> 

![image.png](attachment:f010d540-d339-4b8a-95bd-57ab134a4206:image.png)

## 상품 URI 구조

```
src/
│── main/
│   └── java/
│       └── kr.co.lotteon/
│           ├── controller/
│           │   └── product/
│           │       ├── ProductListController.java
│						│				├── ProductViewController.java
│						│				├── ProductCouponController.java
│						│		    ├── ProductCartController.java
│						│				├── ProductOrderController.java
│						│			  └── ProductOrderSubmitController.java
│           ├── service/
│           │   ├── product/
│           │   │   ├── ProductListService.java
│           │   │   ├── ProductViewService.java
│						│		│	  ├── ProductCouponService.java
│						│		│  	├── ProductCartService.java
│						│		│  	├── ProductOrderService.java
│						│		│  	├── OrderTransactionService.java
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
