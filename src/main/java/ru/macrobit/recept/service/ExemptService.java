package ru.macrobit.recept.service;

import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.ExemptMTRowMapper;
import ru.macrobit.recept.dbfmappers.ExemptMzRowMapper;
import ru.macrobit.recept.dbfmappers.drug.ExemptCategoryRowMapper;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.entities.Category;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by david on 11.07.16.
 */
@ApplicationScoped
public class ExemptService extends AbstractDAO<Exempt> {
    private static final String tablename = "exempt";


    public ExemptService() {
        super("exempt", Exempt.class);
    }

    public Object uploadMintrudDBF(MultipartFormDataInput input) {
        List<Exempt> res = new ArrayList<>();
        input.getFormDataMap().forEach((key, val) -> val.stream().forEach(inputPart -> {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(inputStream, "/tmp/exempts.dbf"), new ExemptMTRowMapper());
                for (int i = 0; i < 100; i++) {
                    res.add(exempts.get(i));
                }
                try {
                    insert(exempts);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        return res;
    }

    public Object uploadMZDBF(MultipartFormDataInput input) throws IOException {
        Map<String, InputPart> files = new HashMap<>();
        input.getFormDataMap().values().forEach(inputParts -> {
            inputParts.forEach(inputPart -> {
                files.put(Recept.getFileName(inputPart), inputPart);

            });

        });

        List<Category> res = new ArrayList<>();
        List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(files.get("REG.DBF").getBody(InputStream.class, null), "/tmp/exemptsMt.dbf"), new ExemptMzRowMapper());
        Map<String, Category> categoriesMap = DbfProcessor
                .loadData(Recept.createFile(files.get("LREG.DBF")
                        .getBody(InputStream.class, null), "/tmp/exemptCats.dbf"), new ExemptCategoryRowMapper())
                .stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));
/*                try {
                    insert(exempts);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
        return null;
    }
}
