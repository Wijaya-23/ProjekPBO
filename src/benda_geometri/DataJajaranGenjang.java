package benda_geometri;

// =============================================
// Kelas untuk menyimpan satu data JajaranGenjang
// Dipakai sebagai paket data yang dikirim ke Prisma & Limas
// =============================================
public class DataJajaranGenjang {
    public final int    nomor;
    public final double alas;
    public final double tinggi;
    public final double sisiMiring;
    public final double luas;
    public final double keliling;
    public final double tinggiBangun;
    public final String jenisConstructor;

    public DataJajaranGenjang(int nomor, double alas, double tinggi,
                               double sisiMiring, double luas, double keliling,
                               double tinggiBangun, String jenisConstructor) {
        this.nomor           = nomor;
        this.alas            = alas;
        this.tinggi          = tinggi;
        this.sisiMiring      = sisiMiring;
        this.luas            = luas;
        this.keliling        = keliling;
        this.tinggiBangun    = tinggiBangun;
        this.jenisConstructor = jenisConstructor;
    }
}
