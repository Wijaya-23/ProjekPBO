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
                // Constructor LENGKAP (3 parameter) untuk Limas
                jg             = new JajaranGenjang(alas, tinggi, sisiMiring);
                bangun         = new LimasJajaranGenjang(jg, tinggiBangun);
                jenisBangun    = "LIMAS JAJARAN GENJANG";
                jenisConstructor = "Lengkap (3 parameter)";
            } else {
                // Constructor OVERLOADING (alas, tinggi, random) bilangan random tidak dipakai untuk perhitungan
                // sisiMiring otomatis = alas, tidak perlu diisi manual
                jg             = new JajaranGenjang(alas, tinggi, rand);
                bangun         = new PrismaJajaranGenjang(jg, tinggiBangun);
                jenisBangun    = "PRISMA JAJARAN GENJANG";
                jenisConstructor = "Overloading (sisiMiring digenerate otomatis)";
            }
            
            //Ambil nilai dari getter
            double nilaiAlas = jg.getAlas();
            double nilaiTinggi = jg.getTinggi();
            double nilaiSisiMiring = jg.getSisiMiring();
            double nilaiLuasDatar = jg.hitungLuas();
            double nilaiKeliling = jg.hitungKeliling();

            System.out.println(
                "\n[" + Thread.currentThread().getName() + "] DATA KE-" + i
            );
            System.out.println("Jenis Bangun      : " + jenisBangun);
            System.out.println("Constructor       : " + jenisConstructor);

            // =============================================
            // BAGIAN 1 - DATA MASUKAN
            // =============================================
            System.out.println("\n  [DATA MASUKAN]");
            System.out.println("  Alas (a)          : " + String.format("%.2f", nilaiAlas));
            System.out.println("  Tinggi datar (t)  : " + String.format("%.2f", nilaiTinggi));
            System.out.println("  Sisi Miring (s)   : " + String.format("%.2f", nilaiSisiMiring));
            System.out.println("  Tinggi 3D (T)     : " + String.format("%.2f", tinggiBangun));

            // =============================================
            // BAGIAN 2 - JAJARAN GENJANG 2D (Overriding)
            // =============================================
            System.out.println("\n  [JAJARAN GENJANG - 2D] (Overriding)");

            System.out.println("  Rumus Luas        : a x t");
            System.out.println("  Perhitungan       : " +
                String.format("%.2f", nilaiAlas) + " x " +
                String.format("%.2f", nilaiTinggi) + " = " +
                String.format("%.2f", nilaiLuasDatar));

            System.out.println("  Rumus Keliling    : 2 x (a + s)");
            System.out.println("  Perhitungan       : 2 x (" +
                String.format("%.2f", nilaiAlas) + " + " +
                String.format("%.2f", nilaiSisiMiring) + ") = " +
                String.format("%.2f", nilaiKeliling));

            // =============================================
            // BAGIAN 3 - BANGUN RUANG 3D (Polymorphism)
            // =============================================
            System.out.println("\n  [" + jenisBangun + " - 3D] (Polymorphism)");

            if (jenisBangun.equals("PRISMA JAJARAN GENJANG")) {

                double luasPermukaan = bangun.hitungLuas();
                double volume        = bangun.hitungVolume();

                System.out.println("  Rumus Luas Permukaan : (2 x Luas Alas) + (Keliling x T)");
                System.out.println("  Perhitungan          : (2 x " +
                    String.format("%.2f", nilaiLuasDatar) + ") + (" +
                    String.format("%.2f", nilaiKeliling) + " x " +
                    String.format("%.2f", tinggiBangun) + ")");
                System.out.println("  Hasil                : " +
                    String.format("%.2f", 2 * nilaiLuasDatar) + " + " +
                    String.format("%.2f", nilaiKeliling * tinggiBangun) + " = " +
                    String.format("%.2f", luasPermukaan));

                System.out.println("  Rumus Volume         : Luas Alas x T");
                System.out.println("  Perhitungan          : " +
                    String.format("%.2f", nilaiLuasDatar) + " x " +
                    String.format("%.2f", tinggiBangun) + " = " +
                    String.format("%.2f", volume));

            } else {

                double luasPermukaan = bangun.hitungLuas();
                double volume        = bangun.hitungVolume();

                System.out.println("  Rumus Luas Permukaan : Luas Alas + (Keliling x T / 2)");
                System.out.println("  Perhitungan          : " +
                    String.format("%.2f", nilaiLuasDatar) + " + (" +
                    String.format("%.2f", nilaiKeliling) + " x " +
                    String.format("%.2f", tinggiBangun) + " / 2)");
                System.out.println("  Hasil                : " +
                    String.format("%.2f", nilaiLuasDatar) + " + " +
                    String.format("%.2f", nilaiKeliling * tinggiBangun / 2.0) + " = " +
                    String.format("%.2f", luasPermukaan));

                System.out.println("  Rumus Volume         : (1/3) x Luas Alas x T");
                System.out.println("  Perhitungan          : (1/3) x " +
                    String.format("%.2f", nilaiLuasDatar) + " x " +
                    String.format("%.2f", tinggiBangun) + " = " +
                    String.format("%.2f", volume));
            }

            System.out.println("  Keliling Alas        : " +
                String.format("%.2f", bangun.hitungKeliling()));

            System.out.println("\n======================================");
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
    
