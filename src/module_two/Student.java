package module_two;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private List<Book> books;

    public Student(String name, int age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<Book> getBooks() { return books; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(", возраст: ").append(age).append("\n");
        sb.append("Книги:\n");
        for (Book book : books)
            sb.append("  - ").append(book).append("\n");
        return sb.toString();
    }
}
