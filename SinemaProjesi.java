import java.util.Scanner;

public class SinemaProjesi {

    static String[] filmİsimleri = new String[10];
    static int[] filmSüreleri = new int[10];
    static String[] filmTürleri = new String[10];
    static int filmSayısi = 0;

    static String[] musteriIsimleri = new String[15];
    static String[] musteriEpostalar = new String[15];
    static int musteriSayisi = 0;

    static int[][] biletler = new int[15][10];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secim;

        do {
            anaMenu();
            secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1 -> filmEkle();
                case 2 -> musteriEkle();
                case 3 -> biletSatinAl();
                case 4 -> filmListesi();
                case 5 -> musteriListesi();
                case 6 -> biletListesi();
                case 7 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        } while (secim != 7);
    }

    static void anaMenu() {
        System.out.println("""
                \n--- ANA MENÜ ---
                1. Film Ekle
                2. Müşteri Ekle
                3. Bilet Satın Al
                4. Film Listesi
                5. Müşteri Listesi
                6. Bilet Listesi
                7. Çıkış
                Seçiminiz: """);
    }

    static void filmEkle() {
        if (filmSayısi >= 10) {
            System.out.println("Film listesi dolu.");
            return;
        }

        System.out.print("Film Adı: ");
        filmİsimleri[filmSayısi] = scanner.nextLine();

        System.out.print("Film Süresi (dakika): ");
        filmSüreleri[filmSayısi] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Film Türü: ");
        filmTürleri[filmSayısi] = scanner.nextLine();

        filmSayısi++;
        System.out.println("Film eklendi!");
    }

    static void musteriEkle() {
        if (musteriSayisi >= 15) {
            System.out.println("Müşteri listesi dolu.");
            return;
        }

        System.out.print("Müşteri Adı: ");
        musteriIsimleri[musteriSayisi] = scanner.nextLine();

        System.out.print("Müşteri E-Posta: ");
        musteriEpostalar[musteriSayisi] = scanner.nextLine();

        musteriSayisi++;
        System.out.println("Müşteri eklendi.");
    }

    static void filmListesi() {
        if (filmSayısi == 0) {
            System.out.println("Kayıtlı film yok.");
            return;
        }

        System.out.println("\n--- FİLMLER ---");
        for (int i = 0; i < filmSayısi; i++) {
            System.out.printf("%d. %s | Süre: %d dk | Tür: %s%n", i + 1, filmİsimleri[i], filmSüreleri[i], filmTürleri[i]);
        }
    }

    static void musteriListesi() {
        if (musteriSayisi == 0) {
            System.out.println("Kayıtlı müşteri yok.");
            return;
        }

        System.out.println("\n--- MÜŞTERİLER ---");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.printf("%d. %s | E-Posta: %s%n", i + 1, musteriIsimleri[i], musteriEpostalar[i]);
        }
    }

    static void biletSatinAl() {
        if (filmSayısi == 0 || musteriSayisi == 0) {
            System.out.println("Bilet satışı için önce film ve müşteri eklenmelidir.");
            return;
        }

        filmListesi();
        System.out.print("Film Numarası: ");
        int filmNo = scanner.nextInt();
        scanner.nextLine();

        if (filmNo < 1 || filmNo > filmSayısi) {
            System.out.println("Geçersiz film numarası.");
            return;
        }

        musteriListesi();
        System.out.print("Müşteri Numarası: ");
        int musteriNo = scanner.nextInt();
        scanner.nextLine();

        if (musteriNo < 1 || musteriNo > musteriSayisi) {
            System.out.println("Geçersiz müşteri numarası.");
            return;
        }

        biletler[musteriNo - 1][filmNo - 1] = 1;
        System.out.println("Bilet başarıyla alındı!");
    }

    static void biletListesi() {
        System.out.println("\n--- BİLET LİSTESİ ---");

        for (int i = 0; i < musteriSayisi; i++) {
            System.out.printf("%s (%s): ", musteriIsimleri[i], musteriEpostalar[i]);
            boolean biletVar = false;

            for (int j = 0; j < filmSayısi; j++) {
                if (biletler[i][j] == 1) {
                    System.out.print(filmİsimleri[j] + ", ");
                    biletVar = true;
                }
            }

            if (!biletVar) {
                System.out.print("Bilet yok");
            }
            System.out.println();
        }
    }
}
