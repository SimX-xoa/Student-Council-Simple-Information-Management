import java.util.*;

public class StudentCouncil {
    private ArrayList<Student> studentList;
    private ArrayList<Event> eventList;

    public StudentCouncil(){
        studentList = new ArrayList<>();
        eventList = new ArrayList<>();
    }

    public void addMember(Student[] s){
        for(Student student : s){
            studentList.add(student);
        }
    }

    public void removeMember(int id){
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).getId() == id){
                studentList.remove(i);
                break;
            }
        }
    }

    public void findMember(int id){
        boolean isFind = false;
        for(int i=0;i<studentList.size();i++){
            if(studentList.get(i).getId() == id){
                System.out.println(studentList.get(i));
                isFind = true;
                break;
            }
        }
        if(isFind == false){
            System.out.println("No user is find");
        }
    }

    public int totalMembers(){
        return studentList.size();
    }

    public void addEventTo(int[] ids, Event e){
        for(int targetId : ids){
            for(int i=0;i<studentList.size();i++){
                int id = studentList.get(i).getId();
                if(targetId == id){
                    studentList.get(i).addEvent(e);
                    break;
                }
            }
        }
        boolean isInEventList = false;
        for(Event event : eventList){
            if(event.getName().equals(e.getName())){
                isInEventList = true;
            }
        }
        if(isInEventList == false){
            eventList.add(e);
        }
    }

    public ArrayList<Event> events(){
        return eventList;
    }
}
