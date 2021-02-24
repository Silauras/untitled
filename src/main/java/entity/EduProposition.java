package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static entity.DataBaseConnection.getConnetion;

/**
 * @author Silauras
 * Created on 18.02.2021 at 18:39
 */

@Getter
@Setter
@AllArgsConstructor
public class EduProposition {
    private int id;
    private String eduSpan;
    private String eduType;
    private String studentType;
    private String eduLanguage;
    private String enrollmentNumber;
    private String enrollmentType;
    private BigDecimal staffCoeff;
    private int departmentId;
    private int acadDegreeId;
    private int directionId;
    private int specialityId;
    private int eduProgramId;

    public static List<EduProposition> getListOfAll() throws SQLException {
        String sql = "SELECT * FROM edu_proposition";
        ResultSet resultSet =  getConnetion().createStatement().executeQuery(sql);

        int id;
        String eduSpan;
        String eduType;
        String studentType;
        String eduLanguage;
        String enrollmentNumber;
        String enrollmentType;
        BigDecimal staffCoeff;
        int departmentId;
        int acadDegreeId;
        int directionId;
        int specialityId;
        int eduProgramId;

        List<EduProposition> eduPropositionList = new ArrayList<EduProposition>();
        while (resultSet.next()) {
            id = resultSet.getInt("edu_proposition_id");
            eduSpan = resultSet.getString("edu_span");
            eduType = resultSet.getString("edu_type");
            studentType = resultSet.getString("student_type");
            eduLanguage = resultSet.getString("edu_language");
            enrollmentNumber = resultSet.getString("enrollment_number");
            enrollmentType = resultSet.getString("enrollment_type");
            staffCoeff = resultSet.getBigDecimal("staff_coeff");
            departmentId = resultSet.getInt("department_id");
            acadDegreeId = resultSet.getInt("acad_degree_id");
            directionId = resultSet.getInt("direction_id");
            specialityId = resultSet.getInt("speciality_id");
            eduProgramId = resultSet.getInt("edu_program_id");
            eduPropositionList.add(
                    new EduProposition(
                            id,
                            eduSpan,
                            eduType,
                            studentType,
                            eduLanguage,
                            enrollmentNumber,
                            enrollmentType,
                            staffCoeff,
                            departmentId,
                            acadDegreeId,
                            directionId,
                            specialityId,
                            eduProgramId
                    )
            );
        }
        return eduPropositionList.isEmpty() ? null : eduPropositionList;
    }
}
