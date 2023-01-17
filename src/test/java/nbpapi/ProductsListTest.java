package nbpapi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductsListTest {

    private ProductsList productsList;
    private Inventory inventory;
    private File xmlFile;

    @BeforeEach
    public void setUp() {
        productsList = new ProductsList();
        inventory = new Inventory();
        xmlFile = new File("faktura.xml");
    }

    @Test
    public void shouldCreateXMLFile() throws JAXBException {
        productsList.setProductsListForXML();
        productsList.createXMLFile();
        assertTrue(xmlFile.exists());
    }
}

