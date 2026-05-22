package benda_geometri;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

// =======================
// 2D: JAJARAN GENJANG
// =======================
public class JajaranGenjang extends Bangun implements Runnable {

    // =============================================
    // Field untuk objek perhitungan langsung
    // =============================================
    private double alas;
    private double tinggi;
    private double sisiMiring;

    // =============================================
    // Field untuk mode Thread massal
    // =============================================
    private int  totalDataTarget;
    private int  interruptSetiap;
    private int  totalDataDihasilkan = 0;

    // Daftar data hasil perhitungan satu siklus
    // Dibagikan ke Prisma dan Limas
    private List<DataJajaranGenjang> daftarData = new ArrayList<>();

    // Referensi thread dan objek
    private Thread               threadPrisma;
    private Thread               threadLimas;
    private PrismaJajaranGenjang objPrisma;
    private LimasJajaranGenjang  objLimas;

    // Latch: dipakai agar JajaranGenjang tahu kapan Prisma/Limas sudah selesai
    private CountDownLatch latchPrisma;
    private CountDownLatch latchLimas;

    // =============================================
    // CONSTRUCTOR 1 - Lengkap (3 parameter double)
    // Overloading: untuk buat objek perhitungan langsung (data genap → Limas)
    // =============================================
    public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
        this.alas       = alas;
        this.tinggi     = tinggi;
        this.sisiMiring = sisiMiring;
    }

    // =============================================
    // CONSTRUCTOR 2 - Overloading (alas, tinggi, Random)
    // sisiMiring di-generate otomatis, dijamin beda dari alas
    // =============================================
    public JajaranGenjang(double alas, double tinggi, Random rand) {
        this.alas       = alas;
        this.tinggi     = tinggi;
        this.sisiMiring = (rand.nextDouble() * (alas - 1)) + 1;
    }

    // =============================================
    // CONSTRUCTOR 3 - untuk mode Thread massal
    // =============================================
    public JajaranGenjang(int totalDataTarget, int interruptSetiap) {
        this.totalDataTarget = totalDataTarget;
        this.interruptSetiap = interruptSetiap;
    }

    // Getter - Encapsulation
    public double getAlas()                         { return alas; }
    public double getTinggi()                       { return tinggi; }
    public double getSisiMiring()                   { return sisiMiring; }
    public int    getTotalDataDihasilkan()          { return totalDataDihasilkan; }
    public List<DataJajaranGenjang> getDaftarData() { return daftarData; }

    // Setter referensi
    public void setThreadPrisma(Thread t)            { this.threadPrisma = t; }
    public void setThreadLimas(Thread t)             { this.threadLimas  = t; }
    public void setObjPrisma(PrismaJajaranGenjang p) { this.objPrisma    = p; }
    public void setObjLimas(LimasJajaranGenjang l)   { this.objLimas     = l; }

    // Overriding dari Bangun
    @Override
    public double hitungLuas() {
        return alas * tinggi;
    }

    // Overriding dari Bangun
    @Override
    public double hitungKeliling() {
        return 2 * (alas + sisiMiring);
    }

    // =============================================
    // RUN - logika thread JajaranGenjang
    // =============================================
    @Override
    public void run() {
        Random rand   = new Random();
        int    siklus = 0;

        System.out.println("=======================================");
        System.out.println("      [JajaranGenjang Thread] MULAI       ");
        System.out.println("=======================================");

        while (totalDataDihasilkan < totalDataTarget) {

            siklus++;
            daftarData.clear();

            System.out.println("\n==========================================");
            System.out.println("  SIKLUS KE-" + siklus +
                " | Total data sejauh ini: " + totalDataDihasilkan);
            System.out.println("==========================================");

            // ─── Generate interruptSetiap data (15 per siklus) ───
            for (int i = 1; i <= interruptSetiap; i++) {

                if (totalDataDihasilkan >= totalDataTarget) break;

                totalDataDihasilkan++;
                int nomorGlobal = totalDataDihasilkan;

                double nilaiAlas, nilaiTinggi, nilaiSisiMiring;
                double tinggiBangun = rand.nextDouble() * 100;
                String jenisConstructor;
                JajaranGenjang jg;

                if (nomorGlobal % 2 == 0) {
                    // Data GENAP → Constructor LENGKAP (3 parameter double)
                    nilaiAlas        = rand.nextDouble() * 100;
                    nilaiTinggi      = rand.nextDouble() * 100;
                    nilaiSisiMiring  = rand.nextDouble() * 100;
                    jg               = new JajaranGenjang(nilaiAlas, nilaiTinggi, nilaiSisiMiring);
                    jenisConstructor = "Lengkap (3 parameter)";
                } else {
                    // Data GANJIL → Constructor OVERLOADING (alas, tinggi, Random)
                    nilaiAlas        = rand.nextDouble() * 100;
                    nilaiTinggi      = rand.nextDouble() * 100;
                    jg               = new JajaranGenjang(nilaiAlas, nilaiTinggi, rand);
                    nilaiSisiMiring  = jg.getSisiMiring();
                    jenisConstructor = "Overloading (sisiMiring otomatis)";
                }

                double nilaiLuas     = jg.hitungLuas();
                double nilaiKeliling = jg.hitungKeliling();

                // Simpan ke daftar data siklus ini
                daftarData.add(new DataJajaranGenjang(
                    nomorGlobal, nilaiAlas, nilaiTinggi, nilaiSisiMiring,
                    nilaiLuas, nilaiKeliling, tinggiBangun, jenisConstructor
                ));

                // Cetak hasil 2D
                System.out.println("\n  [JajaranGenjang] Data ke-" + nomorGlobal);
                System.out.println("  Constructor      : " + jenisConstructor);
                System.out.println("  Alas (a)         : " + String.format("%.2f", nilaiAlas));
                System.out.println("  Tinggi (t)       : " + String.format("%.2f", nilaiTinggi));
                System.out.println("  Sisi Miring (s)  : " + String.format("%.2f", nilaiSisiMiring));
                System.out.println("  Tinggi 3D (T)    : " + String.format("%.2f", tinggiBangun));
                System.out.println("  Rumus Luas       : a x t");
                System.out.println("  Hitung Luas      : " +
                    String.format("%.2f", nilaiAlas) + " x " +
                    String.format("%.2f", nilaiTinggi) + " = " +
                    String.format("%.2f", nilaiLuas));
                System.out.println("  Rumus Keliling   : 2 x (a + s)");
                System.out.println("  Hitung Keliling  : 2 x (" +
                    String.format("%.2f", nilaiAlas) + " + " +
                    String.format("%.2f", nilaiSisiMiring) + ") = " +
                    String.format("%.2f", nilaiKeliling));
                System.out.println("  --------------------------------");
            }

            // ─── Siklus selesai → handoff ke Prisma lalu Limas ───
            int jumlahSiklus = daftarData.size();
            int batasPrisma  = Math.min(8, jumlahSiklus);
            int batasLimas   = Math.min(5, jumlahSiklus);

            System.out.println("\n  [JajaranGenjang] " + jumlahSiklus +
                " data selesai -> INTERRUPT PrismaJajaranGenjang");

            // Siapkan latch: JajaranGenjang tunggu Prisma selesai
            latchPrisma = new CountDownLatch(1);
            objPrisma.setBatasHitung(batasPrisma);
            objPrisma.setDaftarData(new ArrayList<>(daftarData));
            objPrisma.setLatch(latchPrisma);

            // Jalankan thread Prisma
            threadPrisma = new Thread(objPrisma, "Prisma-Thread-S" + siklus);
            threadPrisma.start();

            // Tunggu Prisma selesai
            try { latchPrisma.await(); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("\n  [JajaranGenjang] Prisma selesai → INTERRUPT LimasJajaranGenjang");

            // Siapkan latch: JajaranGenjang tunggu Limas selesai
            latchLimas = new CountDownLatch(1);
            objLimas.setBatasHitung(batasLimas);
            objLimas.setDaftarData(new ArrayList<>(daftarData));
            objLimas.setLatch(latchLimas);

            // Jalankan thread Limas
            threadLimas = new Thread(objLimas, "Limas-Thread-S" + siklus);
            threadLimas.start();

            // Tunggu Limas selesai
            try { latchLimas.await(); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("\n  [LimasJajaranGenjang] Selesai → INTERRUPT JajaranGenjang untuk lanjut");
            System.out.println("  [JajaranGenjang] Menerima sinyal lanjut dari Limas ✓");
        }

        System.out.println("\n=====================================");
        System.out.println("  [JajaranGenjang Thread] SELESAI          ");
        System.out.println("  Total data: " + totalDataDihasilkan + "                       ");
        System.out.println("=======================================");
    }
}
