package benda_geometri;

/**
 *
 * @author HP
 */
public class JajaranGenjang extends Bangun{
    public double alas;
    public double tinggi;
    public double luas;
    public double keliling;
    public double sisiMiring;
    
    public JajaranGenjang(double alas, double tinggi, double sisiMiring){
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
    }
    
    @Override
    public double hitungLuas(){
        luas = alas * tinggi;
        return luas;
    };
    
    @Override
    public double hitungKeliling(){
        keliling = 2 * (alas + sisiMiring);
        return keliling;
    }
}