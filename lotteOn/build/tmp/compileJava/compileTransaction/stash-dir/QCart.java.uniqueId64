package kr.co.lotteon.entity.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -380219477L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final NumberPath<Integer> cartNo = createNumber("cartNo", Integer.class);

    public final NumberPath<Integer> cartProdCount = createNumber("cartProdCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> cartProdDate = createDateTime("cartProdDate", java.time.LocalDateTime.class);

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

    public final kr.co.lotteon.entity.product.QProduct product;

    public final kr.co.lotteon.entity.user.QUser user;

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new kr.co.lotteon.entity.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.lotteon.entity.user.QUser(forProperty("user")) : null;
    }

}

