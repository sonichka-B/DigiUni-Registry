package repository.storage;


import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import service.DepartmentService;
import service.FacultyService;
import service.StudentService;
import service.TeacherService;

import java.time.LocalDate;


public class Data {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final DepartmentService departmentService;
    private final FacultyService facultyService;

    public Data(FacultyService f,  DepartmentService d, TeacherService t, StudentService s ) {
        this.facultyService =f;
        this.departmentService =d;
        this.teacherService =t;
        this.studentService =s;
    }

    public void initializeData(){
        //щоб можна було створити факультети
        Teacher deanFi = new Teacher("Глибовець Андрій Миколайович");
        Teacher deanFen = new Teacher("Лук'яненко Ірина Григорівна");
        Teacher deanFpn = new Teacher("Азаров Денис Сергійович");

        //=== ФАКУЛЬТЕТИ(3) ===
        Faculty fi = new Faculty("F-1", "Факультет інформатики", "ФІ", deanFi, "+380444256092");
        Faculty fen = new Faculty("F-2", "Факультет економічних наук", "ФЕН", deanFen, "+380444256042");
        Faculty fpn = new Faculty("F-3", "Факультет правничих наук", "ФПН", deanFpn, "+380444256073");

        facultyService.crud().getRepository().add(fi);
        facultyService.crud().getRepository().add(fen);
        facultyService.crud().getRepository().add(fpn);
    //-----------------------------------------
        //щоб можна було створити кафедри
        Teacher headInf = new Teacher("Бублик Володимир Васильович");
        Teacher headNet = new Teacher("Симоненко Валерій Павлович");
        Teacher headMath = new Teacher("Іванов Олександр Володимирович");
        Teacher headMulti = new Teacher("Франчук Оксана Вікторівна");

        Teacher headFin = new Teacher("Лук'яненко Ірина Григорівна");
        Teacher headEcon = new Teacher("Юргелевич Сергій Володимирович");
        Teacher headMark = new Teacher("Пітцик Марія Володимирівна");

        Teacher headZagal = new Teacher("Козюбра Микола Іванович");
        Teacher headPrivat = new Teacher("Безклубний Ігор Анатолійович");
        Teacher headKrym = new Teacher("Азаров Денис Сергійович");

        //=== КАФЕДРИ(10) ===
        // Кафедри ФІ (4)
        Department dInf = new Department("D-101", "Кафедра інформатики", fi, headInf, "Корпус 3, ауд. 315");
        Department dNet = new Department("D-102", "Кафедра мережних технологій", fi, headNet, "Корпус 3, ауд. 311");
        Department dMath = new Department("D-103", "Кафедра математики", fi, headMath, "Корпус 3, ауд. 314");
        Department dMulti = new Department("D-104", "Кафедра мультимедійних систем", fi, headMulti, "Корпус 1, ауд. 101");

        // Кафедри ФЕН (3)
        Department dFin = new Department("D-201", "Кафедра фінансів", fen, headFin, "Корпус 4, ауд. 412");
        Department dEcon = new Department("D-202", "Кафедра економічної теорії", fen, headEcon, "Корпус 4, ауд. 410");
        Department dMark = new Department("D-203", "Кафедра маркетингу та управління бізнесом", fen, headMark, "Корпус 4, ауд. 415");

        // Кафедри ФПН (3)
        Department dZagal = new Department("D-301", "Кафедра загальнотеоретичного правознавства та публічного права", fpn, headZagal, "Корпус 4, ауд. 320");
        Department dPrivat = new Department("D-302", "Кафедра приватного права", fpn, headPrivat, "Корпус 4, ауд. 322");
        Department dKrym = new Department("D-303", "Кафедра кримінального та кримінального процесуального права", fpn, headKrym, "Корпус 4, ауд. 324");

        departmentService.crud().getRepository().add(dInf); departmentService.crud().getRepository().add(dNet);
        departmentService.crud().getRepository().add(dMath); departmentService.crud().getRepository().add(dMulti);
        departmentService.crud().getRepository().add(dFin); departmentService.crud().getRepository().add(dEcon);
        departmentService.crud().getRepository().add(dMark); departmentService.crud().getRepository().add(dZagal);
        departmentService.crud().getRepository().add(dPrivat); departmentService.crud().getRepository().add(dKrym);

        //=== ВИКЛАДАЧІ(20) ===
        teacherService.crud().getRepository().add(new Teacher("T-01", "Бублик Володимир Васильович", dInf, "завідувач", "доктор наук", "професор", "2001-09-01", "1.0", LocalDate.of(1965, 5, 12), "bublyk@ukma.edu.ua", "+380671112201"));
        teacherService.crud().getRepository().add(new Teacher("T-02", "Глибовець Андрій Миколайович", dInf, "доцент", "кандидат наук", "доцент", "2008-09-01", "1.0", LocalDate.of(1980, 8, 24), "hlybovets@ukma.edu.ua", "+380671112202"));
        teacherService.crud().getRepository().add(new Teacher("T-03", "Симоненко Валерій Павлович", dNet, "доцент", "кандидат наук", "доцент", "2010-02-01", "1.0", LocalDate.of(1975, 3, 15), "symonenko@ukma.edu.ua", "+380671112203"));
        teacherService.crud().getRepository().add(new Teacher("T-04", "Бойко Тарас Андрійович", dNet, "старший викладач", "доктор філософії", "немає", "2018-09-01", "0.5", LocalDate.of(1990, 11, 2), "boyko@ukma.edu.ua", "+380671112204"));
        teacherService.crud().getRepository().add(new Teacher("T-05", "Іванов Олександр Володимирович", dMath, "професор", "доктор наук", "професор", "1999-09-01", "1.0", LocalDate.of(1960, 1, 10), "ivanov@ukma.edu.ua", "+380671112205"));
        teacherService.crud().getRepository().add(new Teacher("T-06", "Петренко Олена Миколаївна", dMath, "асистент", "немає", "немає", "2021-09-01", "0.5", LocalDate.of(1995, 7, 18), "petrenko.o@ukma.edu.ua", "+380671112206"));
        teacherService.crud().getRepository().add(new Teacher("T-07", "Франчук Оксана Вікторівна", dMulti, "доцент", "кандидат наук", "доцент", "2012-09-01", "1.0", LocalDate.of(1982, 4, 30), "franchuk@ukma.edu.ua", "+380671112207"));
        teacherService.crud().getRepository().add(new Teacher("T-08", "Мельник Дмитро Сергійович", dMulti, "викладач", "доктор філософії", "немає", "2020-09-01", "1.0", LocalDate.of(1992, 9, 14), "melnyk@ukma.edu.ua", "+380671112208"));

        teacherService.crud().getRepository().add(new Teacher("T-09", "Лук'яненко Ірина Григорівна", dFin, "професор", "доктор наук", "професор", "1995-09-01", "1.0", LocalDate.of(1962, 12, 5), "lukianenko@ukma.edu.ua", "+380671112209"));
        teacherService.crud().getRepository().add(new Teacher("T-10", "Шевченко Юлія Олегівна", dFin, "старший викладач", "кандидат наук", "немає", "2015-09-01", "1.0", LocalDate.of(1985, 2, 22), "shevchenko@ukma.edu.ua", "+380671112210"));
        teacherService.crud().getRepository().add(new Teacher("T-11", "Юргелевич Сергій Володимирович", dEcon, "доцент", "кандидат наук", "доцент", "2005-09-01", "1.0", LocalDate.of(1978, 10, 11), "yurhelevych@ukma.edu.ua", "+380671112211"));
        teacherService.crud().getRepository().add(new Teacher("T-12", "Коваленко Максим Петрович", dEcon, "викладач", "доктор філософії", "немає", "2019-09-01", "0.5", LocalDate.of(1991, 6, 8), "kovalenko@ukma.edu.ua", "+380671112212"));
        teacherService.crud().getRepository().add(new Teacher("T-13", "Пітцик Марія Володимирівна", dMark, "доцент", "кандидат наук", "доцент", "2010-09-01", "1.0", LocalDate.of(1980, 1, 25), "pitsyk@ukma.edu.ua", "+380671112213"));
        teacherService.crud().getRepository().add(new Teacher("T-14", "Савченко Ірина Вікторівна", dMark, "асистент", "немає", "немає", "2022-09-01", "0.5", LocalDate.of(1996, 4, 12), "savchenko@ukma.edu.ua", "+380671112214"));

        teacherService.crud().getRepository().add(new Teacher("T-15", "Козюбра Микола Іванович", dZagal, "професор", "доктор наук", "професор", "1992-09-01", "1.0", LocalDate.of(1955, 11, 8), "koziubra@ukma.edu.ua", "+380671112215"));
        teacherService.crud().getRepository().add(new Teacher("T-16", "Ткачук Олег Степанович", dZagal, "доцент", "кандидат наук", "доцент", "2007-09-01", "1.0", LocalDate.of(1975, 8, 19), "tkachuk@ukma.edu.ua", "+380671112216"));
        teacherService.crud().getRepository().add(new Teacher("T-17", "Безклубний Ігор Анатолійович", dPrivat, "доцент", "кандидат наук", "доцент", "2011-09-01", "1.0", LocalDate.of(1981, 2, 14), "bezklubny@ukma.edu.ua", "+380671112217"));
        teacherService.crud().getRepository().add(new Teacher("T-18", "Мороз Анастасія Ігорівна", dPrivat, "викладач", "доктор філософії", "немає", "2020-09-01", "1.0", LocalDate.of(1993, 10, 5), "moroz@ukma.edu.ua", "+380671112218"));
        teacherService.crud().getRepository().add(new Teacher("T-19", "Азаров Денис Сергійович", dKrym, "професор", "доктор наук", "професор", "2003-09-01", "1.0", LocalDate.of(1972, 7, 30), "azarov@ukma.edu.ua", "+380671112219"));
        teacherService.crud().getRepository().add(new Teacher("T-20", "Романенко Віктор Павлович", dKrym, "старший викладач", "кандидат наук", "немає", "2016-09-01", "0.5", LocalDate.of(1986, 5, 21), "romanenko@ukma.edu.ua", "+380671112220"));

        //=== СТУДЕНТИ(30) ===
        studentService.crud().getRepository().add(new Student("S-01", "Коваленко Ілля Максимович", 3, dInf, 1, 2021, "бюджет", "навчається", LocalDate.of(2003, 5, 10), "kovalenko.i@student.ukma.edu.ua", "+380991234501"));
        studentService.crud().getRepository().add(new Student("S-02", "Павленко Анна Вікторівна", 3, dInf, 1, 2021, "контракт", "навчається", LocalDate.of(2004, 1, 22), "pavlenko.a@student.ukma.edu.ua", "+380991234502"));
        studentService.crud().getRepository().add(new Student("S-03", "Григоренко Максим Олегович", 1, dInf, 2, 2023, "бюджет", "навчається", LocalDate.of(2005, 8, 15), "hryhorenko.m@student.ukma.edu.ua", "+380991234503"));

        studentService.crud().getRepository().add(new Student("S-04", "Бойко Софія Андріївна", 4, dNet, 1, 2020, "бюджет", "навчається", LocalDate.of(2002, 11, 30), "boyko.s@student.ukma.edu.ua", "+380991234504"));
        studentService.crud().getRepository().add(new Student("S-05", "Лисенко Артем Дмитрович", 2, dNet, 2, 2022, "контракт", "навчається", LocalDate.of(2004, 3, 14), "lysenko.a@student.ukma.edu.ua", "+380991234505"));
        studentService.crud().getRepository().add(new Student("S-06", "Сидоренко Марія Іванівна", 2, dNet, 2, 2022, "бюджет", "навчається", LocalDate.of(2004, 7, 8), "sydorenko.m@student.ukma.edu.ua", "+380991234506"));

        studentService.crud().getRepository().add(new Student("S-07", "Ткачук Олександр Петрович", 1, dMath, 1, 2023, "бюджет", "навчається", LocalDate.of(2006, 2, 18), "tkachuk.o@student.ukma.edu.ua", "+380991234507"));
        studentService.crud().getRepository().add(new Student("S-08", "Шевчук Вікторія Сергіївна", 3, dMath, 1, 2021, "контракт", "навчається", LocalDate.of(2003, 9, 25), "shevchuk.v@student.ukma.edu.ua", "+380991234508"));
        studentService.crud().getRepository().add(new Student("S-09", "Кравченко Денис Романович", 4, dMath, 2, 2020, "бюджет", "навчається", LocalDate.of(2002, 12, 5), "kravchenko.d@student.ukma.edu.ua", "+380991234509"));

        studentService.crud().getRepository().add(new Student("S-10", "Марченко Дарина Валеріївна", 2, dMulti, 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 4, 11), "marchenko.d@student.ukma.edu.ua", "+380991234510"));
        studentService.crud().getRepository().add(new Student("S-11", "Рудницький Богдан Юрійович", 2, dMulti, 1, 2022, "контракт", "навчається", LocalDate.of(2003, 10, 27), "rudnytskyi.b@student.ukma.edu.ua", "+380991234511"));
        studentService.crud().getRepository().add(new Student("S-12", "Савчук Катерина Миколаївна", 1, dMulti, 2, 2023, "бюджет", "навчається", LocalDate.of(2005, 6, 9), "savchuk.k@student.ukma.edu.ua", "+380991234512"));

