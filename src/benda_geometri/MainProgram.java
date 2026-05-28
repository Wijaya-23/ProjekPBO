package benda_geometri;
import java.util.Random;
import java.util.Scanner;

public class MainProgram {
    //variabel untuk merekam progress tiap thread
    private static volatile int progressJG = 0;
    private static volatile int progressPrisma = 0;
    private static volatile int progressLimas = 0;
    
    private static boolean isFirstPrint = true; 

    public static void main(String[] args) {
        int jumlahData = 10000;
        
        JajaranGenjang[] daftarJG = new JajaranGenjang[jumlahData];
        PrismaJajaranGenjang[] daftarPrisma = new PrismaJajaranGenjang[jumlahData];
        LimasJajaranGenjang[] daftarLimas = new LimasJajaranGenjang[jumlahData];
        Random acak = new Random();
        
        System.out.println("=== MEMULAI PROSES ===\n");

        //thread JajaranGenjang
        Thread threadJG = new Thread(() -> {
            for (int i = 0; i < jumlahData; i++) {
                double randAlas = acak.nextInt(100) + 1;
                double randTinggi = acak.nextInt(100) + 1;
                double randSisiMiring = acak.nextInt(100) + 1;
                
                daftarJG[i] = new JajaranGenjang(randAlas, randTinggi, randSisiMiring);
                daftarJG[i].hitungLuas();
                daftarJG[i].hitungKeliling();
                
                progressJG = i + 1;
                cetakProgressCMD(jumlahData);
                
                //memberikan delay acak (0-3 milidetik) agar pergerakannya bisa dilihat mata
                try { Thread.sleep(acak.nextInt(4)); } catch (Exception e) {}
            }
        });

        //thread PrismaJajaranGenjang
        Thread threadPrisma = new Thread(() -> {
            for (int i = 0; i < jumlahData; i++) {
                //prisma menunggu JG
                while (i >= progressJG) {
                    try { Thread.sleep(1); } catch (Exception e) {}
                }
                
                double randTinggiPrisma = acak.nextInt(100) + 1;
                daftarPrisma[i] = new PrismaJajaranGenjang(randTinggiPrisma, 0, daftarJG[i]);
                daftarPrisma[i].hitungLPprisma();
                daftarPrisma[i].hitungVolume();
                
                progressPrisma = i + 1;
                cetakProgressCMD(jumlahData);
                
                //delay acak Prisma (0-4 milidetik)
                try { Thread.sleep(acak.nextInt(5)); } catch (Exception e) {}
            }
        });

        //thread LimasJajaranGenjang
        Thread threadLimas = new Thread(() -> {
            for (int i = 0; i < jumlahData; i++) {
                //Limas menunggu data JG
                while (i >= progressJG) {
                    try { Thread.sleep(1); } catch (Exception e) {}
                }
                
                double randTinggiLimas = acak.nextInt(100) + 1;
                double randT1 = acak.nextInt(100) + 1;
                double randT2 = acak.nextInt(100) + 1;
                
                daftarLimas[i] = new LimasJajaranGenjang(randTinggiLimas, 0, randT1, randT2, daftarJG[i]);
                daftarLimas[i].hitungLPlimas();
                daftarLimas[i].hitungVolume();
                
                progressLimas = i + 1;
                cetakProgressCMD(jumlahData);
                
                //delay acak Limas (0-5 milidetik)
                try { Thread.sleep(acak.nextInt(6)); } catch (Exception e) {}
            }
        });

        threadJG.start();
        threadPrisma.start();
        threadLimas.start();

        try {
            threadJG.join();
            threadPrisma.join();
            threadLimas.join();
        } catch (InterruptedException e) {}
        
        System.out.println("\n\n=== BERHASIL: 10.000 DATA SELESAI DIPROSES ===");
        
        System.out.println("\n=== MENAMPILKAN RINCIAN HASIL DATA ===");
        Scanner scanner = new Scanner(System.in);
        
        
        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\n--- DATA URUTAN KE-" + (i + 1) + " ---");
            
            System.out.println("[JAJARAN GENJANG]");
            System.out.println("Alas         : " + daftarJG[i].alas);
            System.out.println("Tinggi       : " + daftarJG[i].tinggi);
            System.out.println("Luas         : " + daftarJG[i].hitungLuas());
            System.out.println("Keliling     : " + daftarJG[i].hitungKeliling());
            
            System.out.println("\n[PRISMA]");
            System.out.println("Tinggi Prisma: " + daftarPrisma[i].tinggiPrisma);
            System.out.println("L. Permukaan : " + daftarPrisma[i].hitungLPprisma());
            System.out.println("Volume       : " + daftarPrisma[i].hitungVolume());
            
            System.out.println("\n[LIMAS]");
            System.out.println("Tinggi Limas : " + daftarLimas[i].tinggiLimas);
            System.out.println("L. Permukaan : " + daftarLimas[i].hitungLPlimas());
            System.out.println("Volume       : " + daftarLimas[i].hitungVolume());
            
            if ((i + 1) % 20 == 0) {
                System.out.print("--- Tekan ENTER untuk melihat 20 data berikutnya ---");
                scanner.nextLine();
            }
        }
        
    }
    
    //method cetak khusus untuk cmd
    public static synchronized void cetakProgressCMD(int total) {
        //jika bukan cetakan pertama, tarik kursor naik 3 baris ke atas untuk menimpa teks lama
        if (!isFirstPrint) {
            System.out.print("\033[3A"); 
        } else {
            isFirstPrint = false;
        }

        //cetak bersusun ke bawah
        System.out.println("proses penghitungan JajaranGenjang       = |" + getProgressBar(progressJG, total, 'c') + "| (" + progressJG + " data)");
        System.out.println("proses penghitungan PrismaJajaranGenjang = |" + getProgressBar(progressPrisma, total, 'p') + "| (" + progressPrisma + " data)");
        System.out.println("proses penghitungan LimasJajaranGenjang  = |" + getProgressBar(progressLimas, total, 'l') + "| (" + progressLimas + " data)");
    }

    //pembuat karakter animasi
    public static String getProgressBar(int current, int total, char ch) {
        int barLength = 20; 
        int progress = (int) (((double) current / total) * barLength);
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < progress) bar.append(ch); 
            else bar.append(" ");
        }
        return bar.toString();
    }
}