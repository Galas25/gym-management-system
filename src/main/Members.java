package main;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Members {
    int id_count;
    String Name;
    int membershipId;
    String username;
    String password;
    LocalDate startDate;
    LocalDate expDate;
    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    String membershipType;
    String contact;
    
    Members(String Name, LocalDate startDate, LocalDate ExpDate, String username, String password, String membershipType, String contact){
    
        this.Name = Name;
        this.membershipId =  memId();
        this.startDate = startDate;
        this.expDate = ExpDate;
        this.username = username;
        this.password = password;
        this.membershipType = membershipType;
        this.contact = contact;
    
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int memId(){
        
        int tempId = counter.getCount() + 1;
        counter.setCount(counter.count + 1);
        return tempId;
    }

    public int getId_count() {
        return id_count;
    }

    public void setId_count(int id_count) {
        this.id_count = id_count;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public int getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getExpDate() {
        return expDate;
    }
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}