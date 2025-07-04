# 🌴 API Pemesanan Vila - PBO 2 2025 🌴

📚 **Mata Kuliah:** Pemrograman Berorientasi Objek  
📆 **Semester:** 2  
👨‍🏫 **Dosen Pengampu:** Wayan Oger Vihikan, S.T.I, M.I.T  

---

## 👥 Anggota Kelompok
- I Made Dwira Dwijanata              (2405551051)
- I Komang Anugrah Kusuma Sena Andika (2405551020)
- Gideon Kristian Suharman            (2405551077)
-

---

## 📌 Deskripsi Proyek

Selamat datang di repositori tugas kami! 🎉  
Proyek ini adalah implementasi **API Pemesanan Vila Sederhana** berbasis **Java**, yang dibangun untuk memenuhi tugas mata kuliah **PBO 2**. Jadi ini merupakan program pengembangan Application Programming Interface (API) untuk sistem pemesanan vila yang dibuat menggunakan bahasa Java dan berbasis konsep Object-Oriented Programming. Tujuan dari proyek ini adalah untuk melatih kemampuan mahasiswa dalam mengimplementasikan prinsip OOP, bekerja secara tim, serta mengembangkan API yang dapat memproses berbagai jenis permintaan HTTP.

API yang dikembangkan dapat melakukan operasi GET, POST, PUT, dan DELETE terhadap entitas seperti villas, customers, dan vouchers. Semua respons dan permintaan diformat dalam bentuk JSON. Sistem juga dilengkapi dengan validasi data, penanganan error dengan konsep exception, serta pemberian respons HTTP yang sesuai seperti 400 Bad Request atau 404 Not Found.

Data disimpan dalam database SQLite dan akses ke API diamankan dengan API key yang ditanam langsung dalam kelas Main. Pengujian API dilakukan menggunakan Aplikasi Postman, dan dokumentasi lengkap termasuk endpoint serta tangkapan layar hasil pengujian disertakan dalam file README.md.

Tujuan kami adalah:
- ✅ Menerapkan prinsip **Object-Oriented Programming**
- 🧠 Meningkatkan **keterampilan coding** dengan Java
- 🤝 Bekerja kolaboratif dalam tim
- 🔐 Menerapkan **autentikasi API key**
- 📦 Menggunakan **SQLite** sebagai penyimpanan data

---

## 📁 Struktur Project
src/villa_booking

├── config

│ └── Databases.java

├── controller

│ ├── CustomerController.java

│ ├── VillaController.java

│ └── VoucherController.java

├── http

│ ├── Request.java

│ └── Respond.java

├── model

│ └── Villa.java

├── repository

│ ├── VillaRepository.java

│ └── VoucherRepository.java

├── router

│ └── Router.java

└── Main.java

---

## 🧱 Struktur API (Endpoint Overview)

📍 Base URL: `http://localhost:PORT/`

### 🏡 **Villa**

## Daftar Isi
- [API Pemesanan Vila Sederhana Berbasis Java](#-deskripsi-proyek)
- [Tentang Kelompok Kami](#-anggota-kelompok)
- [Menjalankan Kode Program](#menjalankan-kode-program)
  - [Autentikasi API](#autentikasi-api)
  - [Menjalankan via Postman](#menjalankan-via-postman)
- [Fitur API](#fitur-api)
  - [Endpoint Villa](#endpoint-villa)


## Menjalankan Kode Program
Silakan jalankan project melalui IDE seperti IntelliJ IDEA. Pastikan koneksi ke SQLite sudah benar dan class `Main.java` sudah dikonfigurasi untuk menjalankan server API.

### Autentikasi API
Semua endpoint hanya bisa diakses jika permintaan menyertakan API Key dalam header request. Contoh:
API key ini sudah ditanam langsung di kelas `Main` dan digunakan sebagai validasi permintaan.

### Menjalankan via Postman
Import file collection Postman atau buat request manual dengan metode, URL, dan JSON body sesuai dengan endpoint yang tersedia. Jangan lupa sertakan header `x-api-key`.

## Fitur API

### Endpoint Villa

