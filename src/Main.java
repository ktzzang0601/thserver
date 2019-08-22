
/***
 *  Server Application
 *  문제에 조건 및 동기화 부분은 대부분 해결함
 *  MySQL, Socket 통신, Gson 이용
 *  서버부터 실행 후 클라이언트 실행을 권장함
 *
 * */

public class Main {

    public static void main(String[] args) {

        System.out.println("Server 시작 중...");
       Server server = new Server();
       server.ServerStart();
    }

}
