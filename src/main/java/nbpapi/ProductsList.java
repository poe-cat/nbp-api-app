package nbpapi;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;

public class ProductsList {

    private Products products = new Products();
    private Inventory inventory = new Inventory();

    public void setProductsListForXML() {

        products.setProducts(inventory.selectProducts());
    }

    public void createXMLFile() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(products, new File("faktura.xml"));
    }
}