# Miku User Guide

![](Ui.png?raw=true)

Miku is a productivity tool aimed at organizing tasks, people and important locations all in one place. Miku also provides some mentally stimulating games to help stave off boredom.

# Task & Contact Management Bot - User Guide

## Overview
This bot helps you manage tasks, contacts, locations, and associations efficiently. You can create, modify, search, and delete tasks, contacts, and locations while also associating tasks with contacts and locations.
Do note that some functionalites (not mentioned here) has not been fully implemented and are a work in progress, please check the help message in the app for more information.

---
## Getting Started
1. Ensure you have Java 17 or above installed. You can check the Java version by running ```java --version```.
2. Download the latest release of the ```miku.jar``` file. This is currently ```v2.0.2``` which can be found [here](https://github.com/luna-ortus-cor/ip/releases)
3. Open a terminal.
4. Navigate to the directory where ```miku.jar``` was downloaded to in the terminal. This can be done via the ```cd``` command on most terminals.
5. From the terminal, execute ```java -jar miku.jar``` to launch the application.
6. To terminate the application, either type ```bye`` in the appliication, close the application manually, or execute ```Ctrl-C``` in the terminal.

---
## 📋 Task Management

### 1️⃣ List All Tasks
**Command:**
```plaintext
list
```
**Outcome:**
Displays all tasks currently stored.

### 2️⃣ Mark Task as Done
**Command:**
```plaintext
mark <task_id>
```
**Outcome:**
Marks the specified task as completed.

**Example:**
```plaintext
mark 3
```

### 3️⃣ Unmark Task as Done
**Command:**
```plaintext
unmark <task_id>
```
**Outcome:**
Marks the specified task as not completed.

### 4️⃣ Delete Task
**Command:**
```plaintext
delete <task_id>
```
**Outcome:**
Removes the specified task.

### 5️⃣ Delete All Tasks
**Command:**
```plaintext
delete all
```
**Outcome:**
Removes all tasks from the list.

### 6️⃣ Create To-Do Task
**Command:**
```plaintext
todo <task_description> [/prio <priority>] [/tags <tag1 tag2>]
```
**Outcome:**
Creates a new to-do task.

**Example:**
```plaintext
todo Buy groceries /prio 2 /tags shopping food
```

### 7️⃣ Create Deadline Task
**Command:**
```plaintext
deadline <task_description> /by <due_date> [/prio <priority>] [/tags <tag1 tag2>]
```
**Outcome:**
Creates a deadline-based task.

**Example:**
```plaintext
deadline Submit report /by 2025-02-10 /prio 1 /tags work urgent
```

### 8️⃣ Create Event Task
**Command:**
```plaintext
event <task_description> /from <start_time> /to <end_time> [/prio <priority>] [/tags <tag1 tag2>]
```
**Outcome:**
Creates an event with a specified duration.

**Example:**
```plaintext
event Team meeting /from 10:00 /to 11:30 /prio 1 /tags work meeting
```

### 9️⃣ Search Task by Name
**Command:**
```plaintext
find task name <task_name>
```
**Outcome:**
Finds tasks with matching names.

### 🔟 Set Task Priority
**Command:**
```plaintext
set <task_id> <priority>
```
**Outcome:**
Updates the priority of a task.

### 🔢 Sort Tasks by Priority
**Command:**
```plaintext
sort prio /<asc|desc>
```
**Outcome:**
Sorts tasks by priority in ascending or descending order.

### ➕ Add Tags to Task
**Command:**
```plaintext
add tags <task_id> <tag1 tag2>
```
**Outcome:**
Adds specified tags to the task.

### ❌ Delete Tags from Task
**Command:**
```plaintext
delete tags <task_id> <tag1 tag2>
```
**Outcome:**
Removes specified tags from the task.

### ❌ Delete All Tags from Task
**Command:**
```plaintext
delete all tags <task_id>
```
**Outcome:**
Removes all tags from the task.

---
## 📞 Contact Management

### 1️⃣ Add Contact
**Command:**
```plaintext
add contact
```
**Outcome:**
Prompts user to enter contact details.

### 2️⃣ Edit Contact
**Command:**
```plaintext
edit contact <contact_id>
```
**Outcome:**
Allows editing of a contact’s details.

### 3️⃣ View All Contacts
**Command:**
```plaintext
contacts
```
**Outcome:**
Displays a list of all stored contacts.

### 4️⃣ Search Contact by Name
**Command:**
```plaintext
find name <name>
```

### 5️⃣ Search Contact by Email
**Command:**
```plaintext
find email <email>
```

### 6️⃣ Search Contact by Address
**Command:**
```plaintext
find address <address>
```

### 7️⃣ Delete Contact
**Command:**
```plaintext
delete contact <contact_id>
```

### 8️⃣ Delete All Contacts
**Command:**
```plaintext
delete all contacts
```

---
## 📍 Location Management

### 1️⃣ Add Place
**Command:**
```plaintext
add place <name> /desc <description> /address <address> /lat <latitude> /lon <longitude>
```
**Example:**
```plaintext
add place Home /desc My house /address 123 Main St /lat 37.7749 /lon -122.4194
```

### 2️⃣ Add Website
**Command:**
```plaintext
add website <name> /desc <description> /url <URL>
```
**Example:**
```plaintext
add website Google /desc Search Engine /url https://www.google.com
```

### 3️⃣ Search Location
**Command:**
```plaintext
find location name <name>
```

### 4️⃣ View All Locations
**Command:**
```plaintext
locations
```

### 5️⃣ Delete Location
**Command:**
```plaintext
delete location <location_id>
```

### 6️⃣ Delete All Locations
**Command:**
```plaintext
delete all locations
```

---
## 🔗 Task Associations

### 1️⃣ Associate Task with Contact
**Command:**
```plaintext
associate /task <task_id> /contact <contact_id>
```
**Outcome:**
Links a task with a contact.

### 2️⃣ Associate Task with Location
**Command:**
```plaintext
associate task <task_id> location <location_id>
```
**Outcome:**
Links a task with a location.

### 3️⃣ Find Tasks Associated with Contact
**Command:**
```plaintext
find task by contact <contact_id>
```
**Outcome:**
Finds all tasks associated with a contact.

### 4️⃣ Find Tasks Assocciated with Location
**Command:**
```plaintext
find task by location <location_id>
```
**Outcome:**
Finds all tasks associated with a location.

### 5️⃣ Find Contacts Associated with Task
**Command:**
```plaintext
find contact by task <task_id>
```
**Outcome:**
Finds all contacts associated with a task.

### 6️⃣ Find Locations Associated with Task
**Command:**
```plaintext
find location by task <task_id>
```
**Outcome:**
Finds all locations associated with a task.

---
## 🎮 Miscellaneous Commands

### 1️⃣ Simple Commands
```plaintext
games
track
stats
chat
bye
help
```
**Outcome:**
- `games`: Launches mini-games.
- `track`: Tracks progress.
- `stats`: Shows statistics.
- `chat`: Engages in conversation.
- `bye`: Exits the bot.
- `help`: Displays command guide.

---
## 🏁 Conclusion
Miku provides a powerful task, contact, and location management system with easy-to-use commands.
Use the appropriate syntax to interact effectively and organize your tasks and contacts efficiently!

Miku hopes you have fun! 🚀
Please also provide any feedbak you might have for Miku! <3
This is Miku, signing off!
