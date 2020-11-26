/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/**
 *
 * @author ernol
 */
public class Server {

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(1510);
        boolean bye = false;
        
        while (true) {
            
            byte[] menerima = new byte[1024]; //untuk menerima data berupa byte array
            byte[] mengirim = new byte[1024]; //untuk mengirim data berupa byte array
            // konstruktor untuk menerima datagram
            DatagramPacket dpTerima = new DatagramPacket(menerima, menerima.length);
            serverSocket.receive(dpTerima);
            InetAddress IP = dpTerima.getAddress(); //untuk menerima ip
            int nmrport = dpTerima.getPort(); //untuk menerima port 
            String clientdata = new String(dpTerima.getData()); // untuk menerima data/inputan yang dikirim 
            System.out.println("\nClient : " + clientdata);
            System.out.print("\nServer : ");
            BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
            String serverdata = serverRead.readLine();

            mengirim = serverdata.getBytes(); //sendbuffer berisi inputan byte array dalam clientdata
            // DatagramPacket untuk mengirim datagram yang berkonstruktor isi inputan (mengirim) ,
            DatagramPacket dpKirim = new DatagramPacket(mengirim, mengirim.length, IP, nmrport);
            serverSocket.send(dpKirim);
            //untuk mengecek kondisi server data jika "bye" maka koneksi berakhir
            if (serverdata.equalsIgnoreCase("bye")) {
                System.out.println("connection ended by server");
                break;
            }
        }
        serverSocket.close();
    }
}