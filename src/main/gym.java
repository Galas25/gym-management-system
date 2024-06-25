package main;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class gym {
  static String username = "admin";
  static String password = "test123";
  static GymManagement gym = new GymManagement(5);
  static Employees workers = new Employees(5);
  public static void main(String[] args) {
    
    gym.addMember(new Members("Alex Folsh", LocalDate.now().minusMonths(1), LocalDate.now().minusMonths(1).plusMonths(1),"Alex","123","Regular","0998333554"));
    gym.addMember(new Members("Julian Archeels", LocalDate.now(), LocalDate.now().plusMonths(3),"Galas","2555","Silver","0554882125"));
    
    gym.checkInMember(1, LocalDateTime.now());
    gym.checkInMember(2, LocalDateTime.now());
    
    workers.addEmployee(new Employees("Kulash Mithril","General Manager","0932-328-232"));
    workers.addEmployee(new Employees("Smithrianite Jade","Assistant Manager","0932-328-232"));
    
    Employees emp1 = workers.findEmployeeByID(1);
    Employees emp2 = workers.findEmployeeByID(2);
        if (emp1 != null || emp2 != null) {
            emp1.addAttendanceRecord(LocalDateTime.of(2024, 6, 24, 8, 0), LocalDateTime.of(2024, 6, 24, 18, 0), "Morning");
            emp1.addAttendanceRecord(LocalDateTime.of(2024, 6, 25, 8, 0), LocalDateTime.of(2024, 6, 25, 19, 0), "Morning");
            emp2.addAttendanceRecord(LocalDateTime.of(2024, 6, 24, 16, 0), LocalDateTime.of(2024, 6, 25, 2, 0), "Evening");
            emp2.addAttendanceRecord(LocalDateTime.of(2024, 6, 25, 16, 0), LocalDateTime.of(2024, 6, 26, 1, 0), "Evening");
        }

        // Display attendance records for the first employee
        if (emp1 != null || emp2 != null) {
            emp1.displayAttendanceRecords();
        }
    
//    new dashboard(username);
    login();
    
  }
  
  public static void login() {
    new login();
  }
  
  public static void adminLogin() {
   new adminLogin();
  }
  public static void signUp() {
    new Register();
  }
  
   
}
