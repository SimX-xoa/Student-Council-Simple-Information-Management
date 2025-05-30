import java.util.*;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private int gradeLevel;
    private String position;
    private int id;
    private boolean isNew; //Students joined stu co greater than 1 school year is not considered new
    
    private ArrayList<Event> participatedEvents = new ArrayList<>();

    public Student (){
        firstName = "firstName";
        lastName = "lastName";
        email = "student@example.com";
        gradeLevel = 9;
        position = "position";
        id = 00000;
        isNew = true;
    }

    public Student (String fir, String las, String ema, int grd, int joinedDays, String pos, int id){
        firstName = fir;
        lastName = las;
        email = ema;
        gradeLevel = grd;
        position = pos;
        this.id = id;
        
        double years = (double)joinedDays / 365;
        if(years >= 1.0){
            isNew = false;
        }else{
            isNew = true;
        }
    }

    public String getName(){
        return lastName + " " + firstName;
    }

    public String getEmail(){
        return email;
    }

    public int getGradeLevel(){
        return gradeLevel;
    }

    public String getPosition(){
        return position;
    }

    public int getId(){
        return id;
    }

    public boolean isNew(){
        return isNew;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNew(){ //change student from new to old
        isNew = false;
    }

    public void addEvent(Event e){
        boolean isInEventList = false;
        for(Event event : participatedEvents){
            if(event.getName().equals(e.getName())){
                isInEventList = true;
            }
        }
        if(isInEventList == false){
            participatedEvents.add(e);
        }
    }

    public ArrayList<Event> getEvents(){
        return participatedEvents;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gradeLevel="
                + gradeLevel + ", position=" + position + ", id=" + id + ", isNew=" + isNew + ", participatedEvents="
                + participatedEvents + "]";
    }
}
