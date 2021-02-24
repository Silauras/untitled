package entity;

import lombok.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static entity.DataBaseConnection.getConnetion;

/**
 * @author Silauras
 * Created on 18.02.2021 at 19:14
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class EduPropositionRemake {

    private int id;
    private BigDecimal staffCoeff;
    private int departmentId;
    private int directionId;
    private int specialityId;
    private int eduProgramId;
    private int eduCatalogId;
    private int acadDegreeId;

    public void save() {
        try {
            PreparedStatement preparedStatement = getConnetion().
                    prepareStatement(
                            "INSERT INTO edu_proposition " +
                                    "(edu_proposition_id," +
                                    "staff_coeff," +
                                    "department_id," +
                                    "acad_degree_id,"+
                                    "direction_id," +
                                    "speciality_id," +
                                    "edu_program_id," +
                                    "edu_catalog_id) VALUES(?,?,?,?,?,?,?,?)");
            int placeHolder = 1;

            preparedStatement.setObject(placeHolder++, id, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, staffCoeff, Types.DECIMAL);
            preparedStatement.setObject(placeHolder++, departmentId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, acadDegreeId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, directionId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, specialityId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, eduProgramId, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder, eduCatalogId, java.sql.Types.INTEGER);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
