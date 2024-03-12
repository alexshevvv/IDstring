import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // запрос необходимой информации о пользователе
        System.out.print("Введите вашу фамилию: ");
        String LastName = scanner.nextLine();

        System.out.print("Введите ваше имя: ");
        String FirstName = scanner.nextLine();

        System.out.print("Введите ваше отчество (если есть): ");
        String MiddleName = scanner.nextLine();

        // Повторяем ввод даты, пока не будет введена корректная дата
        LocalDate BirthDate = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Введите вашу дату рождения в формате ГГГГ-ММ-ДД: ");
                String BirthDateStr = scanner.nextLine();

                // Проверка формата даты
                Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                Matcher matcher = pattern.matcher(BirthDateStr);
                if (!matcher.matches()) {
                    throw new DateTimeException("Дата должна быть в формате ГГГГ-ММ-ДД");
                }

                // Парсинг даты
                BirthDate = LocalDate.parse(BirthDateStr);

                // Проверка, что год рождения не больше текущего года
                if (BirthDate.isAfter(LocalDate.now())) {
                    throw new DateTimeException("Дата рождения не может быть в будущем");
                }

                validDate = true; // Если дата прошла проверки, устанавливаем флаг в true
            } catch (DateTimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // разделение ФИО пользователя на инициалы
        char FirstNameInitial = FirstName.charAt(0);
        char MiddleNameInitial = (MiddleName.isEmpty()) ? '-' : MiddleName.charAt(0);

        // определение пола (предполагаем, что мужской, если отчество не оканчивается на "а")
        char gender = (MiddleName.endsWith("а")) ? 'Ж' : 'M';

        // вычисление возраста в полных годах
        LocalDate CurrentDate = LocalDate.now();
        int age = Period.between(BirthDate, CurrentDate).getYears();

        // вывод информации о пользователе
        System.out.println("\nФамилия и инициалы: " + LastName + " " + FirstNameInitial + "." + MiddleNameInitial + ".");
        System.out.println("Пол: " + gender);
        System.out.println("Возраст: " + age + " лет");

        scanner.close();
    }
}
