package module_two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {

    public static List<Student> readStudentsFromFile(String filePath) throws IOException {
        StudentParser parser = new StudentParser();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (!line.isEmpty()) parser.processLine(line, lineNumber);
            }
        }

        parser.flush();
        return parser.getStudents();
    }

    private static class StudentParser {

        private static final int STUDENT_FIELDS = 3;
        private static final int BOOK_FIELDS = 5;

        private final List<Student> students = new ArrayList<>();
        private final List<Book> currentBooks = new ArrayList<>();

        private String currentName = null;
        private int currentAge = 0;
        private boolean damagedStudent = false;

        public void processLine(String line, int lineNumber) {
            String[] parts = line.split(";");

            switch (parts[0].toUpperCase()) {
                case "STUDENT": handleStudent(parts, lineNumber, line); break;
                case "BOOK": handleBook(parts, lineNumber, line); break;
                default:
                    logWarning(lineNumber, "неизвестный тип записи \"" + parts[0] + "\"", line);
                    break;
            }
        }

        public void flush() {
            saveCurrentStudent();
        }

        public List<Student> getStudents() {
            return students;
        }

        private void handleStudent(String[] parts, int lineNumber, String line) {
            saveCurrentStudent();
            currentBooks.clear();
            currentName = null;
            damagedStudent = false;

            if (parts.length < STUDENT_FIELDS) {
                logWarning(lineNumber, "ожидалось " + STUDENT_FIELDS +
                    " поля для STUDENT, найдено " + parts.length, line);
                damagedStudent = true;
                return;
            }

            currentName = parts[1].trim();
            if (currentName.isEmpty()) {
                logWarning(lineNumber, "имя студента не может быть пустым", line);
                damagedStudent = true;
                return;
            }

            currentAge = parsePositiveInt(parts[2].trim(), "возраст студента", lineNumber, line);
            if (currentAge < 0) damagedStudent = true;
        }

        private void saveCurrentStudent() {
            if (currentName != null && !damagedStudent)
                students.add(new Student(currentName, currentAge, new ArrayList<>(currentBooks)));
        }

        private void handleBook(String[] parts, int lineNumber, String line) {
            if (damagedStudent) return;            
            if (currentName == null) {
                logWarning(lineNumber, "запись BOOK найдена раньше любой записи STUDENT", line);
                return;
            }
            if (parts.length < BOOK_FIELDS) {
                logWarning(lineNumber, "ожидалось " + BOOK_FIELDS +
                    " полей для BOOK, найдено " + parts.length, line);
                return;
            }

            String title  = parts[1].trim();
            String author = parts[2].trim();

            if (title.isEmpty()) {
                logWarning(lineNumber, "название книги не может быть пустым", line);
                return;
            }
            if (author.isEmpty()) {
                logWarning(lineNumber, "автор книги не может быть пустым", line);
                return;
            }

            int pages = parsePositiveInt(parts[3].trim(), "количество страниц", lineNumber, line);
            if (pages < 0) return;
            int year = parsePositiveInt(parts[4].trim(), "год издания", lineNumber, line);
            if (year < 0) return;

            currentBooks.add(new Book(title, author, pages, year));
        }

        private static void logWarning(int lineNumber, String message, String line) {
            System.err.println(
                "Предупреждение: строка " + lineNumber + ": " + message + ", строка пропущена \"" + line + "\"");
        }

        private static int parsePositiveInt(String value, String fieldName, int lineNumber, String line) {
            try {
                int result = Integer.parseInt(value);
                if (result <= 0) {
                    logWarning(lineNumber, "поле \"" + fieldName + "\" должно быть положительным числом, " +
                        "получено " + result, line);
                    return -1;
                }
                return result;
            } catch (NumberFormatException e) {
                logWarning(lineNumber, "поле \"" + fieldName + "\" должно быть числом, " +
                    "получено \"" + value + "\"", line);
                return -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Student> students = readStudentsFromFile(
            System.getProperty("user.dir") + "/src/module_two/students.txt");

        students.stream()
            .peek(System.out::println)
            .flatMap(student -> student.getBooks().stream())
            .sorted(Comparator.comparingInt(Book::getPages))
            .distinct()
            .filter(book -> book.getYear() > 2000)
            .limit(3)
            .map(Book::getYear)
            .findFirst()
            .ifPresentOrElse(
                year -> System.out.println("\nГод выпуска найденной книги: " + year),
                ()   -> System.out.println("\nКнига, выпущенная после 2000 года, отсутствует")
            );
    }
}