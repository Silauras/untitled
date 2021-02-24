package entity;

import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static entity.DataBaseConnection.getConnetion;

/**
 * @author Silauras
 * Created on 18.02.2021 at 18:24
 */

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class EduCatalog {
    @EqualsAndHashCode.Exclude
    int id;
    private String fullName;
    private String shortName;
    private String details;
    private String eduSpan;
    private String eduType;
    private String studentType;
    private String eduLanguage;
    private String enrollmentNumber;
    private String enrollmentType;


    public void save() {
        try {
            PreparedStatement preparedStatement = getConnetion().
                    prepareStatement(
                            "INSERT INTO edu_catalog" +
                                    "(edu_catalog_id,"+
                                    "full_name,"+
                                    "short_name,"+
                                    "details,"+
                                    "edu_span,"+
                                    "edu_type,"+
                                    "student_type,"+
                                    "edu_language,"+
                                    "enrollment_number,"+
                                    "enrollment_type)"+
                                    "VALUES(?,?,?,?,?,?,?,?,?,?)"
                    );
            int placeHolder = 1;

            preparedStatement.setObject(placeHolder++, id, java.sql.Types.INTEGER);
            preparedStatement.setObject(placeHolder++, "", java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, "", java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, "", java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, eduSpan, java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, eduType, java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, studentType, java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, eduLanguage, java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder++, enrollmentNumber, java.sql.Types.VARCHAR);
            preparedStatement.setObject(placeHolder, enrollmentType, java.sql.Types.VARCHAR);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static List<EduCatalog> getListOfAll() throws SQLException {
        String sql = "SELECT * FROM edu_catalog";
        ResultSet resultSet = getConnetion().createStatement().executeQuery(sql);

        int id;
        String fullName;
        String shortName;
        String details;
        String eduSpan;
        String eduType;
        String studentType;
        String eduLanguage;
        String enrollmentNumber;
        String enrollmentType;

        List<EduCatalog> eduCatalogList = new ArrayList<EduCatalog>();
        while (resultSet.next()){
            id = resultSet.getInt("edu_catalog_id");
            fullName = resultSet.getString("full_name");
            shortName = resultSet.getString("short_name");
            details = resultSet.getString("details");
            eduSpan = resultSet.getString("edu_span");
            eduType = resultSet.getString("edu_type");
            studentType = resultSet.getString("student_type");
            eduLanguage = resultSet.getString("edu_language");
            enrollmentNumber = resultSet.getString("enrollment_number");
            enrollmentType = resultSet.getString("enrollmentType");
            eduCatalogList.add(
                    new EduCatalog(
                            id,
                            fullName,
                            shortName,
                            details,
                            eduSpan,
                            eduType,
                            studentType,
                            eduLanguage,
                            enrollmentNumber,
                            enrollmentType
                    )
            );
        }
        return eduCatalogList.isEmpty() ? null : eduCatalogList;
    }
}
