package UDPTestClient;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

class Main {

    private static DatagramSocket datagramSocket;
    private static InetAddress hostAddress;
    private static int portNumber = 80;
    private static String clientRequest;

    public static void main(String[] args) {

        //Generate GUI Form
        MainFrame mainForm = new MainFrame();
        JFrame frame = new JFrame("UDP Sender/Receiver Test Client");
        frame.setContentPane(mainForm.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Add Action Listener to the IP Address field
        mainForm.getHostIPAddressField().addActionListener(ae -> {
            try {
                hostAddress = InetAddress.getByName(mainForm.getHostIPAddressField().getText());
            } catch (UnknownHostException e) {
                e.printStackTrace();
                mainForm.getConsoleArea().append("Exception : " + e.getMessage());
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
                mainForm.getHostIPAddressField().setText("127.0.0.1");
            }
        });

        //Add Action Listener to the Port Number field
        mainForm.getPortNumberField().addActionListener(ae -> {
            try {
                portNumber = Integer.parseInt(mainForm.getPortNumberField().getText());
                //    datagramSocket.close();
                datagramSocket = new DatagramSocket(portNumber);
            } catch (Exception e) {
                e.printStackTrace();
                mainForm.getConsoleArea().append("Exception : " + e.getMessage());
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
                mainForm.getPortNumberField().setText("80");
            }
        });

        //Add Action Listener to the Client Request field
        mainForm.getClientRequestField().addActionListener(ae -> {
            try {
                clientRequest = mainForm.getClientRequestField().getText();
            } catch (Exception e) {
                e.printStackTrace();
                mainForm.getConsoleArea().append("Exception : " + e.getMessage());
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
                mainForm.getClientRequestField().setText("");
            }
        });

        //Add Action Listener to the Send Button
        mainForm.getSendButton().addActionListener(ae -> {
            try {
                hostAddress = InetAddress.getByName(mainForm.getHostIPAddressField().getText());
                portNumber = Integer.parseInt(mainForm.getPortNumberField().getText());
                clientRequest = mainForm.getClientRequestField().getText();

                byte[] clientBuffer = clientRequest.getBytes();
                DatagramPacket clientPacket = new DatagramPacket(clientBuffer, clientBuffer.length, hostAddress, portNumber);
                datagramSocket.send(clientPacket);
                mainForm.getConsoleArea().append("Client Request: " + clientRequest);
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
                mainForm.getClientRequestField().setText("");
            } catch (Exception e) {
                e.printStackTrace();
                mainForm.getConsoleArea().append("Exception : " + e.getMessage());
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
            }
        });

        try {
            datagramSocket = new DatagramSocket();
            hostAddress = InetAddress.getLocalHost();

            boolean isRunnable = true;
            while (isRunnable) {
                byte[] hostBuffer = new byte[1024];
                DatagramPacket hostPacket = new DatagramPacket(hostBuffer, hostBuffer.length);
                datagramSocket.receive(hostPacket);
                String hostResponse = new String(hostPacket.getData(), 0, hostPacket.getLength());
                mainForm.getConsoleArea().append("Host Response: " + hostResponse);
                mainForm.getConsoleArea().append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mainForm.getConsoleArea().append("Exception : " + e.getMessage());
            mainForm.getConsoleArea().append(System.getProperty("line.separator"));
        }

    }
}