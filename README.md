## Pembuatan API Pemesanan Vila Sederhana Berbasis Java ##
Jadi ini merupakan program pengembangan Application Programming Interface (API) untuk sistem pemesanan vila yang dibuat menggunakan bahasa Java dan berbasis konsep Object-Oriented Programming. Tujuan dari proyek ini adalah untuk melatih kemampuan mahasiswa dalam mengimplementasikan prinsip OOP, bekerja secara tim, serta mengembangkan API yang dapat memproses berbagai jenis permintaan HTTP.

API yang dikembangkan dapat melakukan operasi GET, POST, PUT, dan DELETE terhadap entitas seperti villas, customers, dan vouchers. Semua respons dan permintaan diformat dalam bentuk JSON. Sistem juga dilengkapi dengan validasi data, penanganan error dengan konsep exception, serta pemberian respons HTTP yang sesuai seperti 400 Bad Request atau 404 Not Found.

Data disimpan dalam database SQLite dan akses ke API diamankan dengan API key yang ditanam langsung dalam kelas Main. Pengujian API dilakukan menggunakan Aplikasi Postman, dan dokumentasi lengkap termasuk endpoint serta tangkapan layar hasil pengujian disertakan dalam file README.md.

## Tentang Kelompok Kami ##
Dwira
Aan
2405551077 Gideon Kristian Suharman  
Davrian

## Daftar Isi
- [API Pemesanan Vila Sederhana Berbasis Java](#api-pemesanan-vila-sederhana-berbasis-java)
- [Tentang Kelompok Kami](#tentang-kelompok-kami)
- [Menjalankan Kode Program](#menjalankan-kode-program)
  - [Autentikasi API](#autentikasi-api)
  - [Menjalankan via Postman](#menjalankan-via-postman)


## Menjalankan Kode Program
Silakan jalankan project melalui IDE seperti IntelliJ IDEA. Pastikan koneksi ke SQLite sudah benar dan class `Main.java` sudah dikonfigurasi untuk menjalankan server API.

### Autentikasi API
Semua endpoint hanya bisa diakses jika permintaan menyertakan API Key dalam header request. Contoh:
API key ini sudah ditanam langsung di kelas `Main` dan digunakan sebagai validasi permintaan.

### Menjalankan via Postman
Import file collection Postman atau buat request manual dengan metode, URL, dan JSON body sesuai dengan endpoint yang tersedia. Jangan lupa sertakan header `x-api-key`.

## Fitur API

### Endpoint Villa

