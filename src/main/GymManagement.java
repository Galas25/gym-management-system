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
     private void resizeMembersArray() {
        int newSize = members.length * 2; // Double the size of the current array
        Members[] newArray = new Members[newSize];
        // Copy existing members to the new array
        System.arraycopy(members, 0, newArray, 0, members.length);
        members = newArray;
    }
    
    public void addMember(Members member){
        if (count >= members.length){
            resizeMembersArray();
        }
        
        members[count] = member;
        count++;
    }
    
    public void removeMember(int id) {
    int index = -1;
    
    // Find the index of the member with the given ID
    for (int i = 0; i < count; i++) {
        if (members[i].getMembershipId() == id) {
            index = i;
            break;
        }
    }
    
    if (index != -1) { // Member found
        // Shift elements to the left to fill the gap
        for (int i = index; i < count - 1; i++) {
            members[i] = members[i + 1];
        }
        
        // Nullify the last element (optional)
        members[count - 1] = null;
        
        // Decrement the count of members
        count--;
        
        System.out.println("Member with ID " + id + " has been removed.");
    } else {
        System.out.println("Member not found.");
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
    public int printAllMembers() {
        int memcount = 0;
        for (int i = 0; i < count; i++) {
            System.out.println("Member ID: " + members[i].getMembershipId() + ", Name: " + members[i].getName());
            memcount += 1;
            
        }
        return memcount;
    
    }
    public int checkinToday(){
          int todayCount = 0;
        LocalDate today = LocalDate.now();
        
        for (int i = 0; i < count; i++) {
            // Assuming members[i] has a check-in time as a LocalDateTime field
            LocalDateTime checkinDateTime = members[i].getCheckInTime();
            
            if (checkinDateTime != null) {
                LocalDate checkinDate = checkinDateTime.toLocalDate();
                
                if (checkinDate.equals(today)) {
                    todayCount++;
                }
            }
        }
        
        return todayCount;
    }
    
    public int activeMembers(){
        int activeCount = 0;
        for (int i = 0; i < count; i++) {   
              if(members[i].getStartDate().isAfter(LocalDate.now().minusMonths(1))){    
                  activeCount += 1; 
              }   
        }
         return activeCount;
    }
    public Members[] getMembers() {
        return members;
    }

    public void setMembers(Members[] members) {
        this.members = members;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
   
}

