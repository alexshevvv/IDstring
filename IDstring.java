import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class IDstring {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // запрос необходимой информации о пользователе
        System.out.print("Введите вашу фамилию: ");
        String LastName = scanner.nextLine();

        System.out.print("Введите ваше имя: ");
        String FirstName = scanner.nextLine();

        System.out.print("Введите ваше отчество: ");
        String MiddleName = scanner.nextLine();

        System.out.print("Введите вашу дату рождения в формате ГГГГ-ММ-ДД: ");
        String BirthDateStr = scanner.nextLine();

        // разделение ФИО пользователя на инициалы
        char FirstNameInitial = FirstName.charAt(0);
        char MiddleNameInitial = MiddleName.charAt(0);

        // определение пола (предполагаем, что мужской, если отчество не оканчивается на "а")
        char gender = (MiddleName.endsWith("а")) ? 'Ж' : 'M';

        // вычисление возраста в полных годах
        LocalDate BirthDate = LocalDate.parse(BirthDateStr);
        LocalDate CurrentDate = LocalDate.now();
        int age = Period.between(BirthDate, CurrentDate).getYears();

        // вывод информации о пользователе
        System.out.println("\nФамилия и инициалы: " + LastName + " " + FirstNameInitial + "." + MiddleNameInitial + ".");
        System.out.println("Пол: " + gender);
        System.out.println("Возраст: " + age + " лет");

        scanner.close();
    }
}
