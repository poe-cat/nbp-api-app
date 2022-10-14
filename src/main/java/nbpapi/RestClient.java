package nbpapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RestClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
    private static String jsonUrl;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę produktu: ");
        String nazwa = scanner.nextLine();
        System.out.println("Podaj datę księgowania: ");
        String data = scanner.nextLine();
        System.out.println("Podaj cenę w USD: ");
        double cenaUSD = scanner.nextDouble();
        scanner.nextLine();

        double cenaPLN = calculateUsdToPLN(data) * cenaUSD;

        System.out.println("Dodałeś produkt.\nNazwa: " + nazwa + ", data księgowania: "
                + data + ", cena w USD: " + cenaUSD + ", cena w PLN: " + cenaPLN);



        Inventory inventory = new Inventory();
        inventory.addProduct(nazwa, data, cenaUSD, cenaPLN);

        List<Product> products = inventory.selectProducts();

        System.out.println("\nLista wszystkich produktów: ");
        for(Product p: products)
            System.out.println(p);

        inventory.closeConnection();
    }

    static double calculateUsdToPLN(String date, double price) throws IOException {

        double exchangedRate;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        String formatDate = dateTimeFormatter.format(localDate);

        jsonUrl = NBP_API_URL + formatDate;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        Rate rate = new Gson().fromJson(reader, Rate.class);
        exchangedRate = rate.getRates().get(0).getMid() * price;

        return exchangedRate;
    }

    static double calculateUsdToPLN(String date) throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        String formatDate = dateTimeFormatter.format(localDate);

        jsonUrl = NBP_API_URL + formatDate;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        Rate rate = new Gson().fromJson(reader, Rate.class);
        double usdRate = rate.getRates().get(0).getMid();

        return usdRate;
    }

}
