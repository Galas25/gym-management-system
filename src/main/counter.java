/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Server
 */
public class counter {
    static int count = 0;
    static int memberCount = 0;
    static int employeeCount = 0;
    static int equipmentCount = 0;

    public static int getEquipmentCount() {
        return equipmentCount;
    }

    public static void setEquipmentCount(int equipmentCount) {
        counter.equipmentCount = equipmentCount;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public static void setEmployeeCount(int employeeCount) {
        counter.employeeCount = employeeCount;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        counter.count = count;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public static void setMemberCount(int memberCount) {
        counter.memberCount = memberCount;
    }
   
    
}
