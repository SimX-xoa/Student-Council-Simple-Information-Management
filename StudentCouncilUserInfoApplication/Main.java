public class Main {
    public static void main(String[] args) {
//--------------------------------------------------------------------------------------------Test       
        StudentCouncil BBCD_StudentCouncil = new StudentCouncil();
        Student Somi = new Student("Jiajia", "Cai", "somi@gmail.com", 11, 602, "President", 70947);
        Student Tony = new Student("Zifan", "Yuan", "Tony@gmail.com", 11, 574, "Communication Director", 70812);
        Student Simba = new Student("Gengbo", "Xiang", "Simba@gmail.com", 9, 175, "Activity Coordinator", 70940);
        Student[] addStudents = {Somi, Tony, Simba};
        BBCD_StudentCouncil.addMember(addStudents);

        Event basketballHouseCup = new Event("2025 Basketball House Cup", 2025, 10, 21);
        Event footballChampionship = new Event("2025 Football Championship", 2024, 9, 14);
        int[] holdStuId1 = {70947, 70812};
        int[] holdStuId2 = {70947, 70940};
        BBCD_StudentCouncil.addEventTo(holdStuId1, basketballHouseCup);
        BBCD_StudentCouncil.addEventTo(holdStuId2, footballChampionship);
        BBCD_StudentCouncil.addEventTo(holdStuId2, basketballHouseCup);

        BBCD_StudentCouncil.findMember(70947);
        System.out.println();
        BBCD_StudentCouncil.findMember(70940);
        System.out.println();
        BBCD_StudentCouncil.findMember(70891);

        System.out.println(BBCD_StudentCouncil.totalMembers());
        System.out.println(BBCD_StudentCouncil.events());

        BBCD_StudentCouncil.removeMember(70940);
        System.out.println();
        BBCD_StudentCouncil.findMember(70940);
//--------------------------------------------------------------------------------------------Test  
    }

}
