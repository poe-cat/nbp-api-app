package nbpapi;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NbpApiAppMain {

    public static void main(String[] args) throws IOException {

        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        RestClient restClient = new RestClient();
        String answer;

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

            double cenaPLN = restClient.calculateUsdToPLN(data) * cenaUSD;


            System.out.println("Dodałeś produkt.\nNazwa: " + nazwa + ", data księgowania: "
                    + data + ", cena w USD: " + cenaUSD + ", cena w PLN: " + cenaPLN);

            inventory.addProduct(nazwa, data, cenaUSD, cenaPLN);

            System.out.println("Czy chcesz dodać kolejny produkt? Wpisz 'tak' żeby kontynuwoać.");
            answer = scanner.nextLine();

        }
        while(answer.equalsIgnoreCase("tak"));

        System.out.println("Aby wyświetlić wszystkie produkty, wciśnij 'P' ");

        List<Product> products = inventory.selectProducts();

        System.out.println("\nLista wszystkich produktów: ");
        for(Product p: products)
            System.out.println(p);

        inventory.closeConnection();
    }
}
