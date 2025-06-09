package kr.co.lotteon.repository.custom;

import com.querydsl.core.Tuple;
import kr.co.lotteon.dto.page.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryRepositoryCustom {
    Page<Tuple> selectAllDelivery(PageRequestDTO pageRequestDTO, Pageable pageable);
}
