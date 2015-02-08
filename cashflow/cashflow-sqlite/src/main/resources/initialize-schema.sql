--
-- File generated with SQLiteStudio v3.0.1 on So Feb 8 18:42:11 2015
--
-- Text encoding used: windows-1252
--
-- PRAGMA foreign_keys = off;
-- BEGIN TRANSACTION;

-- Table: Action
DROP TABLE IF EXISTS [Action];

CREATE TABLE [Action] (
  id       INTEGER PRIMARY KEY,
  register INTEGER REFERENCES Register (id)
                   NOT NULL,
  amount   INTEGER NOT NULL
);


-- Table: Account
DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
  id        INTEGER PRIMARY KEY
    REFERENCES Register (id),
  isVirtual BOOLEAN NOT NULL
);


-- Table: Register
DROP TABLE IF EXISTS Register;

CREATE TABLE Register (
  id      INTEGER PRIMARY KEY,
  owner   INTEGER REFERENCES User (id),
  name    TEXT    NOT NULL,
  uuid    BLOB    NOT NULL
    UNIQUE,
  balance INTEGER NOT NULL
);


-- Table: Share
DROP TABLE IF EXISTS Share;

CREATE TABLE Share (
  id      INTEGER PRIMARY KEY
    REFERENCES Register (id),
  account INTEGER REFERENCES sqlitestudio_temp_table (id)
    NOT NULL
);


-- Table: User
DROP TABLE IF EXISTS User;

CREATE TABLE User (
  id   INTEGER PRIMARY KEY,
  name TEXT    NOT NULL,
  uuid BLOB    UNIQUE
               NOT NULL
);


-- Table: Category
DROP TABLE IF EXISTS Category;

CREATE TABLE Category (
  id   INTEGER PRIMARY KEY,
  name TEXT    NOT NULL,
  uuid BLOB    UNIQUE
               NOT NULL
);


-- Table: Transaction
DROP TABLE IF EXISTS [Transaction];

CREATE TABLE [Transaction] (
  id          INTEGER PRIMARY KEY,
  date        INTEGER NOT NULL,
  description TEXT    NOT NULL,
  category    INTEGER REFERENCES Category (id),
  uuid        BLOB    UNIQUE
                      NOT NULL
);
