package kr.co.lotteon.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.lotteon.dto.page.PageRequestDTO;
import kr.co.lotteon.entity.delivery.QDelivery;
import kr.co.lotteon.entity.order.QOrder;
import kr.co.lotteon.entity.order.QOrderItem;
import kr.co.lotteon.entity.product.QProduct;
import kr.co.lotteon.entity.product.QProductImage;
import kr.co.lotteon.entity.seller.QSeller;
import kr.co.lotteon.entity.user.QUser;
import kr.co.lotteon.repository.custom.DeliveryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QOrder qOrder = QOrder.order;
    private final QDelivery qDelivery = QDelivery.delivery;
    private final QOrderItem qOrderItem = QOrderItem.orderItem;
    private final QProduct qProduct = QProduct.product;
    private final QSeller qSeller = QSeller.seller;


    @Override
    public Page<Tuple> selectAllDelivery(PageRequestDTO pageRequestDTO, Pageable pageable) {

        BooleanExpression expression = null;

        String keyword = pageRequestDTO.getKeyword();
        String type = pageRequestDTO.getSearchType();

        String role = pageRequestDTO.getRole();
        String uid = pageRequestDTO.getUid();

        if(role.contains("SELLER")){
            expression = qSeller.user.uid.eq(uid);
        }

        if (type == null || type.equals("")) {
            List<Tuple> tupleList = queryFactory
                    .select(qOrder, qDelivery, qOrderItem)
                    .from(qOrderItem)
                    .join(qOrder).on(qOrderItem.order.orderNo.eq(qOrder.orderNo))
                    .join(qDelivery).on(qDelivery.order.orderNo.eq(qOrder.orderNo))
                    .join(qOrderItem.product, qProduct)
                    .join(qProduct.seller, qSeller)
                    .where(expression)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qDelivery.deliveryDate.desc())
                    .fetch();

            long total = queryFactory
                    .select(qOrder.count())
                    .from(qOrderItem)
                    .join(qOrder).on(qOrderItem.order.orderNo.eq(qOrder.orderNo))
                    .join(qDelivery).on(qDelivery.order.orderNo.eq(qOrder.orderNo))
                    .join(qOrderItem.product, qProduct)
                    .join(qProduct.seller, qSeller)
                    .where(expression)
                    .fetchOne();

            log.info("total: {}", total);
            log.info("tupleList: {}", tupleList);

            return new PageImpl<>(tupleList, pageable, total);

        } else {

            BooleanExpression booleanExpression = null;

            switch (type) {
                case "송장번호": {
                    booleanExpression = qDelivery.dno.like("%" + keyword + "%");
                    break;
                }
                case "주문번호": {
                    booleanExpression = qOrder.orderNo.like("%" + keyword + "%");
                    break;
                }
                case "수령인": {
                    booleanExpression = qOrder.orderReceiver.like("%" + keyword + "%");
                    break;
                }
                default: {
                    break;
                }
            }

            if(role.contains("SELLER")){
                booleanExpression = booleanExpression.and(qSeller.user.uid.eq(uid));
            }

            List<Tuple> tupleList = queryFactory
                    .select(qOrder, qDelivery, qOrderItem)
                    .from(qOrderItem)
                    .join(qOrder).on(qOrderItem.order.orderNo.eq(qOrder.orderNo))
                    .join(qDelivery).on(qDelivery.order.orderNo.eq(qOrder.orderNo))
                    .join(qOrderItem.product, qProduct)
                    .join(qProduct.seller, qSeller)
                    .where(booleanExpression)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(qDelivery.deliveryDate.desc())
                    .fetch();

            long total = queryFactory
                    .select(qOrder.count())
                    .from(qOrderItem)
                    .join(qOrder).on(qOrderItem.order.orderNo.eq(qOrder.orderNo))
                    .join(qDelivery).on(qDelivery.order.orderNo.eq(qOrder.orderNo))
                    .join(qOrderItem.product, qProduct)
                    .join(qProduct.seller, qSeller)
                    .where(booleanExpression)
                    .fetchOne();

            log.info("total: {}", total);
            log.info("tupleList: {}", tupleList);

            return new PageImpl<>(tupleList, pageable, total);
        }
    }
}
