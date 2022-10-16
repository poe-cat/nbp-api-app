package nbpapi;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class InventoryTest {


    private List<Product> prepareProductsData() {

        Product product1 = new Product("komputer 1", "2022-01-03", 5.0, 20.0);
        Product product2 = new Product("komputer 2", "2022-01-10", 5.0, 20.0);

        return Arrays.asList(product1, product2);
    }
}
