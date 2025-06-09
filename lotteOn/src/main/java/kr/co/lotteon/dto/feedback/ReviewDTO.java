package kr.co.lotteon.dto.feedback;

import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.dto.user.UserDTO;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private int rno;              // 리뷰 번호

    private UserDTO writer;
    private ProductDTO product;

    private String uid;           // 작성자 아이디 (User.uid)
    private int prodNo;           // 상품 번호 (Product.prodNo)

    private String content;       // 리뷰 내용
    private LocalDateTime wdate;  // 작성일
    private BigDecimal rating;    // 평점

    private String sNameImage1;
    private String oNameImage1;
    private String sNameImage2;
    private String oNameImage2;

    // 추가 필드
    private String company;

    // 선택적으로 사용자명, 상품명, 상품 이미지 같은 것도 추가 가능
    private String writerName;
    private String productName;

}