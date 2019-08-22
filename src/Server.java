import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public void ServerStart() {

        ServerSocket serverSocket = null;
        String serverIp = "127.0.0.1";
        int port = 7777;

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(serverIp, port));

            while (true) {

                System.out.println("완료...\n연결을 기다리는 중...");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                String clientIp = isa.getHostName();

                System.out.println("연결시작...Client IP : " + clientIp);
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                String message = ois.readObject().toString();

                //받은 정보를 Json 형태로 파싱후 TypedList로 가공
                Gson gson = new Gson();
                ArrayList<Data> dataList = gson.fromJson(message, new TypeToken<ArrayList<Data>>(){}.getType());
                SqlCon sqlCon = new SqlCon();
                sqlCon.insert(dataList);
                System.out.println(message);

                is.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("서버가 정상적으로 작동되지 않았습니다. 네트워크상태를 확인해주세요...");
        }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}


