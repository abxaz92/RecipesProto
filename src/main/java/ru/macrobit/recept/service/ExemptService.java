package ru.macrobit.recept.service;

import org.hibernate.Session;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.*;
import ru.macrobit.recept.pojo.Disease;
import ru.macrobit.recept.pojo.Exempt;
import ru.macrobit.recept.pojo.ExemptCategory;
import ru.macrobit.recept.pojo.entities.Category;
import ru.macrobit.recept.pojo.entities.FederalInfo;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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
                try (Session session = em.unwrap(Session.class)) {
                    exempts.stream().forEach(session::saveOrUpdate);
                }
                insert(exempts);
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

        List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(files.get("REG.DBF").getBody(InputStream.class, null), "/tmp/exemptsMt.dbf"), new ExemptMzRowMapper());
        List<Category> categories = DbfProcessor
                .loadData(Recept.createFile(files.get("LREG.DBF")
                        .getBody(InputStream.class, null), "/tmp/exemptCats.dbf"), new ExemptMzCategoryRowMapper());
        Map<String, List<Category>> categoriesMap = categories
                .stream()
                .collect(Collectors.groupingBy(Category::getId));
        exempts.parallelStream().forEach(exempt -> {
            if (exempt.getCategoryId() != null) {
                List<Category> categors = categoriesMap.get(exempt.getCategoryId());
                if (categors != null) {
                    exempt.setDiseases(Disease.createDeseases(categors));
                    exempt.setCategories(ExemptCategory.createCategories(categors));
                }
            }
        });
        Set<ExemptCategory> exemptCategories = new HashSet<>();
        Set<Disease> diseases = new HashSet<>();
        categories.stream().forEach(category -> {
            exemptCategories.add(new ExemptCategory(category));
            diseases.add(new Disease(category));
        });
        try (Session session = em.unwrap(Session.class)) {
            utx.begin();
            exemptCategories.forEach(session::saveOrUpdate);
            diseases.forEach(session::saveOrUpdate);
            exempts.stream().filter(exempt -> !exempt.isInvalid()).forEach(session::saveOrUpdate);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Map<String, Object> resMap = new HashMap<>();
        resMap.put("categories", exemptCategories);
        resMap.put("deseases", deseases);
        return resMap;*/

        return exempts.stream().filter(exempt -> exempt.getDiseases().size() > 1).limit(20).collect(Collectors.toList());
    }

    public Object uploadFederalDBF(MultipartFormDataInput input) throws IOException {
        Map<String, InputPart> files = new HashMap<>();
        input.getFormDataMap().values().forEach(inputParts -> {
            inputParts.forEach(inputPart -> {
                files.put(Recept.getFileName(inputPart), inputPart);
            });
        });

        List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(files.get("fp.dbf")
                .getBody(InputStream.class, null), "/tmp/exemptsFed.dbf"), new ExemptFederalRowMapper());
        Map<String, List<FederalInfo>> federalInfoMap = DbfProcessor
                .loadData(Recept.createFile(files.get("fl.dbf")
                        .getBody(InputStream.class, null), "/tmp/exemptFedInfo.dbf"), new ExemptFederalInfoRowMapper())
                .stream().collect(Collectors.groupingBy(FederalInfo::getSnils));

        exempts.parallelStream().forEach(exempt -> {
            if (exempt.getSnils() != null) {
                List<FederalInfo> federalInfos = federalInfoMap.get(exempt.getSnils());
                if (federalInfos != null && federalInfos.size() > 0) {
                    FederalInfo federalInfo = federalInfos.get(0);
                    if (federalInfo != null) {
                        exempt.setBenefitDoc(federalInfo.getBenefitDoc());
                        exempt.setBenefitDocNum(federalInfo.getBenefitDocNum());
                        exempt.setCategoryCode(federalInfo.getCategoryCode());
                        exempt.setDateLgBegin(federalInfo.getDateLgBegin());
                        exempt.setDateLgEnd(federalInfo.getDateLgEnd());
                    }
                }
            }
        });
        try (Session session = em.unwrap(Session.class)) {
            utx.begin();
            exempts.stream().forEach(session::saveOrUpdate);
            utx.commit();
        } catch (Exception e) {

        }

        return exempts.stream().limit(20).collect(Collectors.toList());
    }
}
