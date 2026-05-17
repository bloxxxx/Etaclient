package io.github.bloxxxx.etaclient.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class GzipUtil {
    private GzipUtil() {}

    public static String compress(String data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
            gzip.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return null;
        }

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static String decompress(String compressedBase64) {
        byte[] compressed = Base64.getDecoder().decode(compressedBase64);

        try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressed));
             InputStreamReader reader = new InputStreamReader(gzip, StandardCharsets.UTF_8);
             BufferedReader buffered = new BufferedReader(reader)) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = buffered.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
