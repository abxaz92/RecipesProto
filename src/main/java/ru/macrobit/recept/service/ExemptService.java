package ru.macrobit.recept.service;

import org.hibernate.Session;
import org.jamel.dbf.processor.DbfProcessor;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import ru.macrobit.recept.abstracts.AbstractDAO;
import ru.macrobit.recept.commons.ExemptType;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.dbfmappers.*;
import ru.macrobit.recept.pojo.*;
import ru.macrobit.recept.pojo.entities.Category;
import ru.macrobit.recept.pojo.entities.FederalInfo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by david on 11.07.16.
 */
@ApplicationScoped
public class ExemptService extends AbstractDAO<Exempt> {
    private static final String tablename = "exempt";
    @Inject
    private IllegalExemptService illegalExemptService;
    @Inject
    private ExemptCategoryService exemptCategoryService;

    public ExemptService() {
        super("exempt", Exempt.class);
    }

    public Object uploadMintrudDBF(MultipartFormDataInput input) throws IOException {
        Map<String, InputPart> files = new HashMap<>();
        input.getFormDataMap().values().forEach(inputParts -> {
            inputParts.forEach(inputPart -> files.put(Recept.getFileName(inputPart), inputPart));
        });

        List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(files.get("mintrud.dbf")
                .getBody(InputStream.class, null), "/tmp/exempts.dbf"), new ExemptMTRowMapper());
        Map<String, ExemptCategory> categoryMap = exemptCategoryService.getCategoryMap();
        Map<ExemptId, Exempt> exemptMap = new ConcurrentHashMap<>();
        exempts.stream().forEach(exempt -> {
            if (exempt.getCategoryCode() != null) {
                String[] categories = exempt.getCategoryCode().split(" ");
                for (int i = 0; i < categories.length; i++) {
                    exempt.getCategories().add(categoryMap.get(categories[i]));
                }
            }
            exemptMap.put(exempt.getDoc(), exempt);
        });

        try (Session session = em.unwrap(Session.class)) {
            utx.begin();
            exemptMap.values().stream().forEach(session::saveOrUpdate);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exempts.stream().limit(100).collect(Collectors.toList());
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
        Map<ExemptId, IllegalExempt> illegalExempts = new HashMap<>();
        ConcurrentHashMap<ExemptId, Exempt> exemptMap = new ConcurrentHashMap<>();
        exempts.stream().forEach(exempt -> {
            if (exempt.getId() == null) {
                IllegalExempt illegalExempt = new IllegalExempt(exempt);
                illegalExempt.setDoc(new ExemptId(illegalExempt.getCompositeId(), ExemptType.MINZDRAV));
                illegalExempts.put(illegalExempt.getDoc(), illegalExempt);
                return;
            }
            Exempt exempt1 = exemptMap.putIfAbsent(exempt.getDoc(), exempt);
            if (exempt1 != null) {
                IllegalExempt illegalExempt = new IllegalExempt(exempt);
                if (illegalExempt.getDoc().getId() != null)
                    illegalExempts.put(illegalExempt.getDoc(), illegalExempt);
            }
        });

        try (Session session = em.unwrap(Session.class)) {
            session.getTransaction().begin();
            exemptCategories.forEach(session::saveOrUpdate);
            diseases.forEach(session::saveOrUpdate);
            exemptMap.values().forEach(session::saveOrUpdate);
//            illegalExempts.values().forEach(session::saveOrUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return illegalExempts.values();
    }

    public Object uploadFederalDBF(MultipartFormDataInput input) throws IOException {
        Map<String, InputPart> files = new HashMap<>();
        input.getFormDataMap().values().forEach(inputParts -> {
            inputParts.forEach(inputPart -> files.put(Recept.getFileName(inputPart), inputPart));
        });

        List<Exempt> exempts = DbfProcessor.loadData(Recept.createFile(files.get("fp.dbf")
                .getBody(InputStream.class, null), "/tmp/exemptsFed.dbf"), new ExemptFederalRowMapper());
        Map<String, List<FederalInfo>> federalInfoMap = DbfProcessor
                .loadData(Recept.createFile(files.get("fl.dbf")
                        .getBody(InputStream.class, null), "/tmp/exemptFedInfo.dbf"), new ExemptFederalInfoRowMapper())
                .stream().collect(Collectors.groupingBy(FederalInfo::getSnils));

        Map<String, ExemptCategory> categoryMap = exemptCategoryService.getCategoryMap();

        exempts.parallelStream().forEach(exempt -> {
            if (exempt.getSnils() != null) {
                List<FederalInfo> federalInfos = federalInfoMap.get(exempt.getSnils());
                if (federalInfos != null && federalInfos.size() > 0) {
                    FederalInfo federalInfo = federalInfos.get(0);
                    if (federalInfo != null) {
                        exempt.setBenefitDoc(federalInfo.getBenefitDoc());
                        exempt.setBenefitDocNum(federalInfo.getBenefitDocNum());
                        exempt.setDateLgBegin(federalInfo.getDateLgBegin());
                        exempt.setDateLgEnd(federalInfo.getDateLgEnd());
                    }
                }
                if (exempt.getCategoryCode() != null) {
                    exempt.getCategories().add(categoryMap.get(exempt.getCategoryCode()));
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
