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
public class Client {
    public static void main(String[] args) throws SocketException, IOException {
        
      BufferedReader bacaClient =new BufferedReader(new InputStreamReader(System.in));
      InetAddress IP = InetAddress.getByName("192.168.100.12");
      DatagramSocket socketClient = new DatagramSocket();
      
      while(true) {
      byte[] kirimBuffer = new byte[1024]; //untuk mengirim data berupa byte array
      byte[] terimaBuffer = new byte[1024]; //untuk menerima data berupa byte array
      
      System.out.print("\nClient: "); 
      String clientData = bacaClient.readLine(); // untuk readLine inputan
      kirimBuffer = clientData.getBytes(); //sendbuffer berisi inputan byte array dalam clientdata
      // DatagramPacket untuk mengirim datagram yang berkonstruktor isi inputan (kirimBuffer) ,
      // panjang kirimBuffer, ip dan port saya
      DatagramPacket sendPacket = new DatagramPacket(kirimBuffer, kirimBuffer.length, IP, 1510);
      // Client socket akan memanggil method send yang isinya sendPacket
      socketClient.send(sendPacket);
      
      //untuk mengecek kondisi server data jika "bye" maka koneksi berakhir
      if(clientData.equalsIgnoreCase("bye")) {
          System.out.println("Connection ended by client");
          break;
      }
      //DatagramPacket untuk menerima datagram yang berkonstruktor terimaBuffer dan panjangnya
      DatagramPacket receivePacket = new DatagramPacket(terimaBuffer, terimaBuffer.length);
      socketClient.receive(receivePacket);
      String serverData = new String(receivePacket.getData()); // untuk menerima data/inputan yang dikirim 
      System.out.print("\nServer: " + serverData); //pesan ditampilkan disini
          
      }
      socketClient.close();
    }
}