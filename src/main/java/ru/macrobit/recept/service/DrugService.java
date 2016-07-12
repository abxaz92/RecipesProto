package ru.macrobit.recept.service;

import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.controller.LpuController;
import ru.macrobit.recept.dbfmappers.drug.FarmGroupRowMapper;
import ru.macrobit.recept.pojo.drug.Drug;
import ru.macrobit.recept.pojo.drug.FarmGroup;

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
//        DbfReader drugsReader = new DbfReader(files.get("sp_tov.dbf").getBody(InputStream.class, null));
//        DbfReader tnamesReader = new DbfReader(files.get("Sp_trn.DBF").getBody(InputStream.class, null));
//        DbfReader inamesReader = new DbfReader(files.get("Sp_mnn.DBF").getBody(InputStream.class, null));
//        DbfReader formReader = new DbfReader(files.get("Sp_lf.dbf").getBody(InputStream.class, null));
//        DbfReader doseReader = new DbfReader(files.get("Sp_doza.DBF").getBody(InputStream.class, null));
//        DbfReader volumeReader = new DbfReader(files.get("Sp_edvlf.DBF").getBody(InputStream.class, null));
//        DbfReader weightReader = new DbfReader(files.get("Sp_edmlf.DBF").getBody(InputStream.class, null));
//        DbfReader farmGroupReader = new DbfReader(files.get("Sp_frgp.DBF").getBody(InputStream.class, null));

//        List<Drug> drugs = DBFUploader.precessData(drugsReader, Charset.forName("IBM866"), new DrugRowMapper());
//
//        List<TorgName> tnamesMap = getTnamesMap(tnamesReader);
//        List<MnnName> inamesMap = getInamesMap(inamesReader);
//        List<Form> formMap = getFormsMap(formReader);
//        List<Dose> doseMap = getDoseMap(doseReader);
//        List<Volume> volumeMap = getVolumeMap(volumeReader);
//        List<Weight> weightMap = getWeightMap(weightReader);
        List<FarmGroup> farmGroupMap = DbfProcessor.loadData(Recept.createFile(files.get("Sp_frgp.DBF").getBody(InputStream.class, null), "/tmp/Sp_frgp"), new FarmGroupRowMapper());
        log.info("tnames {}", Recept.MAPPER.writeValueAsString(farmGroupMap));
//        insert(drugs);

        List<Drug> res = new ArrayList<>();
//        for (int i = 0; i < 60; i++) {
//            res.add(drugs.get(i));
//        }
        return res;
    }

}
