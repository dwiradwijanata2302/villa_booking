# 🌴 API Pemesanan Vila - PBO 2 2025 🌴

📚 **Mata Kuliah:** Pemrograman Berorientasi Objek  
📆 **Semester:** 2  
👨‍🏫 **Dosen Pengampu:** Wayan Oger Vihikan, S.T.I, M.I.T  

---

## 👥 Anggota Kelompok
- 🧑‍💻 I Made Dwira Dwijanata              (2405551051)  
- 🧑‍💻 I Komang Anugrah Kusuma Sena Andika (2405551020)  
- 🧑‍💻 Gideon Kristian Suharman            (2405551077)  
- 🧑‍💻 

---

## 📌 Deskripsi Project

Selamat datang di repositori tugas kami! 🎉  
Project ini adalah implementasi **API Pemesanan Vila Sederhana** berbasis **Java**, yang dibangun untuk memenuhi tugas mata kuliah **PBO 2**. Jadi ini merupakan program pengembangan Application Programming Interface (API) untuk sistem pemesanan vila yang dibuat menggunakan bahasa Java dan berbasis konsep Object-Oriented Programming. Tujuan dari proyek ini adalah untuk melatih kemampuan mahasiswa dalam mengimplementasikan prinsip OOP, bekerja secara tim, serta mengembangkan API yang dapat memproses berbagai jenis permintaan HTTP.

API yang dikembangkan dapat melakukan operasi GET, POST, PUT, dan DELETE terhadap entitas seperti villas, customers, dan vouchers. Semua respons dan permintaan diformat dalam bentuk JSON. Sistem juga dilengkapi dengan validasi data, penanganan error dengan konsep exception, serta pemberian respons HTTP yang sesuai seperti 400 Bad Request atau 404 Not Found.

Data disimpan dalam database SQLite dan akses ke API diamankan dengan API key yang ditanam langsung dalam kelas Main. Pengujian API dilakukan menggunakan Aplikasi Postman, dan dokumentasi lengkap termasuk endpoint serta tangkapan layar hasil pengujian disertakan dalam file README.md.

🎯 **Tujuan Proyek**
- ✅ Menerapkan prinsip **Object-Oriented Programming**
- 🧠 Meningkatkan **keterampilan coding** dengan Java
- 🤝 Bekerja kolaboratif dalam tim
- 🔐 Menerapkan **autentikasi API key**
- 📦 Menggunakan **SQLite** sebagai penyimpanan data

💡 **API mendukung method**
- `GET`, `POST`, `PUT`, `DELETE`
- Format: `JSON`
- Validasi input + penanganan error dengan **exception handler**
- Diuji menggunakan **Postman**

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

---

## 🧭 Daftar Isi
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
  - Penutup

---

## Menjalankan Kode Program
Silakan jalankan project melalui IDE seperti IntelliJ IDEA. Pastikan koneksi ke SQLite sudah benar dan class `Main.java` sudah dikonfigurasi untuk menjalankan server API.

### Autentikasi API
Semua endpoint hanya bisa diakses jika permintaan menyertakan API Key dalam header request. Contoh:
API key ini sudah ditanam langsung di kelas `Main` dan digunakan sebagai validasi permintaan.

### Menjalankan via Postman
Import file collection Postman atau buat request manual dengan metode, URL, dan JSON body sesuai dengan endpoint yang tersedia. Jangan lupa sertakan header `x-api-key`.

## Fitur API

### Endpoint Villa

**`GET /villas` - Melihat Daftar Semua Villa**

