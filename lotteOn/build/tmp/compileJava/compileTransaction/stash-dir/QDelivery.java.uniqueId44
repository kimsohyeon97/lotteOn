package kr.co.lotteon.entity.delivery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = -1200649685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final StringPath deliveryCompany = createString("deliveryCompany");

    public final DateTimePath<java.time.LocalDateTime> deliveryDate = createDateTime("deliveryDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> dno = createNumber("dno", Long.class);

    public final kr.co.lotteon.entity.order.QOrder order;

    public final StringPath state = createString("state");

    public QDelivery(String variable) {
        this(Delivery.class, forVariable(variable), INITS);
    }

    public QDelivery(Path<? extends Delivery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelivery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelivery(PathMetadata metadata, PathInits inits) {
        this(Delivery.class, metadata, inits);
    }

    public QDelivery(Class<? extends Delivery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new kr.co.lotteon.entity.order.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

