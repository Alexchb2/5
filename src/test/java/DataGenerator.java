import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    static {
        new Faker(new Locale("ru"));
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Москва", "Пенза", "Волгоград", "Саратов", "Казань"};
        int itemIndex = (int) (Math.random() * cities.length);
        return cities[itemIndex];
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static User generateUser() {
            return new User(generateCity(), generateDate(5), generateName(), generatePhone());
        }
    }

}
