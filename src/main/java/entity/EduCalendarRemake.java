package entity;

import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static entity.DataBaseConnection.getConnetion;

/**
 * @author Silauras
 * Created on 18.02.2021 at 19:47
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class EduCalendarRemake {

    private int id;
    private int acadYearId;
    private int acadDegreeId;
    private int eduCatalogId;

    public void save() {
        try {
            PreparedStatement preparedStatement = getConnetion().
                    prepareStatement(
                            "INSERT INTO edu_calendar" +
                                    "(edu_calendar_id," +
                                    "acad_year_id,"+
                                    "acad_degree_id,"+
                                    "edu_catalog_id) VALUES(?,?,?,?,?,?,?)");
            int placeHolder = 1;

            preparedStatement.setObject(placeHolder++, id, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, acadYearId, Types.DECIMAL);
            preparedStatement.setObject(placeHolder++, acadDegreeId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder, eduCatalogId, java.sql.Types.INTEGER);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
