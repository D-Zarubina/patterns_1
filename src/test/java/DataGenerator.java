import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator(){
    }
    public static String generatorData(int shift){
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(){
        var cities = new String[]{"Великий Новгород", "Екатеринбург", "Йошкар-Ола", "Калининград", "Кемерово", "Киров",
                "Кострома", "Краснодар", "Красноярск", "Курган", "Курск", "Санкт-Петербург", "Сыктывкар", "Чебоксары",
                "Майкоп", "Москва", "Симферополь", "Смоленск", "Тамбов", "Архангельск", "Астрахань", "Барнаул",
                "Биробиджан", "Волгоград", "Горно-Алтайск", "Мурманск", "Нарьян-Мар", "Петропавловск-Камчатский",
                "Ростов-на-Дону", "Рязань", "Самара", "Саранск", "Ярославль", "Белгород", "Владивосток", "Ульяновск",
                "Калуга", "Севастополь", "Уфа", "Псков", "Томск", "Махачкала", "Салехард", "Казань", "Орёл", "Тула",
                "Абакан", "Благовещенск", "Владикавказ", "Горно-Алтайск", "Майкоп", "Хабаровск", "Ханты-Мансийск",
                "Южно-Сахалинск", "Нальчик", "Чебоксары", "Черкесск", "Анадырь", "Оренбург", "Пенза", "Петрозаводск"};
        return cities[new Random().nextInt(cities.length)];
    }
    public static String generateName(String locale){
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }
    public static String generatePhone(String locale){
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }
    public static class Registration {
        private Registration(){
        }
        public static UserInformation generateUser(String locale){
            return new UserInformation(generateCity(), generateName(locale), generatePhone(locale));
        }
    }
    @Value
    public static class UserInformation {
        String city;
        String name;
        String phone;
    }

}
