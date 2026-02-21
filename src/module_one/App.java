package module_one;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Hobby originalHobby = new Hobby("Reading");
        List<Hobby> originalHobbies = new ArrayList<>();
        originalHobbies.add(originalHobby);
        originalHobbies.add(new Hobby("Swimming"));
        
        ImmutablePerson person = new ImmutablePerson("Alex", 30, originalHobbies);
        
        originalHobbies.add(new Hobby("Gaming"));
        originalHobby.setName("Coding");

        System.out.println("Original list: " + originalHobbies);
        System.out.println("Person hobbies: " + person.getHobbies());
        
    }
}
