/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package benda_geometri;

/**
 *
 * @author LENOVOJOJO007
 */
    // =======================
// 2D: JAJARAN GENJANG
// =======================
public class JajaranGenjang extends Bangun {

    private double alas;
    private double tinggi;
    private double sisiMiring;

    public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
    }

    public JajaranGenjang(double alas, double tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = alas; // sisi miring diasumsikan sama dengan alas
    }

    // Getter - Encapsulation
    public double getAlas()       { return alas; }
    public double getTinggi()     { return tinggi; }
    public double getSisiMiring() { return sisiMiring; }
    
    @Override
    public double hitungLuas() {
        return alas * tinggi;
    }
    
    @Override
    public double hitungKeliling() {
        return 2 * (alas + sisiMiring);
    }
    
}

