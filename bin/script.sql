CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS kontak (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   nama varchar(50) not null,
   no_hp varchar(50) not null,
   pesan text
  );

CREATE TABLE IF NOT EXISTS id (
   nama_aplikasi varchar(50) not null,
   id varchar(50) not null,
   password varchar(50) not null
  );

CREATE TABLE IF NOT EXISTS catatan (
   catatan text not null
  );