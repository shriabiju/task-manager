# TaskZen – Project & Task Manager

TaskZen is a **Java Swing-based desktop application** designed to help users efficiently manage **projects, tasks, deadlines, and priorities** in a clean and user-friendly dashboard environment.

This project was developed as an academic mini project / internal submission to demonstrate the implementation of **GUI-based desktop application development using Java**, while applying concepts of **Object-Oriented Programming (OOP)**, modular design, event handling, and user interaction.

---

## 📌 Project Overview

TaskZen provides a centralized platform where users can:

- Create and manage multiple **projects**
- Add tasks under each project
- Assign **deadlines**
- Set **priority levels**
- Mark tasks as **completed**
- Delete tasks and projects
- View and organize work in a visually structured dashboard

The application follows a **modular architecture**, separating the **user interface**, **business logic**, and **data models**, making the project easier to understand, maintain, and extend.

---

## 🎯 Objectives

The main objectives of this project are:

- To develop a desktop-based **Task Management System**
- To apply **Java Swing** for graphical user interface design
- To demonstrate **project-wise task organization**
- To implement operations such as:
  - Add task
  - Delete task
  - Complete task
  - Add project
  - Delete project
- To provide an intuitive and professional dashboard layout
- To practice **modular Java programming** and event-driven design

---

## ✨ Features

### ✅ Project Management
- Create multiple projects
- Delete selected projects
- Automatically load tasks for the selected project

### ✅ Task Management
- Add tasks to a selected project
- Enter task title, date, time, and priority
- Delete selected tasks
- Mark selected tasks as completed

### ✅ Task Properties
Each task stores:
- **Title**
- **Deadline**
- **Priority**
- **Completion status**

### ✅ Priority Levels
Tasks can be assigned one of the following priorities:
- Low
- Medium
- High

### ✅ Completion Tracking
Completed tasks are visually distinguished using:
- ✔ completion mark
- strikethrough text
- completed state tracking

### ✅ Interactive Dashboard UI
The application includes:
- A **sidebar navigation panel**
- A **project workspace panel**
- A **task management panel**
- A **live date and time display**
- A modern dashboard-inspired layout

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** | Core programming language |
| **Java Swing** | GUI development |
| **AWT** | Layouts, colors, fonts, rendering |
| **OOP Concepts** | Encapsulation, modularity, abstraction |
| **Event Handling** | Button clicks, list selection, task actions |

---

## 🧱 Project Structure

The project is organized into multiple packages for better modularity and readability.

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
└── README.md
📂 Module Description
1. main Package
Main.java

This is the entry point of the application.

Responsibilities:

Launches the GUI using SwingUtilities.invokeLater()
Initializes the main application frame
Ensures UI starts safely on the Event Dispatch Thread
2. model Package

This package contains the data classes used in the application.

Project.java

Represents a project that contains:

Project name
List of tasks

Methods include:

Add task
Delete task
Get task list
Count completed/pending tasks
Task.java

Represents an individual task with:

Task title
Deadline
Priority
Completion status

Methods include:

Mark task completed
Mark task pending
Accessors and mutators for task details
3. service Package
TaskService.java

This is the business logic layer of the application.

Responsibilities:

Manage all projects
Add/delete projects
Add/delete tasks
Mark tasks complete
Fetch tasks of a selected project
Maintain counts for total/completed/pending tasks

This class acts as the bridge between:

the UI
and the data models
4. ui Package

This package contains all graphical interface components.

MainFrame.java

The main application window.

Responsibilities:

Creates the dashboard layout
Integrates:
ProjectPanel
TaskPanel
Displays:
app title
welcome message
live date and time
ProjectPanel.java

Responsible for managing project-related interactions.

Features:

Displays all available projects
Allows adding new projects
Allows deleting existing projects
Updates task view when a project is selected
TaskPanel.java

Responsible for task-related operations.

Features:

Add new tasks
Set:
title
date
time
priority
Display tasks in a custom list format
Delete tasks
Mark tasks as completed

This panel is the core working area of the application.

🖥️ User Interface Design

The application follows a dashboard-style desktop interface for a more professional and modern look.

UI Components Included:
Sidebar navigation
Project workspace list
Task input form
Task list display
Action buttons
Date and time card
Custom styling using fonts, colors, spacing, and layout managers
Design Goals:
Clean layout
Easy navigation
Good visual hierarchy
Better user experience than a default Java Swing application
⚙️ Working of the Application
Step 1 – Start the Application

When the application launches:

the main window opens
default projects are shown:
Personal
Work
College
Step 2 – Select a Project

The user selects a project from the left panel.

Once selected:

tasks belonging to that project are loaded in the main area
Step 3 – Add a Task

The user enters:

Task name
Date
Time
Priority

Then clicks Add Task.

The task is added under the selected project.

Step 4 – Manage Tasks

The user can:

Select a task
Mark it as completed
Delete it if needed
Step 5 – Manage Projects

The user can:

Add a new project
Delete an existing project
🔄 Application Workflow
User Action
   ↓
UI Layer (Swing Panels)
   ↓
TaskService (Logic Handling)
   ↓
Project / Task Models
   ↓
Updated UI Display
🧠 Concepts Applied

This project demonstrates the practical use of several programming and software development concepts:

Core Java Concepts
Classes and Objects
Constructors
Packages
Lists / Collections
Access Modifiers
Method Design
Object-Oriented Programming Concepts
Encapsulation
Modularity
Separation of concerns
Reusability
GUI Programming Concepts
Swing Components
Layout Managers
Event Listeners
Custom Rendering
UI Styling
📸 Screens / Interface Sections

The interface consists of the following main sections:

1. Sidebar

Contains:

Branding / App name
Navigation buttons
Project management area
2. Header

Contains:

Welcome text
App subtitle
Current date and time
3. Task Management Area

Contains:

Task input fields
Priority selection
Task list
Task action buttons
🚀 How to Run the Project
Requirements
Java JDK 8 or above
Any Java IDE such as:
IntelliJ IDEA
Eclipse
NetBeans
VS Code (with Java extensions)
Steps to Run
Clone or download the project:
git clone https://github.com/shriabiju/task-manager.git
Open the project in your Java IDE
Navigate to:
main/Main.java
Run the Main.java file
▶️ Sample Default Projects

When the application starts, it automatically loads these sample projects:

Personal
Work
College

These can be used immediately to test task creation and management.

📈 Future Enhancements

This project can be further enhanced with advanced features such as:

File/database storage for permanent saving
Task editing functionality
Project editing / rename option
Search and filter tasks
Progress bar / analytics
Calendar picker for date input
Notification/reminder support
Dark / Light theme toggle
Login system
Export tasks to file

These enhancements can make the project closer to a real-world productivity application.

✅ Advantages of the Project
Easy to use
Clean and organized dashboard
Supports project-wise task management
Demonstrates practical Java GUI development
Good academic mini project for OOP / Java subjects
Modular and extendable code structure
⚠️ Limitations

Current limitations of the project include:

Data is stored only in memory during runtime
No database or file persistence
No user authentication
No task editing option yet
Date and time are entered manually

These limitations are acceptable for a mini project and can be improved in future versions.

📚 Learning Outcomes

Through this project, the following learning outcomes were achieved:

Understanding Java Swing-based desktop development
Applying OOP concepts in a practical application
Designing modular Java projects
Implementing event-driven user interfaces
Building a complete GUI-based management system

👩‍💻 Author

Shria Biju
Shwet Singh
VIT Bhopal University
