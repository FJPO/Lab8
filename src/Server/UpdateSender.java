package Server;

import Common.Source.LabWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class UpdateSender {
    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    public static void addChannel(SocketChannel channel){
        channels.add(channel);
    }
    public static void removeChannel(SocketChannel channel){
        channels.remove(channel);
    }

    public static synchronized void update(LabWork lab){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream stream;
        try {
            stream = new ObjectOutputStream(byteArray);
            stream.writeObject(lab);
        } catch (IOException e) {
            e.printStackTrace();
        }

        channels.forEach(channel -> {
            try {
                channel.write(ByteBuffer.wrap(byteArray.toByteArray()));
            } catch (IOException e) {
                removeChannel(channel);
            }
        });
    }

    public static synchronized void loadAllLabs(SocketChannel channel){
        ServerSide.list.forEach(lab-> {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ObjectOutputStream stream = null;
            try {
                stream = new ObjectOutputStream(byteArray);
                stream.writeObject(lab);
                channel.write(ByteBuffer.wrap(byteArray.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
