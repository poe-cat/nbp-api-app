package nbpapi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InventoryTest {

    @Test
    void shouldAddProductToInventory() {

        //given
        Inventory inventory = new Inventory();

        //when
        inventory.addProduct("komputer1", "2022-10-14", 5.0, 20.0);
        inventory.addProduct("komputer2", "2022-10-12", 5.0, 20.0);
        List<Product> products = inventory.selectProducts();

        //then
        assertThat(products.size(), notNullValue());
        assertThat(products.stream().toList(), hasSize(greaterThanOrEqualTo(3)));

    }
}
