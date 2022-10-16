package nbpapi;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RestClientTest {

    @Test
    public void shouldGetUSDExchangeRate() throws IOException {

        //given
        URL url = getURL("2022-10-14");

        //when
        InputStreamReader reader = new InputStreamReader(url.openStream());
        Rate rate = new Gson().fromJson(reader, Rate.class);
        double usdRate = rate.getRates().get(0).getMid();

        //then
        assertThat(usdRate, notNullValue());
        assertThat(usdRate, greaterThanOrEqualTo(0.1));
    }


    public String getFormatDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

        return dateTimeFormatter.format(localDate);
    }

    public URL getURL(String date) throws IOException {

        String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";
        String jsonUrl = NBP_API_URL + getFormatDate(date);

        return new URL(jsonUrl);
    }
}
