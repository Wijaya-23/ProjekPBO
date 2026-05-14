/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package benda_geometri;

/**
 *
 * @author LENOVOJOJO007
 */

public class PrismaJajaranGenjang extends BangunRuang {

    private JajaranGenjang alas;
    private double tinggiPrisma;

    public PrismaJajaranGenjang(
            JajaranGenjang alas,
            double tinggiPrisma) {

        this.alas = alas;
        this.tinggiPrisma = tinggiPrisma;
    }

    public double getTinggiPrisma() {return tinggiPrisma;}
    
    @Override
    public double hitungLuas() {
        return (2 * alas.hitungLuas())
                + (alas.hitungKeliling() * tinggiPrisma);
    }

    @Override
    public double hitungKeliling() {
        return alas.hitungKeliling();
    }

   
    @Override
    public double hitungVolume() {
        return alas.hitungLuas() * tinggiPrisma;
    }
}