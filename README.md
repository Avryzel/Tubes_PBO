## Aplikasi Kontak

Aplikasi Kontak adalah aplikasi berbasis javafx yang menyimpan beberapa informasi seperti nomor telfon, username dan password untuk sebuah aplikasi, dan catatan.

## Apa yang berfungsi

<h2>1. Login Untuk Memasuki Aplikasi</h2>
   
  ![image](https://github.com/user-attachments/assets/229e9de9-95b3-4398-ab55-b04d162258a0)

  `username`: admin
  
  `password`: 1234

<h2>2. Menu Utama Aplikasi</h2>
   
  ![image](https://github.com/user-attachments/assets/107b12bf-56ce-4074-ac70-743d04a09184)

  `Kontak Apk`: untuk menyimpan kontak.
  
  `Id Apk`: untuk menyimpan username dan password untuk sebuah aplikasi.
  
  `catatan Aok`: untuk menyimpan catatan.

<h2>3. Fitur Kontak Apk</h2>
   
  ![image](https://github.com/user-attachments/assets/1880690a-673f-4873-8d2c-68b555e9a99b)

  `Tampilan`: menampilkan nama dan kontak yang tersimpan dalam database.
  
  `Tambah Kontak`: menambahkan kontak ke dalam database.
  
  `Edit Kontak`: mengedit atau memperbarui kontak yang ada di dalam database.
  
  `Hapus Kontak`: menghapus kontak yang ada di dalam database.
  
  `Kembali`: Kembali ke menu utama.

<h2>4. Fitur ID Apk</h2>
   
   ![image](https://github.com/user-attachments/assets/5a7a0ab0-4478-46ec-8f20-2ae95ade1c33)
   
  `Tampilan`: menampilkan nama aplikasi, id, dan password untuk sebuah aplikasi yang tersimpan dalam database.
  
  `Tambah Id`: menambahkan Id ke dalam database.
  
  `Edit Id`: mengedit atau memperbarui Id yang ada di dalam database.
  
  `Hapus Id`: menghapus Id yang ada di dalam database.
  
  `Kembali`: Kembali ke menu utama.

<h2>5. Fitur Catatan Apk</h2>
   
   ![image](https://github.com/user-attachments/assets/537edcf1-7891-4659-a878-2c8c397f5dba)

  `Tampilan`: menampilkan catatan tersimpan dalam database.
  
  `Tambah Catatan`: menambahkan catatan ke dalam database.
  
  `Edit Catatan`: mengedit atau memperbarui catatan yang ada di dalam database.
  
  `Hapus Catatan`: menghapus catatan yang ada di dalam database.
  
  `Kembali`: Kembali ke menu utama.

## Update
Untuk saat ini, menu yang baru terkoneksi dengan database yaitu Kontak Apk.

- database bikin manual sendiri

- nama database database_kontak

- nama table kontak

- isi table nama varchar(50) not null

- no_hp varchar(50) not null

- pesan text


CREATE DATABASE database_kontak;

   USE DATABASE database_kontak;
   
   CREATE TABLE kontak (
   
      nama varchar(50) not null,
      
      no_hp varchar(50) not null,
      
      pesan text
      
);


menampilkan data yang ada di database ✅

tambah kontak ✅

edit kontak ✅

hapus kontak ✅

