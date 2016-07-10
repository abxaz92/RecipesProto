package ru.macrobit.recept.service;

import net.iryndin.jdbf.reader.DbfReader;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.DBFUploader;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.DoctorRowMapper;
import ru.macrobit.recept.pojo.Doctor;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 7/10/16.
 */
@ApplicationScoped
public class DoctorService extends AbstractDAO<Doctor> {
    public DoctorService() {
        super("doctor", Doctor.class);
    }

    public Object uploadDBF(MultipartFormDataInput input) {
        List<Doctor> res = new ArrayList<>();
        input.getFormDataMap().forEach((key, val) -> val.stream().forEach(inputPart -> {
            try (InputStream inputStream = inputPart.getBody(InputStream.class, null)) {
//                List<Doctor> doctors = DBFUploader.precessData(new DbfReader(inputStream), Charset.forName("windows-1251"), new DoctorRowMapper());
                List<Doctor> doctors = DbfProcessor.loadData(Recept.createFile(inputStream, "/tmp/lpu.dbf"), new DoctorRowMapper());
                res.addAll(doctors);
                try {
                    insert(doctors);
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
