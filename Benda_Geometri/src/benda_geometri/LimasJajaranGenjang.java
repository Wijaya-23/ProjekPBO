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
// LIMAS JAJARAN GENJANG
// =======================
class LimasJajaranGenjang extends BangunRuang {
    private JajaranGenjang alas;
    private double tinggiLimas;

    public LimasJajaranGenjang(JajaranGenjang alas, double tinggiLimas) {
        this.alas = alas;
        this.tinggiLimas = tinggiLimas;
    }

    
    public double hitungLuas() {
        return alas.hitungLuas(); // sederhana
    }

    @Override
    public double hitungKeliling() {
        return alas.hitungKeliling();
    }

   
    @Override
    public double hitungVolume() {
        return (1.0/3.0) * alas.hitungLuas() * tinggiLimas;
    }

   
    public double hitungKelling() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

