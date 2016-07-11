package ru.macrobit.recept.service;

import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.ExemptMintrudRowMapper;
import ru.macrobit.recept.pojo.Exempt;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 11.07.16.
 */
@ApplicationScoped
public class ExemptService extends AbstractDAO<Exempt> {
    private static final String tablename = "exempt";


    public ExemptService() {
        super("exempt", Exempt.class);
    }

    public Object uploadDBF(MultipartFormDataInput input) {
        List<Exempt> res = new ArrayList<>();
        input.getFormDataMap().forEach((key, val) -> val.stream().forEach(inputPart -> {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(inputStream, "/tmp/exempts.dbf"), new ExemptMintrudRowMapper());
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
}
