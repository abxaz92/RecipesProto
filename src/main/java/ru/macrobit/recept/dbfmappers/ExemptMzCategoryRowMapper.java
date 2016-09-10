package ru.macrobit.recept.dbfmappers;

import org.jamel.dbf.processor.DbfRowMapper;
import ru.macrobit.recept.commons.Recept;
import ru.macrobit.recept.pojo.entities.Category;

/**
 * Created by [david] on 09.09.16.
 */
public class ExemptMzCategoryRowMapper implements DbfRowMapper<Category> {
    private static final String ENCODING = "windows-1251";

    @Override
    public Category mapRow(Object[] row) {
        Category category = new Category();
        category.setCategoryCode(Recept.getString(row[0], ENCODING));
        category.setMkbCode(Recept.getString(row[1], ENCODING));
        category.setMkbName(Recept.getString(row[9], ENCODING));
        category.setDeseaseName(Recept.getString(row[16], ENCODING));
        category.setDrugs(Recept.getString(row[17], ENCODING));
        category.setSovmest(Recept.getString(row[18], ENCODING));
        category.setId(Recept.getString(row[23], ENCODING));
        return category;
    }
}
