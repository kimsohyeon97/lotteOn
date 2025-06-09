package kr.co.lotteon.entity.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItem is a Querydsl query type for OrderItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItem extends EntityPathBase<OrderItem> {

    private static final long serialVersionUID = -1532918434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItem orderItem = new QOrderItem("orderItem");

    public final StringPath category = createString("category");

    public final NumberPath<Integer> itemCount = createNumber("itemCount", Integer.class);

    public final NumberPath<Integer> itemDiscount = createNumber("itemDiscount", Integer.class);

    public final NumberPath<Long> itemNo = createNumber("itemNo", Long.class);

    public final NumberPath<Integer> itemPoint = createNumber("itemPoint", Integer.class);

    public final NumberPath<Integer> itemPrice = createNumber("itemPrice", Integer.class);

    public final StringPath opt1 = createString("opt1");

    public final StringPath opt1Cont = createString("opt1Cont");

    public final StringPath opt2 = createString("opt2");

    public final StringPath opt2Cont = createString("opt2Cont");

    public final StringPath opt3 = createString("opt3");

    public final StringPath opt3Cont = createString("opt3Cont");

    public final StringPath opt4 = createString("opt4");

    public final StringPath opt4Cont = createString("opt4Cont");

    public final StringPath opt5 = createString("opt5");

    public final StringPath opt5Cont = createString("opt5Cont");

    public final StringPath opt6 = createString("opt6");

    public final StringPath opt6Cont = createString("opt6Cont");

    public final QOrder order;

    public final StringPath orderStatus = createString("orderStatus");

    public final kr.co.lotteon.entity.product.QProduct product;

    public QOrderItem(String variable) {
        this(OrderItem.class, forVariable(variable), INITS);
    }

    public QOrderItem(Path<? extends OrderItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItem(PathMetadata metadata, PathInits inits) {
        this(OrderItem.class, metadata, inits);
    }

    public QOrderItem(Class<? extends OrderItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.product = inits.isInitialized("product") ? new kr.co.lotteon.entity.product.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

