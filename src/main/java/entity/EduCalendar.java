package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static entity.DataBaseConnection.getConnetion;

/**
 * @author Silauras
 * Created on 18.02.2021 at 19:38
 */

@Getter
@Setter
@AllArgsConstructor
public class EduCalendar {
    private int id;
    private String eduSpan;
    private String eduType;
    private String studentType;
    private String eduLanguage;
    private String enrollmentNumber;
    private String enrollmentType;
    private int acadYearId;
    private int acadDegreeId;

    public static List<EduCalendar> getListOfAll() throws SQLException {
        String sql = "SELECT * FROM edu_calendar";
        ResultSet resultSet = getConnetion().createStatement().executeQuery(sql);

        int id;
        String eduSpan;
        String eduType;
        String studentType;
        String eduLanguage;
        String enrollmentNumber;
        String enrollmentType;
        int acadYearId;
        int acadDegreeId;

        List<EduCalendar> eduCalendarList = new ArrayList<EduCalendar>();
        while (resultSet.next()){
            id = resultSet.getInt("edu_calendar_id");
            eduSpan = resultSet.getString("edu_span");
            eduType = resultSet.getString("edu_type");
            studentType = resultSet.getString("student_type");
            eduLanguage = resultSet.getString("edu_language");
            enrollmentNumber = resultSet.getString("enrollment_number");
            enrollmentType = resultSet.getString("enrollment_Type");
            acadYearId = resultSet.getInt("acad_year_id");
            acadDegreeId = resultSet.getInt("acad_degree_id");
            eduCalendarList.add(
                    new EduCalendar(
                            id,
                            eduSpan,
                            eduType,
                            studentType,
                            eduLanguage,
                            enrollmentNumber,
                            enrollmentType,
                            acadYearId,
                            acadDegreeId
                    )
            );
        }
        return eduCalendarList.isEmpty() ? null : eduCalendarList;
    }
}
