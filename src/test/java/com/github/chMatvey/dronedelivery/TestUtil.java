package com.github.chMatvey.dronedelivery;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.util.StreamUtils.copyToByteArray;
import static org.springframework.util.StreamUtils.copyToString;

@UtilityClass
public class TestUtil {
    @SneakyThrows
    public static byte[] readFileAsBytes(String path) {
        return copyToByteArray(new ClassPathResource(path).getInputStream());
    }

    @SneakyThrows
    public static String readFileAsString(String path) {
        return copyToString(new ClassPathResource(path).getInputStream(), UTF_8);
    }
}
