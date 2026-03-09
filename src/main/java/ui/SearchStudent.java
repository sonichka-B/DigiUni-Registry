package ui;
import domain.Student;
import service.StudentSearchService;

public class SearchStudent {
        private Validation validation = new Validation();
        private StudentSearchService studentSearchService;

        public SearchStudent(StudentSearchService studentSearchService) {

            this.studentSearchService = studentSearchService;
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
                }
                validation.waitZeroToExit();
            }
        }
        private void searchStudentByName() {
            System.out.println("Введіть ІМ'Я ПРІЗВИЩЕ ПО-БАТЬКОВІ:");
            String fullName = validation.readNotEmptyString("(через пробіл)");
            studentSearchService.findStudentsByFullName(fullName);
        }
        private void searchStudentByCourse() {
            int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
           studentSearchService.findStudentsByCourse(course);
        }
        private void searchStudentByGroup() {
            int group = validation.readInt("Введіть номер групи: ", 1, 6);
            studentSearchService.findStudentsByGroup(group);
        }

    }

