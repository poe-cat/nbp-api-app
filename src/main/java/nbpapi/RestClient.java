package nbpapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
    private static String jsonUrl;


    public double getUSDExchangeRate(String date) throws IOException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        String formatDate = dateTimeFormatter.format(localDate);

        jsonUrl = NBP_API_URL + formatDate;
        URL url = new URL(jsonUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        Rate rate = new Gson().fromJson(reader, Rate.class);

        return rate.getRates().get(0).getMid();
    }


    public double getUSDExchangeRate(String date, double price) throws IOException {

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
}
