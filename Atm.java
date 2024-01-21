import java.util.HashMap;
import java.util.Map;

public class Atm {
    private int atmNo;
    private Map<String, Double> accountBalances;

    public Atm(int atmNo) {
        this.atmNo = atmNo;
        this.accountBalances = new HashMap<>();
    }

    public void paraYatir(String hesapNo, double miktar) {
        if (kayitKontrol(hesapNo)) {
            if (miktar > 0) {
                double mevcutBakiye = accountBalances.getOrDefault(hesapNo, 0.0);
                accountBalances.put(hesapNo, mevcutBakiye + miktar);
                System.out.println(hesapNo + " hesabına " + miktar + " TL yatırıldı. Yeni bakiye: " + accountBalances.get(hesapNo));
            } else {
                System.out.println("Geçersiz miktar. Lütfen pozitif bir miktar girin.");
            }
        } else {
            System.out.println("Hesap bulunamadı veya şifre yanlış. Lütfen geçerli bir hesap numarası ve şifre girin.");
        }
    }

    public void paraCek(String hesapNo, double miktar) {
        if (kayitKontrol(hesapNo)) {
            if (miktar > 0) {
                if (accountBalances.containsKey(hesapNo)) {
                    double mevcutBakiye = accountBalances.get(hesapNo);
                    if (mevcutBakiye >= miktar) {
                        accountBalances.put(hesapNo, mevcutBakiye - miktar);
                        System.out.println(hesapNo + " hesabından " + miktar + " TL çekildi. Yeni bakiye: " + accountBalances.get(hesapNo));
                    } else {
                        System.out.println("Yetersiz bakiye. Çekilecek miktar mevcut bakiyeyi aşmaktadır.");
                    }
                } else {
                    System.out.println("Hesap bulunamadı. Lütfen geçerli bir hesap numarası girin.");
                }
            } else {
                System.out.println("Geçersiz miktar. Lütfen pozitif bir miktar girin.");
            }
        } else {
            System.out.println("Hesap bulunamadı veya şifre yanlış. Lütfen geçerli bir hesap numarası ve şifre girin.");
        }
    }

    public boolean kayitKontrol(String hesapNo) {
        // Gerçek bir otentikasyon mekanizması kullanılması gerekmektedir.
        // Bu örnekte sadece hesap numarasını kontrol ediyoruz.
        return hesapNo != null && accountBalances.containsKey(hesapNo);
    }

    public static void main(String[] args) {
        Atm atm = new Atm(12345);

        // Hesap oluşturma ve bakiye sorgulama (test için)
        atm.accountBalances.put("123456", 1000.0);

        // Para yatırma testi
        atm.paraYatir("123456", 500.0);

        // Para çekme testi
        atm.paraCek("123456", 200.0);

        // Geçersiz miktar testi
        atm.paraCek("123456", -50.0);

        // Hesap bulunamadı testi
        atm.paraCek("789012", 100.0);
    }
}
