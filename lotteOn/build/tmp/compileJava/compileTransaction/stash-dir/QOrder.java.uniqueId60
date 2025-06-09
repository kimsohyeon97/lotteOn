package kr.co.lotteon.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -687971413L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final NumberPath<Integer> couponDiscount = createNumber("couponDiscount", Integer.class);

    public final StringPath orderAddr = createString("orderAddr");

    public final StringPath orderContent = createString("orderContent");

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final ListPath<OrderItem, QOrderItem> orderItems = this.<OrderItem, QOrderItem>createList("orderItems", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final NumberPath<Integer> orderNo = createNumber("orderNo", Integer.class);

    public final StringPath orderReceiver = createString("orderReceiver");

    public final StringPath orderSender = createString("orderSender");

    public final NumberPath<Integer> orderTotalPrice = createNumber("orderTotalPrice", Integer.class);

    public final NumberPath<Integer> originalTotalPrice = createNumber("originalTotalPrice", Integer.class);

    public final StringPath payment = createString("payment");

    public final StringPath paymentContent = createString("paymentContent");

    public final NumberPath<Integer> pointDiscount = createNumber("pointDiscount", Integer.class);

    public final StringPath receiverHp = createString("receiverHp");

    public final StringPath receiverZip = createString("receiverZip");

    public final StringPath senderHp = createString("senderHp");

    public final NumberPath<Integer> shippingFee = createNumber("shippingFee", Integer.class);

    public final NumberPath<Integer> totalDiscount = createNumber("totalDiscount", Integer.class);

    public final NumberPath<Integer> totalPoint = createNumber("totalPoint", Integer.class);

    public final NumberPath<Integer> totalQuantity = createNumber("totalQuantity", Integer.class);

    public final kr.co.lotteon.entity.user.QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.lotteon.entity.user.QUser(forProperty("user")) : null;
    }

}

