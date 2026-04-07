# TaskZen – Project & Task Management Application

TaskZen is a **Java Swing-based desktop application** developed to help users efficiently manage **projects, tasks, deadlines, and priorities** through a clean, organized, and interactive dashboard interface.

This project was created as an **academic mini project / internal submission** to demonstrate the practical implementation of **Java GUI development**, **Object-Oriented Programming (OOP)**, modular architecture, and event-driven programming.

---

## 📸 Demo

<p align="center">
  <img src="./1.png" alt="TaskZen Demo 1" width="800"/>
</p>

<p align="center">
  <img src="./2.png" alt="TaskZen Demo 2" width="800"/>
</p>

<p align="center">
  <img src="./3.png" alt="TaskZen Demo 3" width="800"/>
</p>

---

## 📌 Project Overview

TaskZen provides a centralized workspace where users can:

- Create and manage multiple **projects**
- Add and organize **tasks** under each project
- Assign **deadlines**
- Set **priority levels**
- Mark tasks as **completed**
- Delete tasks and projects
- View work in a structured and user-friendly interface

The application follows a **modular design approach**, separating:

- **User Interface**
- **Business Logic**
- **Data Models**

This improves the project's **readability, maintainability, and scalability**.

---

## 🎯 Objectives

The primary objectives of this project are:

- To develop a **desktop-based Task Management System**
- To implement a **GUI application using Java Swing**
- To demonstrate **project-wise task organization**
- To apply **Object-Oriented Programming concepts**
- To implement essential task management operations such as:
  - Add Task
  - Delete Task
  - Complete Task
  - Add Project
  - Delete Project
- To create a **clean and professional dashboard-style user interface**

---

## ✨ Features

### 1. Project Management
- Create multiple projects
- Delete selected projects
- Load tasks dynamically for the selected project

### 2. Task Management
- Add tasks under a selected project
- Enter:
  - Task title
  - Date
  - Time
  - Priority
- Delete selected tasks
- Mark selected tasks as completed

### 3. Task Properties
Each task stores:
- **Title**
- **Deadline**
- **Priority**
- **Completion Status**

### 4. Priority Levels
Tasks can be assigned one of the following priority levels:
- **Low**
- **Medium**
- **High**

### 5. Completion Tracking
Completed tasks are visually distinguished using:
- ✔ Completion indicator
- Strikethrough text
- Completion status tracking

### 6. Interactive Dashboard UI
The application includes:
- Sidebar navigation panel
- Project workspace panel
- Task management panel
- Live date and time display
- Modern dashboard-inspired layout

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** | Core programming language |
| **Java Swing** | GUI development |
| **AWT** | Layouts, fonts, colors, and rendering |
| **OOP Concepts** | Encapsulation, abstraction, modularity |
| **Event Handling** | User interaction and action management |

---

## 🧱 Project Structure

```bash
TaskZen/
│
├── main/
│   └── Main.java
│
├── model/
│   ├── Project.java
│   └── Task.java
│
├── service/
│   └── TaskService.java
│
├── ui/
│   ├── MainFrame.java
│   ├── ProjectPanel.java
│   └── TaskPanel.java
│
├── 1.png
├── 2.png
├── 3.png
└── README.md

---

## 📂 Module Description