package rahadian.prast.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author ian
 * create 03/01/25 14.26
 */
public class ArrayToMapTest {


    @Test
    void arrayToMap() {

        ObjectMapper objectMapper = new ObjectMapper();

        String[] array = new String[] { "Soal Jawaban ? : Jawaban 2", "Seberapa puaskah anda ? : 10", "Bagaimana birokrasi dipemeritahan yang sekarang? : Sangat Puas" };

        for (String s : array) {
            Map<String, String> map = new HashMap<>();
            String[] keyValue = s.split(":");
            map.put(keyValue[0], keyValue[1]);
            String result;
            try {
                result = objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }


            System.out.println(result);
        }


        for (String s : array) {
            String[] keyValue = s.split(":");
            String result;
            try {
                result = objectMapper.writeValueAsString(keyValue);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println(result);
        }

    }
}
