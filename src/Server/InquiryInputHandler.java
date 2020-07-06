package Server;

import Common.CommandsM.Exit;
import Common.CommandsM.General.Executable;
import Common.Security.User;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class InquiryInputHandler implements Runnable {
    private final SocketChannel channel;

    public InquiryInputHandler(SocketChannel channel){
        this.channel = channel;
    }

    @Override
    public void run() {
        if (channel == null) throw new IllegalArgumentException("Channel is null");
        try {

            while (!channel.socket().isClosed()) {
                Object object = receiveObject();
                if (!(object instanceof Executable))
                    if(object instanceof User){
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        ObjectOutputStream stream = new ObjectOutputStream(byteArray);
                        User user = (User)object;
                        if(user.getRegisterMode().equals("signin")) {
                            stream.writeObject(DataBaseHandler.checkLoginAccuracy(user));
                            if(DataBaseHandler.checkLoginAccuracy(user)){
                                UpdateSender.addChannel(channel);
                                UpdateSender.loadAllLabs(channel);
                            }
                        } else {
                            boolean success = DataBaseHandler.addUser(user);
                            stream.writeObject(success);
                            if(success){
                                UpdateSender.addChannel(channel);
                                UpdateSender.loadAllLabs(channel);
                            }
                        }
                        channel.write(ByteBuffer.wrap(byteArray.toByteArray()));
                        continue;
                    } else continue;
                ServerSide.processingInquiryPool.execute(new InquiryProcessingHandler((Executable) object, channel));
                if(object instanceof Exit) break;
            }
        } catch (IOException e) {
        }
    }

    /**
     * Метод получает объект
     *
     * @return Объект, полученный от сервера
     */
    private Object receiveObject() throws IOException {
        if (channel == null) {
            System.err.println("Соединене не создано");
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        channel.read(buffer);


        try {
            ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
            return objStream.readObject();
        } catch (Exception e) {
            return null;
        }
    }


}
