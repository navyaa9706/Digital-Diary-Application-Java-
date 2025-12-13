# Digital-Diary-Application-Java-
A Java-based Digital Diary application that allows users to securely record, manage, search, and delete personal diary entries using file handling and object-oriented programming concepts.

# 📔 Digital Diary Application (Java)

A **Java-based Digital Diary Application** that allows users to securely write, manage, and store personal diary entries.  
The project uses **Object-Oriented Programming** and **file handling** to provide a simple, organized, and persistent diary system without using any external database.

---

## 🚀 Features

- 🔐 User registration and login system
- 📝 Add diary entries with date, title, and content
- 👀 View all saved diary entries
- 🔍 Search diary entries using keywords
- ❌ Delete diary entries by ID
- 💾 Persistent local storage using file handling
- 🖥️ Console-based menu-driven interface
- 🪟 Swing GUI for writing diary entries

---

## 🛠️ Tech Stack

- **Language:** Java  
- **Concepts Used:**  
  - Object-Oriented Programming (OOP)  
  - File Handling (BufferedReader, BufferedWriter)  
  - Collections (ArrayList)  
  - Exception Handling  
- **Interface:** Console + Swing GUI  
- **Storage:** Text files (`users.txt`, `<username>_diary.txt`)

---

## 🧩 Project Workflow

### 1️⃣ User Authentication
- Users can register and log in securely
- Credentials stored locally using file handling

### 2️⃣ Diary Management
- Add new diary entries with date and content
- View previously saved entries
- Search entries using keywords
- Delete unwanted entries

### 3️⃣ Data Storage
- All diary entries stored in user-specific text files
- Ensures persistent data across sessions

---

## 📂 Project Structure

Digital-Diary/
│
├── src/
│ ├── DiaryApp.java
│ ├── Diary.java
│ ├── DiaryEntry.java
│ ├── UserAuth.java
│
├── data/
│ ├── users.txt
│ └── <username>_diary.txt
│
├── README.md
└── .gitignore