        studentService.crud().getRepository().add(new Student("S-13", "Мельник Анастасія Павлівна", 3, dFin, 1, 2021, "бюджет", "навчається", LocalDate.of(2003, 1, 16), "melnyk.a@student.ukma.edu.ua", "+380991234513"));
        studentService.crud().getRepository().add(new Student("S-14", "Гончаренко Володимир Олегович", 4, dFin, 2, 2020, "контракт", "навчається", LocalDate.of(2002, 8, 3), "honcharenko.v@student.ukma.edu.ua", "+380991234514"));
        studentService.crud().getRepository().add(new Student("S-15", "Романчук Єлизавета Андріївна", 2, dFin, 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 11, 20), "romanchuk.y@student.ukma.edu.ua", "+380991234515"));

        studentService.crud().getRepository().add(new Student("S-16", "Олійник Тарас Максимович", 1, dEcon, 1, 2023, "бюджет", "навчається", LocalDate.of(2005, 3, 29), "oliynyk.t@student.ukma.edu.ua", "+380991234516"));
        studentService.crud().getRepository().add(new Student("S-17", "Ковальчук Вероніка Дмитрівна", 3, dEcon, 1, 2021, "контракт", "навчається", LocalDate.of(2003, 7, 14), "kovalchuk.v@student.ukma.edu.ua", "+380991234517"));
        studentService.crud().getRepository().add(new Student("S-18", "Поліщук Владислав Сергійович", 4, dEcon, 2, 2020, "бюджет", "навчається", LocalDate.of(2002, 5, 6), "polishchuk.v@student.ukma.edu.ua", "+380991234518"));

        studentService.crud().getRepository().add(new Student("S-19", "Кузьменко Юлія Віталіївна", 2, dMark, 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 9, 17), "kuzmenko.y@student.ukma.edu.ua", "+380991234519"));
        studentService.crud().getRepository().add(new Student("S-20", "Ільченко Назар Ігорович", 2, dMark, 2, 2022, "контракт", "навчається", LocalDate.of(2003, 12, 1), "ilchenko.n@student.ukma.edu.ua", "+380991234520"));
        studentService.crud().getRepository().add(new Student("S-21", "Захаренко Олена Валентинівна", 1, dMark, 1, 2023, "бюджет", "навчається", LocalDate.of(2005, 2, 24), "zakharenko.o@student.ukma.edu.ua", "+380991234521"));

        studentService.crud().getRepository().add(new Student("S-22", "Павлюк Роман Михайлович", 3, dZagal, 1, 2021, "бюджет", "навчається", LocalDate.of(2003, 6, 13), "pavliuk.r@student.ukma.edu.ua", "+380991234522"));
        studentService.crud().getRepository().add(new Student("S-23", "Білоус Діана Олександрівна", 4, dZagal, 2, 2020, "контракт", "навчається", LocalDate.of(2002, 4, 8), "bilous.d@student.ukma.edu.ua", "+380991234523"));
        studentService.crud().getRepository().add(new Student("S-24", "Клименко Олег Борисович", 2, dZagal, 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 10, 31), "klymenko.o@student.ukma.edu.ua", "+380991234524"));

        studentService.crud().getRepository().add(new Student("S-25", "Карпенко Соломія Вікторівна", 1, dPrivat, 1, 2023, "бюджет", "навчається", LocalDate.of(2005, 1, 5), "karpenko.s@student.ukma.edu.ua", "+380991234525"));
        studentService.crud().getRepository().add(new Student("S-26", "Демченко Артур Анатолійович", 3, dPrivat, 1, 2021, "контракт", "навчається", LocalDate.of(2003, 8, 19), "demchenko.a@student.ukma.edu.ua", "+380991234526"));
        studentService.crud().getRepository().add(new Student("S-27", "Гриценко Яна Ростиславівна", 2, dPrivat, 2, 2022, "бюджет", "навчається", LocalDate.of(2004, 3, 27), "hrytsenko.y@student.ukma.edu.ua", "+380991234527"));

        studentService.crud().getRepository().add(new Student("S-28", "Степаненко Тимур Георгійович", 4, dKrym, 1, 2020, "бюджет", "навчається", LocalDate.of(2002, 11, 14), "stepanenko.t@student.ukma.edu.ua", "+380991234528"));
        studentService.crud().getRepository().add(new Student("S-29", "Мороз Віталій Миколайович", 2, dKrym, 2, 2022, "контракт", "навчається", LocalDate.of(2004, 7, 2), "moroz.v@student.ukma.edu.ua", "+380991234529"));
        studentService.crud().getRepository().add(new Student("S-30", "Волкова Карина Станіславівна", 1, dKrym, 1, 2023, "бюджет", "навчається", LocalDate.of(2005, 12, 18), "volkova.k@student.ukma.edu.ua", "+380991234530"));
    }
}
