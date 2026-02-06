package ui;
import domain.Student;
import domain.Teacher;
import service.StudentService;
import service.TeacherService;

public class SearchPerson {
        private Validation validation = new Validation();
        private StudentService studentService;
        private TeacherService teacherService;

        public SearchPerson(StudentService studentService, TeacherService teacherService) {
            this.studentService = studentService;
            this.teacherService = teacherService;
        }
//для студентіков (за ПІБ, курсом, групою)
        public void showStudentSearchMenu() {
            while (true) {
                System.out.println("--- ПОШУК СТУДЕНТА ---");
                System.out.println("1. За ПІБ");
                System.out.println("2. За курсом");
                System.out.println("3. За групою");
                System.out.println("0. Назад");

                int choice = validation.readInt("Оберіть опцію", 0, 3);
                switch (choice) {
                    case 1:
                        searchStudentByName();
                        break;
                    case 2:
                        searchStudentByCourse();
                        break;
                    case 3:
                        searchStudentByGroup();
                        break;
                    case 0:
                        return;
                }
                validation.waitZeroToExit();
            }
        }
        private void searchStudentByName() {
            System.out.println("Введіть повне ПІБ:");
            String fullName = validation.readString("(через пробіл)");
            String[] parts = fullName.trim().split("\\s+");
            if (parts.length < 3) {
                System.out.println("Помилка вводу");
                return;
            }
            Student found = studentService.findStudentByFullName(parts[1], parts[2], parts[0]);
            printResult(found);
        }
        private void searchStudentByCourse() {
            int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
            Student found = studentService.findStudentByCourse(course);
            printResult(found);
        }
        private void searchStudentByGroup() {
            int group = validation.readInt("Введіть номер групи: ", 1, 6);
            Student found = studentService.findStudentByGroup(group);
            printResult(found);
        }
    // вивід результатів студентів
    private void printResult(Student student) {
        if (student == null) {
            System.out.println("Нікого не знайдено");
        } else {
            System.out.println("Знайдено: ");
            System.out.println(student);
        }
    }



        // пошук викладача за ПІБ
        public void searchTeacherByName() {
            System.out.println("--- ПОШУК ВИКЛАДАЧА ---");
            System.out.println("Введіть повне ПІБ викладача (через пробіл):");
            String fullName = validation.readString("(через пробіл)");

            // [0]прізвище, [1]імя, [2]по батькові
            String[] parts = fullName.trim().split("\\s+");
            if (parts.length < 3) {
                System.out.println("Помилка вводу");
                return;
            }
            Teacher found = teacherService.findTeacherByFullName(parts[1], parts[2], parts[0]);
            if (found== null) {
                System.out.println("Викладача не знайдено");
            } else {
                System.out.println("Знайдено: ");
                System.out.println(found);
            }
        }

    }

