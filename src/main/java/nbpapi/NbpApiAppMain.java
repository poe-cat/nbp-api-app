package nbpapi;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NbpApiAppMain {

    private static final Inventory inventory = new Inventory();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductsList PRODUCTS_LIST = new ProductsList();

    public static void main(String[] args) throws IOException, JAXBException {

        RestClient restClient = new RestClient();

        String answer;
        char choice, ignore;

        for(;;) {
            do {
                System.out.println("Co chcesz zrobić?\n");
                System.out.println("Wyświetl wszystkie produkty - wciśnij 1");
                System.out.println("Wyszukaj produkt po nazwie - wciśnij 2");
                System.out.println("Dodaj nowy produkt - wciśnij 3");
                System.out.println("Zakończ program - wciśnij 4");

                choice = (char) System.in.read();

                do {
                    ignore = (char) System.in.read();
                } while (ignore != '\n');
            } while(choice < '1' | choice > '3' & choice != '4');

            if(choice == '4') break;

            System.out.println("\n");

            switch(choice) {
                case '1':
                    listAllProducts();
                    break;
                case '2':
                    searchProductByName();
                case '3':
                    do {
                        System.out.println("Podaj nazwę produktu: ");
                        String nazwa = scanner.nextLine();
                        System.out.println("Podaj datę księgowania (format: yyyy-mm-dd):");
                        String data = scanner.nextLine();

                        if (!data.matches("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$")) {
                            System.out.println("Nieprawidłowy format daty. Wprowadź ponownie: ");
                            data = scanner.nextLine();
                        }

                        System.out.println("Podaj cenę w USD: ");
                        double cenaUSD = scanner.nextDouble();
                        scanner.nextLine();

                        // USD to PLN price
                        double cenaPLN = restClient.calculateUsdToPLN(data) * cenaUSD;


                        System.out.println("\nDodałeś produkt.\nNazwa: " + nazwa + ", data księgowania: "
                                + data + ", cena w USD: " + cenaUSD + ", cena w PLN: " + cenaPLN);

                        // Saving to database
                        inventory.addProduct(nazwa, data, cenaUSD, cenaPLN);

                        System.out.println("Czy chcesz dodać kolejny produkt? Wpisz 'tak' żeby kontynuwoać.");
                        answer = scanner.nextLine();

                    }
                    while(answer.equalsIgnoreCase("tak"));

                    // Saving to XML
                    saveDataToXML();
            }
        }
        inventory.closeConnection();
    }

    public static void listAllProducts() {

        List<Product> products = inventory.selectProducts();
        System.out.println("\nLista wszystkich produktów:\n");
        for (Product p : products)
            System.out.println(p);
    }

    public static void searchProductByName() {
        System.out.println("Wyszukaj po nazwie: ");
        String search = scanner.nextLine();
        List<Product> productsListBySearch = inventory.findProductsByName(search);
        for(Product p: productsListBySearch)
            System.out.println(p);
    }

    public static void saveDataToXML() throws JAXBException {
        PRODUCTS_LIST.setProductsListForXML();
        PRODUCTS_LIST.createXMLFile();
    }
}
