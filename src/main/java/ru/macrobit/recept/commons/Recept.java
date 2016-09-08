package ru.macrobit.recept.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jamel.dbf.utils.DbfUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;

/**
 * Клас для общих методов
 */
public class Recept {
    public static final Logger log = LoggerFactory.getLogger(Recept.class);

    // JSON парсер
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode jsonNode = mainNode.get(fieldName);
            // if field exists and is an embedded object
            if (jsonNode != null && jsonNode.isObject()) {
                merge(jsonNode, updateNode.get(fieldName));
            } else {
                ((ObjectNode) mainNode).put(fieldName,
                        updateNode.get(fieldName));
            }
        }
        return mainNode;
    }

    public static String getFileName(InputPart inputPart) {
        String[] contentDisposition = inputPart.getHeaders().getFirst("Content-Disposition").split(";");
        for (String fileName : contentDisposition) {
            if ((fileName.trim().startsWith("filename"))) {
                String[] name = fileName.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return new String(finalFileName.getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8);
            }
        }
        return "unknown";
    }

    public static File createFile(InputStream is, String fileName) throws IOException {
        File file = new File(fileName);
        OutputStream os = new FileOutputStream(file);
        int length;
        byte[] bytes = new byte[1024];
        while ((length = is.read(bytes)) != -1) {
            os.write(bytes, 0, length);
        }
        is.close();
        os.flush();
        os.close();
        return file;
    }

    public static boolean isSnilsValid(String snils) {
        if (snils == null)
            return false;
        if (snils.length() != 11)
            return false;
        if (!snils.matches("\\d+"))
            return false;
        int sum = 0;
        int checkSum = Integer.parseInt(snils.substring(9));
        // StringBuilder s = new StringBuilder();
        int j = 9;
        for (int i = 0; i < snils.length() - 2; i++) {
            // s.append(Character.getNumericValue(snils.charAt(i))).append("x").append(j).append(" + ");
            sum += Character.getNumericValue(snils.charAt(i)) * j;
            j--;
        }
        int mod = (sum % 101);
        // LOG.debug(snils + " " + " " + mod + " " + checkSum + " " +
        // s.toString());
        return mod == checkSum || mod == 100;
    }

    public static Number getNumber(Object obj) {
        try {
            return ((Number) obj).longValue();
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    public static Date getDate(Object obj) {
        try {
            return ((Date) obj);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    public static String getString(Object obj, String encoding) {
        try {
            return new String(DbfUtils.trimLeftSpaces((byte[]) obj), encoding);
        } catch (UnsupportedEncodingException e) {
            log.error("{}", e);
        }
        return null;
    }

    public static Object castJsonValue(JsonNode jsonNode) {
        if (jsonNode.isInt()) {
            return jsonNode.longValue();
        } else if (jsonNode.isFloat()) {
            return jsonNode.floatValue();
        } else {
            return jsonNode.textValue();
        }
    }
}
