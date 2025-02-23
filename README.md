# Tucil1_13523027 - IQ Puzzler Pro
<p align = "justify"> <b>IQ Puzzler Pro</b> adalah program bahasa Java dengan CLI <i>(command-line interface)</i> untuk menyelesaikan suatu persoalan berdasarkan mainan dunia nyata bernama IQ Puzzler Pro. Pengguna menyediakan input untuk program berupa papan <i>(board)</i> berbentuk persegi atau custom dan sejumlah balok/<i>block/pieces</i>. Program kemudian menggunakan <i>brute force</i> untuk mencari salah stau solusi dari susunan balok yang dapat menyelesaikan permainan (jika ada). Permainan dianggap selesai jika semua balok dapat dimasukkan ke dalam papan untuk mengisi semua ruang di dalamnya sehingga pada papan tidak ada spot/ruang kosong. </p>

## Requirements
### Dependencies
- Java 11

### Installations
- Install JRE versi terbaru

## Cara Menjalankan Program (Tidak Perlu Kompilasi)
1. Clone repository ini, misal dengan terminal: ``git clone https://github.com/Fajar2k5/Tucil1_13523027.git``
2. Jalankan program ``java -jar bin/IQPuzzler.jar``

## Cara Menggunakan Program
1. Jalankan program di dalam CLI
2. Ketik `1` untuk masuk ke program utama

   ![image](https://github.com/user-attachments/assets/59f8eda9-829b-4235-a037-c2a96e4e846b)
3. Masukkan nama atau path ke file test case dengan [format](#format-input)

   ![image](https://github.com/user-attachments/assets/60de6c4a-de3a-41f7-9f59-752dd60d689a)
  
4. Jika input valid akan ditampilkan solusinya (jika ada) dengan durasi brute force dan banyak percobaan yang dilakukan dan prompt untuk menyimpan solusi ke file teks atau gambar. Path ke file output maupun input relative terhadap directory di mana terminal dijalankan.

   ![image](https://github.com/user-attachments/assets/b7772477-a923-4a19-b5a5-2da53e56ca66)
5. Ketik `ya` untuk menyimpan program, kemudian ketik `png` atau `txt` untuk menyimpan dalam format gambar atau plain text

## Format Input
1. Baris pertama, `N M P` N: lebar board, M: panjang board, P: banyak pieces
2. Baris kedua `DEFAULT` untuk board berbentuk persegi atau `CUSTOM` untuk board berbentuk kustom
3. Jika board DEFAULT maka baris ketiga hingga akhir adalah pieces/block yang masing-masing direpresentasikan oleh huruf kapital, huruf yang sama berarti balok yang sama, karakter penyusun satu balok harus saling terhubung (atas/bawah/kiri/kanan bukan diagonal)
4. Jika board bertipe CUSTOM maka baris ketiga hingga baris ke N+2 adalah board kustom dengan karakter titik `.` menunjkkan ruang kosong yang bisa diisi balok sedangkan karakter `X` merepresentasikan tempat tidak kosong, baru kemudian baris sisany adalah pieces/block
5. Contoh input tipe DEFAULT dan CUSTOM
   ```
   5 5 7
   DEFAULT
   A
   AA
   B
   BB
   C
   CC
   D
   DD
   EE
   EE
   E
   FF
   FF
   F
   GGG
   ```
   ```
   5 7 5
   CUSTOM
   ...X...
   .XXXXX.
   XXXXXXX
   .XXXXX.
   ...X...
   A
   AAA
   BB
   BBB
   CCCC
    C
   D
   EEE
   E
   ```

## Author
| NIM      | Nama                    | Kelas                                                                                                                                                                                                               |
|----------|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 13523027 | Fajar Kurniawan         | K-01                                                              |
