package main;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Members {
    int id_count;
    String Name;
    int membershipId;
    LocalDate startDate;
    LocalDate expDate;
    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    
    Members(String Name, LocalDate startDate, LocalDate ExpDate){
    
        this.Name = Name;
        this.membershipId =  memId();
        this.startDate = startDate;
        this.expDate = ExpDate;
    
    }

    public int memId(){
        
        int tempId = counter.getCount() + 1;
        counter.setCount(counter.count + 1);
        return Integer.parseInt("1002024" + tempId);
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