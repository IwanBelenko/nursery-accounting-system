import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pet {
    private int id;
    private String name;
    private String type;
    private LocalDate birthDate;
    private List<String> commands;

    public Pet() {
        commands = new ArrayList<>();
    }


    // Метод для добавления новой команды
    public void addCommand(String command) {
        commands.add(command);
    }

    public String getName() {
        return null;
    }

    public String getType() {
        return null;
    }

    public LocalDate getBirthDate() {
        return null;
    }

    public CharSequence getCommands() {
        return null;
    }

    public void setId(int id) {
    }

    public void setName(String next) {
    }

    public void setType(String next) {
    }

    public void setBirthDate(LocalDate parse) {
    }
}

class AnimalRegistry {
    private Connection connection;

    public AnimalRegistry() {
        // Установка соединения с базой данных
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal_base", "root", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для добавления нового животного в реестр
    public void addPet(Pet pet) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Pets (Name, Type, BirthDate, Commands) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setDate(3, Date.valueOf(pet.getBirthDate()));
            statement.setString(4, String.join(", ", pet.getCommands()));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    pet.setId(id);
                    System.out.println("Животное успешно добавлено в реестр с ID: " + id);
                }
            } else {
                System.out.println("Не удалось добавить животное в реестр.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода списка команд животного по ID
    public void printCommands(int petId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Commands FROM Pets WHERE ID = ?");
            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String commands = resultSet.getString("Commands");
                System.out.println("Список команд: " + commands);
            } else {
                System.out.println("Животное с указанным ID не найдено.");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для обучения животных новым командам
    public void teachCommands(int petId, String newCommand) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Pets SET Commands = CONCAT(Commands, ', ', ?) WHERE ID = ?");
            statement.setString(1, newCommand);
            statement.setInt(2, petId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Животное успешно обучено новой команде.");
            } else {
                System.out.println("Не удалось обучить животное новой команде.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода списка животных по дате рождения
    public void printPetsByBirthDate() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Pets ORDER BY BirthDate");

            System.out.println("Список животных по дате рождения:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String type = resultSet.getString("Type");
                LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();

                System.out.println("ID: " + id + ", Name: " + name + ", Type: " + type + ", Birth Date: " + birthDate);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для подсчета общего количества животных
    public void countTotalAnimals() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS TotalAnimals FROM Pets");

            if (resultSet.next()) {
                int totalAnimals = resultSet.getInt("TotalAnimals");
                System.out.println("Общее количество животных: " + totalAnimals);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для закрытия соединения с базой данных
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class AnimalRegistryApp {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Вывести список команд животного по ID");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Вывести список животных по дате рождения");
            System.out.println("5. Подсчитать общее количество животных");
            System.out.println("0. Выход");

            System.out.print("\nВыберите пункт меню: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Pet newPet = new Pet();

                    System.out.print("Введите имя животного: ");
                    newPet.setName(scanner.next());

                    System.out.print("Введите тип животного: ");
                    newPet.setType(scanner.next());

                    System.out.print("Введите дату рождения животного (гггг-мм-дд): ");
                    newPet.setBirthDate(LocalDate.parse(scanner.next()));

                    System.out.print("Введите список команд через запятую: ");
                    String commandsInput = scanner.next();
                    String[] commands = commandsInput.split(",");
                    for (String command : commands) {
                        newPet.addCommand(command.trim());
                    }

                    registry.addPet(newPet);
                    break;

                case 2:
                    System.out.print("Введите ID животного: ");
                    int petId = scanner.nextInt();
                    registry.printCommands(petId);
                    break;

                case 3:
                    System.out.print("Введите ID животного: ");
                    petId = scanner.nextInt();

                    System.out.print("Введите новую команду: ");
                    String newCommand = scanner.next();

                    registry.teachCommands(petId, newCommand);
                    break;

                case 4:
                    registry.printPetsByBirthDate();
                    break;

                case 5:
                    registry.countTotalAnimals();
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Неверный пункт меню.");
                    break;
            }

            System.out.println(); 
        }

        registry.closeConnection();
        scanner.close();
    }
}
