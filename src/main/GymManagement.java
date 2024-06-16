/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Server
 */
public class GymManagement {
    private Members[] members;
    private int count;
    
    public GymManagement(int size){
        members = new Members[size];
        count = 0; 
    }
    
    public void addMember(Members member){
        if (count <members.length){
            members[count] = member;
            count++;
        }else{
            System.out.println("full");
        } 
    }
    
   public Members findMemberByID(int membershipID) {
        for (int i = 0; i < count; i++) {
            if (members[i].getMembershipId() == membershipID) {
                return members[i];
            }
        }
        return null;
    }

    // Method to check in a member
    public void checkInMember(int membershipID, LocalDateTime checkInTime) {
        Members member = findMemberByID(membershipID);
         DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, MMMM d, h:mm a", Locale.ENGLISH);
        if (member != null) {
            member.setCheckInTime(checkInTime);
            System.out.println("Member " + member.getName()+ " checked in at " + checkInTime.format(format));
        } else {
            System.out.println("Member not found.");
        }
    }

    // Method to check out a member
    public void checkOutMember(int membershipID, LocalDateTime checkOutTime) {
        Members member = findMemberByID(membershipID);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, MMMM d, h:mm a", Locale.ENGLISH);
        if (member != null) {
            member.setCheckOutTime(checkOutTime);
            System.out.println("Member " + member.getName()+ " checked out at " + checkOutTime.format(format));
        } else {
            System.out.println("Member not found.");
        }
    }

    // Method to print all members
    public void printAllMembers() {
        for (int i = 0; i < count; i++) {
            System.out.println("Member ID: " + members[i].getMembershipId() + ", Name: " + members[i].getName());
        }
    }
}

