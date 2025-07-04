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

## 📌 Deskripsi Project

Selamat datang di repositori tugas kami! 🎉  
Project ini adalah implementasi **API Pemesanan Vila Sederhana** berbasis **Java**, yang dibangun untuk memenuhi tugas mata kuliah **PBO 2**. Jadi ini merupakan program pengembangan Application Programming Interface (API) untuk sistem pemesanan vila yang dibuat menggunakan bahasa Java dan berbasis konsep Object-Oriented Programming. Tujuan dari proyek ini adalah untuk melatih kemampuan mahasiswa dalam mengimplementasikan prinsip OOP, bekerja secara tim, serta mengembangkan API yang dapat memproses berbagai jenis permintaan HTTP.

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
- [API Pemesanan Vila Sederhana Berbasis Java](#-api-pemesanan-vila---pbo-2-2025-)
- [Tentang Kelompok Kami](#-anggota-kelompok)
- [Deskripsi Project](#-deskripsi-project)
- [Struktur Project](#-struktur-project)  
- [Menjalankan Kode Program](#menjalankan-kode-program)
  - [Autentikasi API](#autentikasi-api)
  - [Menjalankan via Postman](#menjalankan-via-postman)
- [Fitur API](#fitur-api)
  - [Endpoint Villa](#endpoint-villa)
    - Daftar semua vila
    - Informasi detail suatu vila
    - Informasi kamar suatu vila, lengkap dengan fasilitas dan harga
    - Daftar semua booking pada suatu vila
    - Daftar semua review pada suatu vila
    - Pencarian ketersediaan vila berdasarkan tanggal check-in dan checkout
    - Menambahkan data vila
    - Menambahkan tipe kamar pada vila
    - Mengubah data suatu vila
    - Mengubah informasi kamar suatu vila
    - Menghapus kamar suatu vila
    - Menghapus data suatu vila
  - [Endpoint Customer](#endpoint-customer)
    - Daftar semua customer
    - Informasi detail seorang customer
    - Daftar booking yang telah dilakukan oleh seorang customer
    - Daftar ulasan yang telah diberikan oleh customer
    - Menambahkan customer baru (registrasi customer)
    - Customer melakukan pemesanan vila
    - Customer memberikan ulasan pada vila (berdasarkan informasi booking)
    - Mengubah data seorang customer
  - [Endpoint Voucher](#endpoint-voucher)
    - Daftar semua voucher
    - Informasi detail suatu voucher
    - Membuat voucher baru
    - Mengubah data suatu voucher
    - Menghapus data suatu voucher


## Menjalankan Kode Program
Silakan jalankan project melalui IDE seperti IntelliJ IDEA. Pastikan koneksi ke SQLite sudah benar dan class `Main.java` sudah dikonfigurasi untuk menjalankan server API.

### Autentikasi API
Semua endpoint hanya bisa diakses jika permintaan menyertakan API Key dalam header request. Contoh:
API key ini sudah ditanam langsung di kelas `Main` dan digunakan sebagai validasi permintaan.

### Menjalankan via Postman
Import file collection Postman atau buat request manual dengan metode, URL, dan JSON body sesuai dengan endpoint yang tersedia. Jangan lupa sertakan header `x-api-key`.

## Fitur API

### Endpoint Villa

`GET /villas` - Melihat Daftar Semua Vila 

`GET /villas/{id}` – Melihat Informasi detail suatu vila

`GET /villas/{id}/rooms` – Melihat Informasi kamar suatu vila, lengkap dengan fasilitas dan harga

`GET /villas/{id}/bookings` – Melihat Daftar semua booking pada suatu vila

`GET /villas/{id}/reviews` - Melihat Daftar semua review pada suatu vila

`GET /villas?ci_date={checkin_date}&co_date={checkout_date}` – Melihat Pencarian ketersediaan vila berdasarkan tanggal check-in dan 
checkout.

`POST /villas` – Menambahkan data vila  

`POST /villas/{id}/rooms` – Menambahkan tipe kamar pada vila 

`PUT /villas/{id}` – Mengubah data suatu vila 

`PUT /villas/{id}/rooms/{id}` – Mengubah informasi kamar suatu vila 

`DELETE /villas/{id}/rooms/{id}` – Menghapus kamar suatu vila 

`DELETE /villas/{id}` – Menghapus data suatu vila 

### Endpoint Customer

`GET /customers` - Melihat Daftar semua customer 

`GET /customers/{id}` - Melihat Informasi detail seorang customer 

`GET /customers/{id}/bookings` - Melihat Daftar booking yang telah dilakukan oleh seorang customer 

`GET /customers/{id}/reviews` - Melihat Daftar ulasan yang telah diberikan oleh customer 

`POST /villas` – Menambahkan Menambahkan customer baru (registrasi customer) 

`POST /villas` – Menambahkan Customer melakukan pemesanan vila

`POST /villas` – Menambahkan Customer memberikan ulasan pada vila (berdasarkan informasi booking)

`PUT /villas/{id}/rooms/{id}` – Mengubah data seorang customer 

### Endpoint Voucher

`GET /vouchers` - Melihat Daftar semua voucher  

`GET /vouchers/{id}` - Melihat Informasi detail suatu voucher 

`POST /vouchers` – Menambahkan voucher baru

`PUT /vouchers/{id}` – Mengubah data suatu voucher 

`DELETE /vouchers/{id}` – Menghapus data suatu voucher
