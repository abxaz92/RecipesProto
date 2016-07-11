package ru.macrobit.recept.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.Iterator;

/**
 * Клас для общих методов
 */
public class Recept {
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
}
