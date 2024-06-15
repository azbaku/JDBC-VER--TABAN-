import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
    public static final String DB_URL="jdbc:mysql://localhost/employees";
    public static final String DB_USER="root";
    public static final String DB_PASSWORD="mysql";
    public static void main(String[] args) {


        try {

            Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE employees (id INT PRIMARY KEY, name VARCHAR(255), position VARCHAR(255), salary INT)";
            statement.executeUpdate(sql);
            System.out.println("employees tablosu başarıyla oluşturuldu!");
            sql = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);


            for (int i=1;i<=5;i++){
                //sql = "INSERT INTO employees VALUES (" + i + ", 'Employee " + i + "', 'position " + i + "', " + (i * 1000) + ")";
                Scanner sc=new Scanner(System.in);
                System.out.println(i+". çalışanın adını giriniz : ");
                String name=sc.nextLine();

                System.out.println(i+". çalışanın pozisyonunu giriniz : ");
                String position=sc.nextLine();

                System.out.println(i+". çalışanın maaşını giriniz : ");
                int salary=sc.nextInt();
                sql = "INSERT INTO employees VALUES (" + i + ", \"" + name + "\", \"" + position + "\", " + salary + ")";
                statement.executeUpdate(sql);


            }

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");

                System.out.printf("%d: %s (%s) - Salary: %.2f%n", id, name, position, salary);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}