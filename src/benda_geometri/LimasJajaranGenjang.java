package benda_geometri;

/**
 *
 * @author HP
 */
public class LimasJajaranGenjang extends BangunRuang{
    public double tinggiLimas;
    public double LPlimas;
    public double tinggiSegitigaDepanBelakang;
    public double tinggiSegitigaKiriKanan;
    public JajaranGenjang alasLimas;
    
    public LimasJajaranGenjang(double tinggiLimas, double LPlimas, double tinggiSegitiga1, double tinggiSegitiga2, JajaranGenjang alasLimas){
        this.tinggiLimas = tinggiLimas;
        this.LPlimas = LPlimas;
        this.tinggiSegitigaDepanBelakang = tinggiSegitiga1;
        this.tinggiSegitigaKiriKanan = tinggiSegitiga2;
        this.alasLimas = alasLimas;
    }
    
    public double hitungLPlimas(){
        LPlimas = alasLimas.hitungLuas() + (2 * (1.0/2.0) * alasLimas.alas * tinggiSegitigaDepanBelakang) + (2 * (1.0/2.0) * alasLimas.sisiMiring * tinggiSegitigaKiriKanan);
        return LPlimas;
    }
    
    @Override
    public double hitungVolume(){
        return (1.0/3.0) * alasLimas.hitungLuas() * tinggiLimas;
    }
}