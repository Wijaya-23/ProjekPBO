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
            
            
            BangunRuang bangun;
            JajaranGenjang jg;
            String jenisBangun;
            String jenisConstructor;

            if (i % 2 == 0) {
                // Constructor LENGKAP (3 parameter) → untuk Limas
                jg             = new JajaranGenjang(alas, tinggi, sisiMiring);
                bangun         = new LimasJajaranGenjang(jg, tinggiBangun);
                jenisBangun    = "LIMAS JAJARAN GENJANG";
                jenisConstructor = "Lengkap (3 parameter)";
            } else {
                // Constructor OVERLOADING (2 parameter) → untuk Prisma
                // sisiMiring otomatis = alas, tidak perlu diisi manual
                jg             = new JajaranGenjang(alas, tinggi);
                bangun         = new PrismaJajaranGenjang(jg, tinggiBangun);
                jenisBangun    = "PRISMA JAJARAN GENJANG";
                jenisConstructor = "Overloading (2 parameter, sisiMiring = alas)";
            }

            System.out.println(
                "\n[" + Thread.currentThread().getName() + "] DATA KE-" + i
            );
            System.out.println("Jenis Bangun      : " + jenisBangun);
            System.out.println("Constructor       : " + jenisConstructor);
            System.out.println("Alas              : " + String.format("%.2f", alas));
            System.out.println("Tinggi (datar)    : " + String.format("%.2f", tinggi));
            System.out.println("Sisi Miring       : " + String.format("%.2f", jg.getSisiMiring()));
            System.out.println("Tinggi Bangun 3D  : " + String.format("%.2f", tinggiBangun));

            // Hasil bangun datar - Overriding
            System.out.println("-- Jajaran Genjang (2D) --");
            System.out.println("   Luas Datar     : " + String.format("%.2f", jg.hitungLuas()));
            System.out.println("   Keliling Datar : " + String.format("%.2f", jg.hitungKeliling()));

            // Hasil bangun ruang - Polymorphism + Overriding
            System.out.println("-- Bangun Ruang (3D) --");
            System.out.println("   Luas Permukaan : " + String.format("%.2f", bangun.hitungLuas()));
            System.out.println("   Keliling Alas  : " + String.format("%.2f", bangun.hitungKeliling()));
            System.out.println("   Volume         : " + String.format("%.2f", bangun.hitungVolume()));
            System.out.println("======================================");
        }

        System.out.println(
            "=== " + Thread.currentThread().getName() + " SELESAI ==="
        );
    }
}

            // Object bangun datar
            /*JajaranGenjang jg =
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
        );*/
    }
}