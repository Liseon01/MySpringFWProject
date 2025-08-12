package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
class OrderSpringTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService service;

    @Test
    void testShoppingCart() {
        assertNotNull(cart, "shoppingCart가 주입되어야 함");
        assertNotNull(cart.getProducts(), "products 리스트가 초기화되어야 함");
        assertEquals(2, cart.getProducts().size(), "상품 개수 검증");

        assertEquals("노트북", cart.getProducts().get(0).getName());
        assertEquals("스마트폰", cart.getProducts().get(1).getName());
    }

    @Test
    void testOrderService() {
        assertNotNull(service, "orderService가 주입되어야 함");
        assertNotNull(service.getShoppingCart(), "orderService에 shoppingCart가 주입되어야 함");

        double total = service.calculateOrderTotal();
        assertEquals(950000.0, total, 0.0001, "총 주문 금액 검증 (150000 + 800000)");
    }
}
