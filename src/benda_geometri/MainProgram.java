package benda_geometri;

public class MainProgram {

    public static void main(String[] args) {

        final int TOTAL_DATA     = 10000; // target minimal 10.000 data
        final int INTERRUPT_TIAP = 15;    // JajaranGenjang di-interrupt tiap 15 data

        // =============================================
        // Buat objek dari 3 class utama
        // =============================================
        JajaranGenjang       objJG     = new JajaranGenjang(TOTAL_DATA, INTERRUPT_TIAP);
        PrismaJajaranGenjang objPrisma = new PrismaJajaranGenjang();
        LimasJajaranGenjang  objLimas  = new LimasJajaranGenjang();

        // =============================================
        // Daftarkan referensi objek ke JajaranGenjang
        // agar bisa mengatur Prisma dan Limas dari dalam siklus
        // =============================================
        objJG.setObjPrisma(objPrisma);
        objJG.setObjLimas(objLimas);

        // =============================================
        // Buat dan jalankan thread JajaranGenjang
        // Prisma & Limas dibuat ulang tiap siklus di dalam JajaranGenjang
        // =============================================
        Thread threadJG = new Thread(objJG, "JajaranGenjang-Thread");

        System.out.println("===========================================");
        System.out.println("  PROGRAM GEOMETRI - OOP & MULTITHREADING   ");
        System.out.println("  Target data  : " + TOTAL_DATA + "                       ");
        System.out.println("  Interrupt    : setiap " + INTERRUPT_TIAP + " data             ");
        System.out.println("  Prisma hitung: 8 data pertama per siklus  ");
        System.out.println("  Limas hitung : 5 data pertama per siklus  ");
        System.out.println("============================================\n");

        threadJG.start();

        try {
            threadJG.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=======================================");
        System.out.println("          SEMUA THREAD SELESAI               ");
        System.out.println("=========================================");
    }
}
