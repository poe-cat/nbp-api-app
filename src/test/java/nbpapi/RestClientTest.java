package nbpapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class RestClientTest {

    @Test
    void shouldThrowExceptionWithInvalidDate() throws IOException {
        RestClient restClient = new RestClient();

        double result = restClient.getUSDExchangeRate("2023-01-04", 10);
        assertEquals(43.998, result, 0.01);

        assertThrows(IOException.class, () -> restClient.getUSDExchangeRate("2023-01-01", 10));
    }
}