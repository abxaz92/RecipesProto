package ru.macrobit.recept.service;

import org.hibernate.Session;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.controller.LpuController;
import ru.macrobit.recept.dbfmappers.DrugRowMapper;
import ru.macrobit.recept.dbfmappers.drug.*;
import ru.macrobit.recept.pojo.drug.*;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by [david] on 12.07.16.
 */
@ApplicationScoped
public class DrugService extends AbstractDAO<Drug> {
    private static final Logger log = LoggerFactory.getLogger(LpuController.class);

    public DrugService() {
        super("drug", Drug.class);
    }

    public Object uploadDBF(MultipartFormDataInput input) throws IOException {
        Map<String, InputPart> files = new HashMap<>();
        input.getFormDataMap().values().forEach(inputParts -> {
            inputParts.forEach(inputPart -> {
                files.put(Recept.getFileName(inputPart), inputPart);

            });

        });
        log.info("{}", files.keySet());

        List<Drug> drugs = DbfProcessor.loadData(Recept.createFile(files.get("sp_tov.dbf").getBody(InputStream.class, null), "/tmp/Sp_frgp"), new DrugRowMapper());
        List<TorgName> tnames = DbfProcessor.loadData(Recept.createFile(files.get("Sp_trn.DBF").getBody(InputStream.class, null), "/tmp/Sp_frgp"), new TorgNameRowMapper());
        List<MnnName> inames = DbfProcessor.loadData(Recept.createFile(files.get("Sp_mnn.DBF").getBody(InputStream.class, null), "/tmp/Sp_mnn.DBF"), new InameRowMapper());
        List<Form> forms = DbfProcessor.loadData(Recept.createFile(files.get("Sp_lf.dbf").getBody(InputStream.class, null), "/tmp/Sp_lf.dbf"), new FormsRowMapper());
        List<Dose> doses = DbfProcessor.loadData(Recept.createFile(files.get("Sp_doza.DBF").getBody(InputStream.class, null), "/tmp/Sp_doza.DBF"), new DoseRowMapper());
        List<Volume> volumes = DbfProcessor.loadData(Recept.createFile(files.get("Sp_edvlf.DBF").getBody(InputStream.class, null), "/tmp/Sp_edvlf.DBF"), new VolumeRowMapper());
        List<Weight> weights = DbfProcessor.loadData(Recept.createFile(files.get("Sp_edmlf.DBF").getBody(InputStream.class, null), "/tmp/Sp_edmlf.DBF"), new WeightRowMapper());
        List<FarmGroup> farmGroups = DbfProcessor.loadData(Recept.createFile(files.get("Sp_frgp.DBF").getBody(InputStream.class, null), "/tmp/Sp_frgp"), new FarmGroupRowMapper());

        try (Session session = em.unwrap(Session.class)) {
            utx.begin();
            tnames.forEach(session::save);
            inames.forEach(session::save);
            forms.forEach(session::save);
            doses.forEach(session::save);
            volumes.forEach(session::save);
            weights.forEach(session::save);
            farmGroups.forEach(session::save);
            session.flush();
            drugs.forEach(session::save);
            utx.commit();
        } catch (Exception e) {

        }

        List<Drug> res = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            res.add(drugs.get(i));
        }
        return res;
    }

}
