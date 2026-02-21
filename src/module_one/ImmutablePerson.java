package module_one;

import java.util.ArrayList;
import java.util.List;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<Hobby> hobbies;

    public ImmutablePerson(String name, int age, List<Hobby> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = copyHobbies(hobbies);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Hobby> getHobbies() {
        return copyHobbies(hobbies);
    }

    private static List<Hobby> copyHobbies(List<Hobby> source) {
        List<Hobby> copied = new ArrayList<>(source.size());
        for (Hobby hobby : source) {
            copied.add(new Hobby(hobby.getName()));
        }
        return copied;
    }
}
