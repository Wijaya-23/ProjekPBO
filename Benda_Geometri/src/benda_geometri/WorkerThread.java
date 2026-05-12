/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package benda_geometri;

/**
 *
 * @author LENOVOJOJO007
 */

import java.util.Random;

public class WorkerThread extends Thread {

    private int jumlahData;

    // Constructor
    public WorkerThread(int jumlahData) {
        this.jumlahData = jumlahData;
    }

    @Override
    public void run() {

        Random rand = new Random();

        System.out.println(
                "=== " + Thread.currentThread().getName()
                + " MULAI ==="
        );

        for (int i = 1; i <= jumlahData; i++) {

            // Generate data random
            double alas = rand.nextDouble() * 100;
            double tinggi = rand.nextDouble() * 100;
            double sisiMiring = rand.nextDouble() * 100;
            double tinggiBangun = rand.nextDouble() * 100;

            // Object bangun datar
            JajaranGenjang jg =
                    new JajaranGenjang(
                            alas,
                            tinggi,
                            sisiMiring
                    );

            // Polymorphism
            BangunRuang bangun;

            String jenisBangun;

            // Menentukan jenis bangun
            if (i % 2 == 0) {

                bangun =
                        new LimasJajaranGenjang(
                                jg,
                                tinggiBangun
                        );

                jenisBangun = "LIMAS JAJARAN GENJANG";

            } else {

                bangun =
                        new PrismaJajaranGenjang(
                                jg,
                                tinggiBangun
                        );

                jenisBangun = "PRISMA JAJARAN GENJANG";
            }

            // OUTPUT
            System.out.println(
                    "\n[" + Thread.currentThread().getName()
                    + "] DATA KE-" + i
            );

            System.out.println(
                    "Jenis Bangun : "
                    + jenisBangun
            );

            System.out.println(
                    "Alas         : "
                    + alas
            );

            System.out.println(
                    "Tinggi       : "
                    + tinggi
            );

            System.out.println(
                    "Sisi Miring  : "
                    + sisiMiring
            );

            System.out.println(
                    "Tinggi Ruang : "
                    + tinggiBangun
            );

            System.out.println(
                    "Luas         : "
                    + jg.hitungLuas()
            );

            System.out.println(
                    "Keliling     : "
                    + jg.hitungKeliling()
            );

            System.out.println(
                    "Volume       : "
                    + bangun.hitungVolume()
            );

            System.out.println(
                    "======================================"
            );
        }

        System.out.println(
                "=== "
                + Thread.currentThread().getName()
                + " SELESAI ==="
        );
    }
}