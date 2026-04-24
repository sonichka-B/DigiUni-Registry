package ui;
import service.StudentSearchService;
import service.StudentService;
import validation.ValidPIB;
import validation.Validation;

public class SearchStudent extends BaseMenu{
        private Validation validation = new Validation();
//        private StudentSearchService studentSearchService;
        StudentService studentService;
        private ValidPIB validPIB = new ValidPIB();

        public SearchStudent(StudentService studentService) {
            this.studentService = studentService;
        }
//для студентіков (за ПІБ, курсом, групою)
  @Override
protected void printTitle() {
    System.out.println("--- ПОШУК СТУДЕНТА ---");
}

    @Override
    protected void printOptions() {
        System.out.println("1. За ПІБ");
        System.out.println("2. За курсом");
        System.out.println("3. За групою");
        System.out.println("0. Назад");
    }

    @Override
    protected int getMaxOption() {
        return 3;
    }

    @Override
    protected void handleChoice(int choice) {
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
        }
        validation.waitZeroToExit();
    }
        private void searchStudentByName() {
            System.out.println("Введіть ІМ'Я ПРІЗВИЩЕ ПО-БАТЬКОВІ:");
            String fullName = validPIB.validPIB("(через пробіл)");
//            studentSearchService.findStudentsByFullName(fullName);
            studentService.search().findStudentsByFullName(fullName);
        }
        private void searchStudentByCourse() {
            int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
//           studentSearchService.findStudentsByCourse(course);
            studentService.search().findStudentsByCourse(course);
        }
        private void searchStudentByGroup() {
            int group = validation.readInt("Введіть номер групи: ", 1, 6);
//            studentSearchService.findStudentsByGroup(group);
            studentService.search().findStudentsByGroup(group);
        }

    }

