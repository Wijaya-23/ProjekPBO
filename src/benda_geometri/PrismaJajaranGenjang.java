package benda_geometri;

import java.util.List;
import java.util.concurrent.CountDownLatch;

// =======================
// PRISMA JAJARAN GENJANG
// =======================
public class PrismaJajaranGenjang extends BangunRuang implements Runnable {

    // =============================================
    // Field untuk perhitungan langsung
    // =============================================
    private JajaranGenjang alas;
    private double         tinggiPrisma;

    // =============================================
    // Field untuk mode Thread
    // =============================================
    private List<DataJajaranGenjang> daftarData;
    private int             batasHitung;
    private CountDownLatch  latch;
    private Thread          threadJajaranGenjang;

    // =============================================
    // CONSTRUCTOR 1 - untuk perhitungan langsung
    // =============================================
    public PrismaJajaranGenjang(JajaranGenjang alas, double tinggiPrisma) {
        this.alas         = alas;
        this.tinggiPrisma = tinggiPrisma;
    }

    // =============================================
    // CONSTRUCTOR 2 - untuk mode Thread
    // =============================================
    public PrismaJajaranGenjang() { }

    // Getter - Encapsulation
    public double getTinggiPrisma() { return tinggiPrisma; }

    // Setter
    public void setThreadJajaranGenjang(Thread t)            { this.threadJajaranGenjang = t; }
    public void setDaftarData(List<DataJajaranGenjang> data) { this.daftarData = data; }
    public void setBatasHitung(int batas)                    { this.batasHitung = batas; }
    public void setLatch(CountDownLatch latch)               { this.latch = latch; }

    // Overriding: Luas permukaan prisma
    @Override
    public double hitungLuas() {
        return (2 * alas.hitungLuas())
             + (alas.hitungKeliling() * tinggiPrisma);
    }

    // Overriding: Keliling alas
    @Override
    public double hitungKeliling() {
        return alas.hitungKeliling();
    }

    // Overriding: Volume prisma
    @Override
    public double hitungVolume() {
        return alas.hitungLuas() * tinggiPrisma;
    }

    // =============================================
    // RUN - logika thread Prisma
    // =============================================
    @Override
    public void run() {
        System.out.println("\n  =======================================");
        System.out.println("    [PrismaJajaranGenjang] DIINTERRUPT      ");
        System.out.println("    Menghitung " + batasHitung + " data pertama dari JG   ");
        System.out.println("  ========================================");

        for (int i = 0; i < batasHitung && i < daftarData.size(); i++) {

            DataJajaranGenjang data = daftarData.get(i);

            double luasPermukaan = (2 * data.luas) + (data.keliling * data.tinggiBangun);
            double volume        = data.luas * data.tinggiBangun;

            System.out.println("\n  [PrismaJajaranGenjang] Data ke-" + data.nomor);
            System.out.println("  -- Data dari JajaranGenjang --");
            System.out.println("  Alas (a)             : " + String.format("%.2f", data.alas));
            System.out.println("  Tinggi (t)           : " + String.format("%.2f", data.tinggi));
            System.out.println("  Sisi Miring (s)      : " + String.format("%.2f", data.sisiMiring));
            System.out.println("  Luas 2D              : " + String.format("%.2f", data.luas));
            System.out.println("  Keliling 2D          : " + String.format("%.2f", data.keliling));
            System.out.println("  Tinggi Prisma (T)    : " + String.format("%.2f", data.tinggiBangun));
            System.out.println("  -- Perhitungan Prisma --");
            System.out.println("  Rumus Luas Permukaan : (2 x Luas) + (Keliling x T)");
            System.out.println("  Perhitungan          : (2 x " +
                String.format("%.2f", data.luas) + ") + (" +
                String.format("%.2f", data.keliling) + " x " +
                String.format("%.2f", data.tinggiBangun) + ")");
            System.out.println("  Hasil Luas Permukaan : " +
                String.format("%.2f", 2 * data.luas) + " + " +
                String.format("%.2f", data.keliling * data.tinggiBangun) +
                " = " + String.format("%.2f", luasPermukaan));
            System.out.println("  Rumus Volume         : Luas x T");
            System.out.println("  Perhitungan          : " +
                String.format("%.2f", data.luas) + " x " +
                String.format("%.2f", data.tinggiBangun) +
                " = " + String.format("%.2f", volume));
            System.out.println("  ---------------------------------");
        }

        System.out.println("\n  [PrismaJajaranGenjang] Selesai hitung " + batasHitung + " data");
        System.out.println("  =======================================");
        System.out.println("    [PrismaJajaranGenjang Thread] SELESAI   ");
        System.out.println("  =======================================");

        // Beritahu JajaranGenjang bahwa Prisma sudah selesai
        if (latch != null) latch.countDown();
    }
}
