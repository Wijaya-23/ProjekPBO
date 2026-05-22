package benda_geometri;

import java.util.List;
import java.util.concurrent.CountDownLatch;

// =======================
// LIMAS JAJARAN GENJANG
// =======================
class LimasJajaranGenjang extends BangunRuang implements Runnable {

    // =============================================
    // Field untuk perhitungan langsung
    // =============================================
    private JajaranGenjang alas;
    private double         tinggiLimas;

    // =============================================
    // Field untuk mode Thread
    // =============================================
    private List<DataJajaranGenjang> daftarData;
    private int            batasHitung;
    private CountDownLatch latch;
    private Thread         threadJajaranGenjang;

    // =============================================
    // CONSTRUCTOR 1 - untuk perhitungan langsung
    // =============================================
    public LimasJajaranGenjang(JajaranGenjang alas, double tinggiLimas) {
        this.alas        = alas;
        this.tinggiLimas = tinggiLimas;
    }

    // =============================================
    // CONSTRUCTOR 2 - untuk mode Thread
    // =============================================
    public LimasJajaranGenjang() { }

    // Getter - Encapsulation
    public double getTinggiLimas() { return tinggiLimas; }

    // Setter
    public void setThreadJajaranGenjang(Thread t)            { this.threadJajaranGenjang = t; }
    public void setDaftarData(List<DataJajaranGenjang> data) { this.daftarData = data; }
    public void setBatasHitung(int batas)                    { this.batasHitung = batas; }
    public void setLatch(CountDownLatch latch)               { this.latch = latch; }

    // Overriding: Luas permukaan limas
    @Override
    public double hitungLuas() {
        return alas.hitungLuas()
             + (alas.hitungKeliling() * tinggiLimas / 2.0);
    }

    // Overriding: Keliling alas
    @Override
    public double hitungKeliling() {
        return alas.hitungKeliling();
    }

    // Overriding: Volume limas
    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * alas.hitungLuas() * tinggiLimas;
    }

    // =============================================
    // RUN - logika thread Limas
    // =============================================
    @Override
    public void run() {
        System.out.println("\n  =======================================");
        System.out.println("    [LimasJajaranGenjang] DIINTERRUPT       ");
        System.out.println("    Menghitung " + batasHitung + " data pertama dari JG   ");
        System.out.println("  =========================================");

        for (int i = 0; i < batasHitung && i < daftarData.size(); i++) {

            DataJajaranGenjang data = daftarData.get(i);

            double luasPermukaan = data.luas + (data.keliling * data.tinggiBangun / 2.0);
            double volume        = (1.0 / 3.0) * data.luas * data.tinggiBangun;

            System.out.println("\n  [LimasJajaranGenjang] Data ke-" + data.nomor);
            System.out.println("  -- Data dari JajaranGenjang --");
            System.out.println("  Alas (a)             : " + String.format("%.2f", data.alas));
            System.out.println("  Tinggi (t)           : " + String.format("%.2f", data.tinggi));
            System.out.println("  Sisi Miring (s)      : " + String.format("%.2f", data.sisiMiring));
            System.out.println("  Luas 2D              : " + String.format("%.2f", data.luas));
            System.out.println("  Keliling 2D          : " + String.format("%.2f", data.keliling));
            System.out.println("  Tinggi Limas (T)     : " + String.format("%.2f", data.tinggiBangun));
            System.out.println("  -- Perhitungan Limas --");
            System.out.println("  Rumus Luas Permukaan : Luas + (Keliling x T / 2)");
            System.out.println("  Perhitungan          : " +
                String.format("%.2f", data.luas) + " + (" +
                String.format("%.2f", data.keliling) + " x " +
                String.format("%.2f", data.tinggiBangun) + " / 2)");
            System.out.println("  Hasil Luas Permukaan : " +
                String.format("%.2f", data.luas) + " + " +
                String.format("%.2f", data.keliling * data.tinggiBangun / 2.0) +
                " = " + String.format("%.2f", luasPermukaan));
            System.out.println("  Rumus Volume         : (1/3) x Luas x T");
            System.out.println("  Perhitungan          : (1/3) x " +
                String.format("%.2f", data.luas) + " x " +
                String.format("%.2f", data.tinggiBangun) +
                " = " + String.format("%.2f", volume));
            System.out.println("  ----------------------------------");
        }

        System.out.println("\n  [LimasJajaranGenjang] Selesai hitung " + batasHitung + " data");
        System.out.println("  >>> MENGINTERRUPT JajaranGenjang untuk lanjut siklus berikutnya");
        System.out.println("  =======================================");
        System.out.println("    [LimasJajaranGenjang Thread] SELESAI    ");
        System.out.println("  =======================================");

        // Beritahu JajaranGenjang bahwa Limas sudah selesai → lanjut siklus
        if (latch != null) latch.countDown();
    }
}
