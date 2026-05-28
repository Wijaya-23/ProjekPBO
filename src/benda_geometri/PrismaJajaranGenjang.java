package benda_geometri;

/**
 *
 * @author HP
 */
public class PrismaJajaranGenjang extends BangunRuang{
    public double tinggiPrisma;
    public double LPprisma;
    public JajaranGenjang alasPrisma;
    
    public PrismaJajaranGenjang(double tinggiPrisma, double LPprisma, JajaranGenjang alasPrisma){
        this.tinggiPrisma = tinggiPrisma;
        this.LPprisma = LPprisma;
        this.alasPrisma = alasPrisma;
    }
    
    public double hitungLPprisma(){
        LPprisma = (2 * alasPrisma.hitungLuas()) + (alasPrisma.hitungKeliling() * tinggiPrisma);
        return LPprisma;
    }
    
    @Override 
    public double hitungVolume(){
        return alasPrisma.hitungLuas() * tinggiPrisma;
    }
}