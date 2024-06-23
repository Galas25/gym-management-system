/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.time.LocalDate;
import java.util.Random;

    
public class Employees {
    private Employees[] employee;
    int employeeCount;
    int employeeId;
    String name;
    String position;
    String contactNo;
    
    public Employees(int size){
        employee = new Employees[size];
        employeeCount = 0; 
    }
    public int emId(){
        
        int tempId = counter.getEmployeeCount() + 1;
        counter.setEmployeeCount(counter.employeeCount + 1);
        return tempId;
    }
    public Employees(String name, String position, String contactNo){
        this.employeeId = emId();
        this.name = name;
        this.contactNo = contactNo;
        this.position = position;
  
    }
    class Attendance{
        LocalDate clockIn;
        LocalDate clockOut;
        String shift;
        
        public Attendance(LocalDate in, LocalDate out, String shift){
            this.clockIn = in;
            this.clockOut = out;
            this.shift = shift;
        }

        public LocalDate getClockIn() {
            return clockIn;
        }

        public void setClockIn(LocalDate clockIn) {
            this.clockIn = clockIn;
        }

        public LocalDate getClockOut() {
            return clockOut;
        }

        public void setClockOut(LocalDate clockOut) {
            this.clockOut = clockOut;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }
        
    }
    
    //attendance

    class Payroll{
        int hrWorked;
        int overtime;
        int payrate;
        Random random = new Random();
        
        public Payroll(){
            this.hrWorked = random.nextInt(12) + 1;;
            this.overtime = random.nextInt(3) + 1;
            this.payrate = 500;
        }
        
        public Payroll(int hour, int overtime, int pay){
            this.hrWorked = hour;
            this.overtime = overtime;
            this.payrate = pay;

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

        public Random getRandom() {
            return random;
        }

        public void setRandom(Random random) {
            this.random = random;
        }
        
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
           
     private void resizeEmployeeArray() {
        int newSize = employee.length * 2; // Double the size of the current array
        Employees[] newArray = new Employees[newSize];
        // Copy existing members to the new array
        System.arraycopy(employee, 0, newArray, 0, employee.length);
        employee = newArray;
    }  
    
    public void addEmployee(Employees employees){
        if (employeeCount >= employee.length){
            resizeEmployeeArray();
        }

        employee[employeeCount] = employees;
        employeeCount++;
    }
    
    public void removeEmployee(int id) {
    int index = -1;
    
    // Find the index of the member with the given ID
    for (int i = 0; i < employeeCount; i++) {
        if (employee[i].getEmployeeId() == id) {
            index = i;
            break;
        }
    }
    
    if (index != -1) { // Member found
        // Shift elements to the left to fill the gap
        for (int i = index; i < employeeCount - 1; i++) {
            employee[i] = employee[i + 1];
        }
        
        // Nullify the last element (optional)
        employee[employeeCount - 1] = null;
        
        // Decrement the count of members
        employeeCount--;
        
        System.out.println("Employee with ID " + id + " has been removed.");
    } else {
        System.out.println("Employee not found.");
    }
}

   public Employees findEmployeeByID(int EmployeeID) {
        for (int i = 0; i < employeeCount; i++) {
            if (employee[i].getEmployeeId() == EmployeeID) {
                return employee[i];
            }
        }
        return null;
    }
 
        public Employees[] getEmployee() {
            return employee;
        }

        public void setEmployee(Employees[] employee) {
            this.employee = employee;
        }
        
        
        
    }

    