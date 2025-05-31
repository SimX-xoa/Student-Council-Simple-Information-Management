/*public class Main {
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
        
        BBCD_StudentCouncil.findMember(70940);
//--------------------------------------------------------------------------------------------Test  
    }

}*/
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentCouncil council = new StudentCouncil();
        setupSampleData(council); // 添加示例数据
        
        // 创建服务器，监听8080端口
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // 设置路由
        server.createContext("/members", new MembersHandler(council));
        server.createContext("/events", new EventsHandler(council));
        server.createContext("/", new HomeHandler());
        
        server.setExecutor(null); // 使用默认执行器
        server.start();
        System.out.println("🚀 服务器已启动: http://localhost:8080");
    }
    
    private static void setupSampleData(StudentCouncil council) {
        Student s1 = new Student("Jiajia", "Cai", "somi@gmail.com", 11, 602, "President", 70947);
        Student s2 = new Student("Zifan", "Yuan", "Tony@gmail.com", 11, 574, "Communication Director", 70812);
        council.addMember(new Student[]{s1, s2});
        
        Event e1 = new Event("篮球杯", 2025, 10, 21);
        Event e2 = new Event("足球赛", 2024, 9, 14);
        council.addEventTo(new int[]{70947}, e1);
        council.addEventTo(new int[]{70947, 70812}, e2);
    }
    
    // 首页处理器
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<h1>🏫 学生会管理系统 API</h1>" +
                             "<p>可用端点:</p>" +
                             "<ul>" +
                             "<li><a href='/members'>/members</a> - 获取所有成员</li>" +
                             "<li><a href='/events'>/events</a> - 获取所有事件</li>" +
                             "</ul>";
            sendResponse(exchange, response);
        }
    }
    
    // 成员处理器
    static class MembersHandler implements HttpHandler {
        private final StudentCouncil council;
        
        public MembersHandler(StudentCouncil council) {
            this.council = council;
        }
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder response = new StringBuilder("<h1>👥 学生会成员</h1>");
            
            // 这里简化处理，实际应用中应该返回JSON格式
            for (Student student : council.getStudentList()) {
                response.append("<div style='border:1px solid #ccc; padding:10px; margin:10px;'>")
                       .append("<h3>").append(student.getName()).append("</h3>")
                       .append("<p>ID: ").append(student.getId()).append("</p>")
                       .append("<p>职位: ").append(student.getPosition()).append("</p>")
                       .append("</div>");
            }
            
            sendResponse(exchange, response.toString());
        }
    }
    
    // 事件处理器
    static class EventsHandler implements HttpHandler {
        private final StudentCouncil council;
        
        public EventsHandler(StudentCouncil council) {
            this.council = council;
        }
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder response = new StringBuilder("<h1>🎉 学生会事件</h1>");
            
            for (Event event : council.getEventList()) {
                response.append("<div style='border:1px solid #ccc; padding:10px; margin:10px;'>")
                       .append("<h3>").append(event.getName()).append("</h3>")
                       .append("<p>日期: ").append(event.getYear()).append("-")
                       .append(event.getMonth()).append("-").append(event.getDay()).append("</p>")
                       .append("</div>");
            }
            
            sendResponse(exchange, response.toString());
        }
    }
    
    // 发送响应通用方法
    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
