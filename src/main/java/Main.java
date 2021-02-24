import static entity.DataBaseConnection.getConnetion;

import entity.*;

import java.sql.SQLException;
import java.util.*;

/**
 * @author Silauras
 * Created on 23.02.2021 at 17:02
 */

public class Main {
    static int id = 1;

    public static void main(String[] args) throws SQLException {
        List<EduProposition> eduPropositionList = EduProposition.getListOfAll();
        List<EduCalendar> eduCalendarList = EduCalendar.getListOfAll();
        execute(
                "create table edu_catalog\n" +
                        "(\n" +
                        "    edu_catalog_id    int auto_increment primary key,\n" +
                        "    full_name         varchar(255) null,\n" +
                        "    short_name        varchar(255) null,\n" +
                        "    details           varchar(255) null,\n" +
                        "    edu_span          varchar(255) not null,\n" +
                        "    edu_type          varchar(255) not null,\n" +
                        "    student_type      varchar(255) not null,\n" +
                        "    enrollment_type   varchar(255) not null,\n" +
                        "    edu_language      varchar(255) not null,\n" +
                        "    enrollment_number varchar(255) not null\n" +
                        ");"
        );

        execute("alter table edu_proposition drop column edu_span;");
        execute("alter table edu_proposition drop column edu_type;");
        execute("alter table edu_proposition drop column student_type;");
        execute("alter table edu_proposition drop column edu_language;");
        execute("alter table edu_proposition drop column enrollment_number;");
        execute("alter table edu_proposition drop column enrollment_type;");
        execute("alter table edu_proposition add edu_catalog_id int null;");
        execute("alter table edu_proposition add constraint edu_proposition_edu_catalog_edu_catalog_id_fk " +
                "foreign key (edu_catalog_id) references edu_catalog (edu_catalog_id);");


        execute("alter table edu_calendar drop column edu_span;");
        execute("alter table edu_calendar drop column edu_type;");
        execute("alter table edu_calendar add edu_catalog_id int null;");
        execute("alter table edu_calendar drop column student_type;");
        execute("alter table edu_calendar drop column edu_language;");
        execute("alter table edu_calendar drop column enrollment_number;");
        execute("alter table edu_calendar drop column enrollment_type;");
        execute("alter table edu_calendar add constraint edu_calendar_edu_catalog_edu_catalog_id_fk " +
                "foreign key (edu_catalog_id) references edu_catalog (edu_catalog_id);");


        List<EduCatalog> eduCatalogList = new ArrayList<>();

        if (eduPropositionList != null) {
            for (EduProposition eduProposition : eduPropositionList) {
                int currentId = 0;
                String eduSpan = eduProposition.getEduSpan();
                String eduType = eduProposition.getEduType();
                String studentType = eduProposition.getStudentType();
                String eduLanguage = eduProposition.getEduLanguage();
                String enrollmentNumber = eduProposition.getEnrollmentNumber();
                String enrollmentType = eduProposition.getEnrollmentType();
                EduCatalog eduCatalog = EduCatalog.builder()
                        .eduSpan(eduSpan)
                        .eduType(eduType)
                        .studentType(studentType)
                        .eduLanguage(eduLanguage)
                        .enrollmentNumber(enrollmentNumber)
                        .enrollmentType(enrollmentType)
                        .build();
                if (!eduCatalogList.contains(eduCatalog)) {
                    eduCatalog.setId(id++);
                    eduCatalog.save();
                    eduCatalogList.add(eduCatalog);
                    System.out.println("Added: " + eduCatalog.toString());
                }
                getConnetion()
                        .prepareStatement(String.format(
                                "UPDATE edu_proposition SET edu_catalog_id = '%d' WHERE edu_proposition_id = '%d';",
                                eduCatalogList.get(eduCatalogList.indexOf(eduCatalog)).getId(),
                                eduProposition.getId()
                                )
                        ).executeUpdate();
                System.out.println("Updated:" + EduPropositionRemake.builder()
                        .id(eduProposition.getId())
                        .staffCoeff(eduProposition.getStaffCoeff())
                        .departmentId(eduProposition.getDepartmentId())
                        .acadDegreeId(eduProposition.getAcadDegreeId())
                        .directionId(eduProposition.getDirectionId())
                        .specialityId(eduProposition.getSpecialityId())
                        .eduProgramId(eduProposition.getEduProgramId())
                        .eduCatalogId(eduCatalogList.get(eduCatalogList.indexOf(eduCatalog)).getId())
                        .build().toString()
                );
            }
        }
        if (eduCalendarList != null) {
            for (EduCalendar eduCalendar : eduCalendarList) {
                String eduSpan = eduCalendar.getEduSpan();
                String eduType = eduCalendar.getEduType();
                String studentType = eduCalendar.getStudentType();
                String eduLanguage = eduCalendar.getEduLanguage();
                String enrollmentNumber = eduCalendar.getEnrollmentNumber();
                String enrollmentType = eduCalendar.getEnrollmentType();
                EduCatalog eduCatalog = EduCatalog.builder()
                        .eduSpan(eduSpan)
                        .eduType(eduType)
                        .studentType(studentType)
                        .eduLanguage(eduLanguage)
                        .enrollmentNumber(enrollmentNumber)
                        .enrollmentType(enrollmentType)
                        .build();
                if (!eduCatalogList.contains(eduCatalog)) {
                    eduCatalog.setId(id++);
                    eduCatalog.save();
                    eduCatalogList.add(eduCatalog);
                    System.out.println("Added: " + eduCatalog.toString());
                }
                getConnetion()
                        .prepareStatement(String.format(
                                "UPDATE edu_calendar SET edu_catalog_id = '%d' WHERE edu_calendar_id = '%d';",
                                eduCatalogList.get(eduCatalogList.indexOf(eduCatalog)).getId(),
                                eduCalendar.getId()
                                )
                        ).executeUpdate();
                System.out.println("Updated: " +
                        EduCalendarRemake.builder()
                                .id(eduCalendar.getId())
                                .acadYearId(eduCalendar.getAcadYearId())
                                .acadDegreeId(eduCalendar.getAcadYearId())
                                .eduCatalogId(eduCatalogList.get(eduCatalogList.indexOf(eduCatalog)).getId())
                                .build().toString()
                );
            }
        }

        DataBaseConnection.connectionClose();
    }

    static void execute(String sql) throws SQLException {
        System.out.println(sql);
        getConnetion().prepareStatement(sql).execute();
    }
}
