/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package benda_geometri;

/**
 *
 * @author LENOVOJOJO007
 */

public class MainProgram {

    public static void main(String[] args) {

        int totalData = 10000;

        int jumlahThread = 5;

        int dataPerThread =
                totalData / jumlahThread;

        WorkerThread[] threads =
                new WorkerThread[jumlahThread];

        // Membuat thread
        for (int i = 0; i < jumlahThread; i++) {

            threads[i] =
                    new WorkerThread(dataPerThread);

            threads[i].setName(
                    "Thread-" + (i + 1)
            );

            threads[i].start();
        }

        // Menunggu semua thread selesai
        for (int i = 0; i < jumlahThread; i++) {

            try {

                threads[i].join();

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        System.out.println(
                "\nSEMUA THREAD SELESAI"
        );
    }
}


    /**
     * @param args the command line arguments
     */
   

