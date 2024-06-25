package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Employees {
    private Employees[] employee;
    int employeeId;
    int employeeCount;
    String name;
    String position;
    String contactNo;
    List<Attendance> attendanceRecords;
    private double hourlyPayRate; // Added field for hourly pay rate

    public Employees(int size) {
        employee = new Employees[size];
        employeeCount = 0;
        attendanceRecords = new ArrayList<>();
    }

    public int memId(){
        int tempId = 0;
        tempId += counter.getEmployeeCount() + 1;
        counter.setEmployeeCount(tempId);
        return tempId;
        
    }

    public Employees(String name, String position, String contactNo) {
        this.employeeId = memId();
        this.name = name;
        this.contactNo = contactNo;
        this.position = position;
        this.hourlyPayRate = calculateHourlyPayRate(position); // Set hourly pay rate based on position
        this.attendanceRecords = new ArrayList<>();
    }
    
        
    
    
    public double calculateHourlyPayRate(String position) {
    switch (position) {
        case "General Manager":
            return (60000 + 80000) / 2080.0; // Average of the salary range divided by hours worked per year
        case "Assistant Manager":
            return (40000 + 55000) / 2080.0;
        case "Front Desk Receptionist":
            return (25000 + 35000) / 2080.0;
        case "Fitness Trainer/Instructor":
            return (30000 + 50000) / 2080.0;
        case "Personal Trainer":
            return (40000 + 60000) / 2080.0;
        case "Membership Sales Representative":
            return (30000 + 45000) / 2080.0;
        case "Cleaning Staff/Janitor":
            return (20000 + 30000) / 2080.0;
        case "Maintenance Technician":
            return (35000 + 50000) / 2080.0;
        case "Group Fitness Coordinator":
            return (35000 + 50000) / 2080.0;
        case "Nutritionist/Dietitian":
            return (45000 + 65000) / 2080.0;
        case "Marketing Manager":
            return (50000 + 70000) / 2080.0;
        case "Administrative Assistant":
            return (30000 + 40000) / 2080.0;
        default:
            return 0.0; // Return 0.0 if position not found or if hourly rate cannot be determined
    }
}



    public class Attendance {
        LocalDateTime clockIn;
        LocalDateTime clockOut;
        String shift;

        public Attendance(LocalDateTime in, LocalDateTime out, String shift) {
            this.clockIn = in;
            this.clockOut = out;
            this.shift = shift;
        }

        public LocalDateTime getClockIn() {
            return clockIn;
        }

        public void setClockIn(LocalDateTime clockIn) {
            this.clockIn = clockIn;
        }

        public LocalDateTime getClockOut() {
            return clockOut;
        }

        public void setClockOut(LocalDateTime clockOut) {
            this.clockOut = clockOut;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return "Clock In: " + clockIn.format(formatter) +
                   ", Clock Out: " + (clockOut != null ? clockOut.format(formatter) : "N/A") +
                   ", Shift: " + shift;
        }
    }

    public class Payroll {
        int hrWorked;
        int overtime;
        int payrate;
        Random random = new Random();

        public Payroll() {
            this.hrWorked = random.nextInt(40) + 1;
            this.overtime = random.nextInt(5) + 1;
            this.payrate = 16;
        }

        public Payroll(int hour, int overtime, int pay) {
            this.hrWorked = hour;
            this.overtime = overtime;
            this.payrate = pay;
        }
        
        public double calculateEarnings() {
        double regularPay = hrWorked * payrate;
        double overtimePay = overtime * 1.5 * payrate; // Assuming overtime rate is 1.5 times regular rate

        return regularPay + overtimePay;
        }
        
        public int getHrWorked() {
            return hrWorked;
        }

        public void setHrWorked(int hrWorked) {
            this.hrWorked = hrWorked;
        }

        public int getOvertime() {
            return overtime;
        }

        public void setOvertime(int overtime) {
            this.overtime = overtime;
        }

        public int getPayrate() {
            return payrate;
        }

        public void setPayrate(int payrate) {
            this.payrate = payrate;
        }
    }

    public void addAttendanceRecord(LocalDateTime clockIn, LocalDateTime clockOut, String shift) {
        attendanceRecords.add(new Attendance(clockIn, clockOut, shift));
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void displayAttendanceRecords() {
        System.out.println("Attendance Records for Employee: " + name);
        for (Attendance record : attendanceRecords) {
            System.out.println(record);
        }
    }

    // Existing methods for employee management

    private void resizeEmployeeArray() {
        int newSize = employee.length * 2;
        Employees[] newArray = new Employees[newSize];
        System.arraycopy(employee, 0, newArray, 0, employee.length);
        employee = newArray;
    }

    public void addEmployee(Employees employees) {
        if (employeeCount >= employee.length) {
            resizeEmployeeArray();
        }

        employee[employeeCount] = employees;
        employeeCount++;
    }

    public void removeEmployee(int id) {
        int index = -1;

        for (int i = 0; i < employeeCount; i++) {
            if (employee[i].getEmployeeId() == id) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < employeeCount - 1; i++) {
                employee[i] = employee[i + 1];
            }
            employee[employeeCount - 1] = null;
            employeeCount--;
            System.out.println("Employee with ID " + id + " has been removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public Employees findEmployeeByID(int EmployeeID) {
    for (int i = 0; i < employeeCount; i++) {
        if (employee[i] != null && employee[i].getEmployeeId() == EmployeeID) {
            return employee[i];
        }
    }
    return null;
}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Employees[] getEmployee() {
        return employee;
    }

    public void setEmployee(Employees[] employee) {
        this.employee = employee;
    }
}
