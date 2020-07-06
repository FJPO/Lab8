package Server;


public class Main {

    final static int PORT = 7788;

    public static void main(String[] args)  {

        new ServerSide().loop(PORT);

    }
}
