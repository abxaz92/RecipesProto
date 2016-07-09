package ru.macrobit.recept.service;

import net.iryndin.jdbf.reader.DbfReader;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.DBFUploader;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.LpuRowMapper;
import ru.macrobit.recept.pojo.Lpu;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 7/8/16
 */
@ApplicationScoped
public class LpuService extends AbstractDAO<Lpu> {
    private static final Logger log = LoggerFactory.getLogger(LpuService.class);

    public LpuService() {
        super("lpu", Lpu.class);
    }

    public Object uploadDBF(MultipartFormDataInput input) {
        List<Lpu> res = new ArrayList<>();
        input.getFormDataMap().forEach((key, val) -> val.stream().forEach(inputPart -> {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
                List<Lpu> lpus = DbfProcessor.loadData(Recept.createFile(inputStream, "/tmp/lpu.dbf"), new LpuRowMapper());
                res.addAll(lpus);
                try {
                    insert(lpus);
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