![Image](https://github.com/user-attachments/assets/e8e78065-a66e-4403-bd99-31d1aadef020)

Gambar diatas merupakan contoh implemantasi dimana menampilkan **seluruh villa**

---

**`GET /villas/{id}` – Melihat Informasi detail suatu vila**

![Image](https://github.com/user-attachments/assets/015cffb2-7ca6-495e-a242-dbdb32ffe374)

Gambar diatas merupakan contoh implemantasi dimana menampilkan villa dengan **id 1**

---

**`GET /villas/{id}/rooms` – Melihat Informasi kamar suatu vila, lengkap dengan fasilitas dan harga**

![Image](https://github.com/user-attachments/assets/0a2355b2-2e4c-499b-a3f7-04d73081a3ba)

Gambar diatas merupakan contoh implemantasi dimana menampilkan villa dengan **id 1** lengkap dengan **fasilitas** dan **harga**

---

**`GET /villas/{id}/bookings` – Melihat Daftar semua booking pada suatu vila**

![Image](https://github.com/user-attachments/assets/964da655-c2eb-400b-b82c-983acf3f0500)

Gambar diatas merupakan contoh implemantasi dimana menampilkan daftar **semua booking** pada suatu vila

---

**`GET /villas/{id}/reviews` - Melihat Daftar semua review pada suatu vila**

![Image](https://github.com/user-attachments/assets/0bf731ac-0453-45a7-9e3b-e827e3d6bddf)

Gambar diatas merupakan contoh implemantasi dimana menampilkan daftar **semua review** pada suatu vila , pada gambar menampilkan review pada villa dengan **id 1**

---

**`GET /villas/available?/checkin=YYYY-MM-DD&checkout=YYYY-MM-DD` – Melihat Pencarian ketersediaan vila berdasarkan tanggal check-in dan 
checkout.**

![Image](https://github.com/user-attachments/assets/02e56873-6ba4-4992-98dd-fff0dc15fc78)

Gambar diatas merupakan contoh implemantasi dimana menampilkan ketersediaan vila berdasarkan tanggal check-in dan checkout. Pada Gambar Villa dengan id 1 tidak ditampilkan karena sudah Full book ( booking pada id kamar yang sama pada villa yang sama sudah sejumlah ketersediaan atau quantity dari type kamar tersebut)

---

**`POST /villas` – Menambahkan data vila**

![Image](https://github.com/user-attachments/assets/0e580bc3-4ea7-476c-a6d5-965dce9fa904)

Gambar diatas merupakan contoh implemantasi dimana menambahkan data pada vila

---

**`POST /villas/{id}/rooms` – Menambahkan tipe kamar pada vila**

![Image](https://github.com/user-attachments/assets/556d306e-1e18-47f2-b115-68dd7599f6e7)

Gambar diatas merupakan contoh implemantasi dimana menambahkan **tipe kamar** (room_types) pada suatu villa

---

**`PUT /villas/{id}` – Mengubah data suatu vila**

![Image](https://github.com/user-attachments/assets/51cdd229-384c-4e30-8887-9d05b1ba350f)

Gambar diatas merupakan contoh implemantasi dimana mengubah data pada suatu villa

---

**`PUT /villas/{id}/rooms/{id}` – Mengubah informasi kamar suatu vila**

![Image](https://github.com/user-attachments/assets/9b39c879-33f2-4d59-8643-494e5b47cd81)

Gambar diatas merupakan contoh implemantasi dimana mengubah informasi kamar suatu villa, pada gambar perubahan dilakukan untuk villa dengan id 2 dan room types 4 melakukan perubahan quantity dari sebelumnya 2 menjadi 1

---

**`DELETE /villas/{id}/rooms/{id}` – Menghapus kamar suatu vila**

![Image](https://github.com/user-attachments/assets/455cb431-6576-462f-bc13-82bcbaa75333)

Gambar diatas merupakan contoh implemantasi dimana menghapus kamar suatu villa, pada gambar kamar dari villa dengan id 2 dan id rooms 4 dihapus

---

**`DELETE /villas/{id}` – Menghapus data suatu vila**

![Image](https://github.com/user-attachments/assets/82b9c406-8982-4b7f-9c40-24d136ec6946)

Gambar diatas merupakan contoh implemantasi dimana menghapus suatu villa, pada gambar villa dengan id 6 berhasil dihapus

---

### Endpoint Customer

**`GET /customers` - Melihat Daftar semua customer **

![Image](https://github.com/user-attachments/assets/b55fe478-6b56-4f74-a355-d505bc7e04ac)

Gambar diatas merupakan contoh implemantasi dimana melihat daftar **semua customer**

---

**`GET /customers/{id}` - Melihat Informasi detail seorang customer**

![Image](https://github.com/user-attachments/assets/e162ffd2-b636-4496-9a6e-fef74f639d80)

Gambar diatas merupakan contoh implemantasi dimana melihat daftar customer dengan **id 3**

---

**`GET /customers/{id}/bookings` - Melihat Daftar booking yang telah dilakukan oleh seorang customer** 

![Image](https://github.com/user-attachments/assets/e79f1fad-ff36-4c38-8919-79bb94f23202)

Gambar diatas merupakan contoh implemantasi dimana melihat daftar booking yang telah dilakukan oleh seorang customer, pada gambar menampilkan booking yang dilakukan oleh customer dengan id 3

---

**`GET /customers/{id}/reviews` - Melihat Daftar ulasan yang telah diberikan oleh customer**

![Image](https://github.com/user-attachments/assets/9e4aa478-763d-4707-8595-7e4d308a68f1)

Gambar diatas merupakan contoh implemantasi dimana melihat daftar ulasan yang diberikan oleh customer, pada gambar terlihat daftar ulasan customer dengan id 3

---

**`POST /customers` – Menambahkan Menambahkan customer baru (registrasi customer)** 

![Image](https://github.com/user-attachments/assets/4c3b1c55-910b-4376-8bae-10f0aa66083d)

Gambar diatas merupakan contoh implemantasi dimana menambahkan customer baru (registrasi customer), dengan memasukkan nama email dan nomor telepon berformat string

---

**`POST /customers/{id}/bookings` – Menambahkan Customer melakukan pemesanan vila**

![Image](https://github.com/user-attachments/assets/a196a984-1c10-4737-9c4a-ea4b1f216ef2)

Gambar diatas merupakan contoh implemantasi dimana menambahkan customer melakukan pemesanan villa, customer memilih room type lalu tanggal check in dan check out, lalu bisa menggunakan voucher jika sedang ada voucher berlaku dan payment status yang bisa dilunaskan saat reservasi atau tidak , dan jika tidak payment status akan berupa waiting , dan apabila tidak membayar akan tertulis failed

---

**`POST /customers/{id}/bookings/{id}/reviews ` – Menambahkan Customer yang akan memberikan ulasan pada vila (berdasarkan informasi booking)**

![Image](https://github.com/user-attachments/assets/41090f0c-ddf6-43a7-b9f5-e13667b396d1)

Gambar diatas merupakan contoh implemantasi dimana menambahkan customer yang akan memberikan ulasan pada vila (berdasarkan informasi booking)

---

**`PUT /customers/{id} ` – Mengubah data seorang customer** 

![Image](https://github.com/user-attachments/assets/a3c3b5a3-b8d7-4b5f-9a75-8977a1e0313a)

Gambar diatas merupakan contoh implemantasi dimana mengubah data seorang customer, perubahan nama email dan nomor telepon bisa dilakukan

---

### Endpoint Voucher

**`GET /vouchers` - Melihat Daftar semua voucher**

<img width="1919" height="919" alt="Image" src="https://github.com/user-attachments/assets/b70f03de-9c1f-42e3-9725-ed33688deca2" />

Gambar diatas merupakan contoh implemantasi dimana menampilkan **seluruh voucher**

---

**`GET /vouchers/{id}` - Melihat Informasi detail suatu voucher**

<img width="1869" height="965" alt="Image" src="https://github.com/user-attachments/assets/16a69f77-43a1-4b6d-9c25-46bb0f76fd9c" />


Gambar diatas merupakan contoh implemantasi dimana menampilkan **voucher** dengan **id 1**

---

**`POST /vouchers` – Menambahkan voucher baru**
![add voucher](https://github.com/user-attachments/assets/c0a18ba5-17c4-49c3-a8bf-0b8d06912706)


Gambar di atas merupakan contoh untuk menambahkan voucher baru ke dalam sistem.  
Setelah request berhasil dikirim, sistem memberikan response dengan status 201 Created dan pesan "voucher created" yang menandakan voucher telah berhasil dibuat.  
 
---

**`PUT /vouchers/{id}` – Mengubah data suatu voucher**
![put voucher](https://github.com/user-attachments/assets/ac17c7aa-083d-45c1-87fb-e6c59f674137)



Gambar di atas merupakan contoh implementasi untuk mengupdate atau memodifikasi data voucher yang sudah ada dalam sistem. Pada request ini, menggunakan method PUT dengan endpoint http://localhost:8080/vouchers/1 dimana angka 1 menunjukkan ID voucher yang akan diupdate.  
Setelah request berhasil dieksekusi, sistem memberikan response dengan status 200 OK dan pesan "Voucher updated" yang menandakan data voucher telah berhasil diperbarui dalam database dengan informasi yang baru.  

---

**`DELETE /vouchers/{id}` – Menghapus data suatu voucher**
![delete voucher](https://github.com/user-attachments/assets/cc5731fa-1c4a-4eea-9d6a-7c3f20e3b4b1)



Gambar di atas merupakan contoh implementasi untuk menghapus voucher dari sistem. Pada request ini, menggunakan method DELETE dengan endpoint yang menyertakan ID voucher yang akan dihapus (dalam hal ini ID = 1). Setelah request berhasil dieksekusi, sistem memberikan response dengan status 200 OK dan pesan "Voucher deleted" yang menandakan voucher telah berhasil dihapus dari database.  

---

## 📝 Penutup

Proyek ini merupakan hasil kerja kolaboratif dari tim kami dalam menyelesaikan tugas mata kuliah Pemrograman Berorientasi Objek. Melalui pengembangan API ini, kami telah belajar bagaimana menerapkan prinsip-prinsip OOP dalam konteks nyata, mulai dari pengelolaan entitas, routing, hingga validasi dan penanganan error.

Kami berharap proyek ini dapat menjadi referensi dan pembelajaran, tidak hanya bagi tim kami, namun juga untuk teman-teman yang sedang belajar membangun API berbasis Java. Terima kasih telah meluangkan waktu untuk melihat repositori ini 🙏

Jika kamu menyukai proyek ini, jangan lupa ⭐ dan fork!  
Semoga bermanfaat dan sampai jumpa di proyek berikutnya 🚀
