# Miku User Guide

// Product screenshot goes here

Miku is a productivity tool aimed at organizing tasks, people and important locations all in one place. Miku also provides some mentally stimulating games to help stave off boredom.

# Task & Contact Management Bot - User Guide

## Overview
This bot helps you manage tasks, contacts, locations, and associations efficiently. You can create, modify, search, and delete tasks, contacts, and locations while also associating tasks with contacts and locations.

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
find task <task_name>
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
find location <name>
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
associate /task <task_id> /location <location_id>
```
**Outcome:**
Links a task with a location.

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
This bot provides a powerful task, contact, and location management system with easy-to-use commands. Use the appropriate syntax to interact effectively and organize your tasks efficiently!

Enjoy using the bot! 🚀
