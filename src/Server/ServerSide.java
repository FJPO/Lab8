package Server;

import Common.Source.LabWork;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Основной класс сервера
 */
public class ServerSide {

    protected static ExecutorService inquiryReaderPool;
    protected static ForkJoinPool processingInquiryPool;

    protected static List<LabWork> list;

    //Многопоточный
    public void loop(int port){



        inquiryReaderPool = Executors.newFixedThreadPool(101);
        processingInquiryPool = ForkJoinPool.commonPool();



        list = Collections.synchronizedList(DataBaseHandler.getLabCollection());
        System.out.println("Коллекция загружена из датабазы");
        System.out.printf("Адрес %s; порт %d\n", "127.0.0.1", port);

        try(ServerSocketChannel channel = ServerSocketChannel.open().bind(new InetSocketAddress(port))) {
            for(;;){
                SocketChannel channel1 = channel.accept();
                InquiryInputHandler input = new InquiryInputHandler(channel1);
                inquiryReaderPool.execute(input);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
