package miku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Storage class for reading and writing of files.
 */
public class Storage {
    private Ui ui;

    /**
     * Initializes a Storage instance for reading and writing of files.
     *
     * @param ui a Ui instance
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    //we should also include generic read and write methods for arbitrary files

    /**
     * Checks if filepath pointing to a file exists, if it does not then creates the filepath and file.
     *
     * @param fp filepath to check
     * @return int specifying status of the file
     */
    public int checkFilePathExistsElseCreate(String fp) {
        Path path = Paths.get(fp);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return -1;
            } else {
                return 1;
            }
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Reads tasks from a file specified by a file path and returns an arraylist of the tasks.
     *
     * @param fp a String containing the file path
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> readTasks(String fp) {
        ArrayList<Task> taskList = new ArrayList<>();
        int response = checkFilePathExistsElseCreate(fp);
        if (response == -1) {
            return taskList;
        } else if (response == 0) {
            handleError(9);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fp))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = parseTask(line);
                if (t != null) {
                    taskList.add(t);
                }
            }
        } catch (IOException e) {
            handleError(6);
        }
        return taskList;
    }

    /**
     * Parses a string specifying a task and returns a subclass instance of Task corresponding to the line.
     *
     * @param line string representation of a task
     */
    private Task parseTask(String line) {
        Pattern todoPattern =
            Pattern.compile("^T\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s\\|\\s(.*)\\s\\|$");
        Pattern deadlinePattern =
            Pattern.compile("^D\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s\\|\\s(.+?)\\s\\|\\s(.*)\\s\\|$");
        Pattern eventPattern =
            Pattern.compile("^E\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s"
                            + "\\|\\s(.+?)\\s\\|\\s(.+?)\\s\\|\\s(.*)\\s\\|$");

        Matcher todoMatcher = todoPattern.matcher(line);
        Matcher deadlineMatcher = deadlinePattern.matcher(line);
        Matcher eventMatcher = eventPattern.matcher(line);

        if (todoMatcher.matches()) {
            boolean isDone = todoMatcher.group(1).equals("1");
            String name = todoMatcher.group(3).trim();
            int priority = Integer.valueOf(todoMatcher.group(2));
            String tags = todoMatcher.group(4).trim();
            Todo t = new Todo(name, isDone, priority);
            if (!tags.isEmpty()) {
                for (String s:tags.split("\\s+")) {
                    t.addTag(s);
                }
            }
            return t;
        } else if (deadlineMatcher.matches()) {
            boolean isDone = deadlineMatcher.group(1).equals("1");
            String name = deadlineMatcher.group(3).trim();
            String by = deadlineMatcher.group(4).trim();
            int priority = Integer.valueOf(deadlineMatcher.group(2));
            String tags = deadlineMatcher.group(5).trim();
            Deadline d = new Deadline(name, isDone, priority, by);
            if (!tags.isEmpty()) {
                for (String s:tags.split("\\s+")) {
                    d.addTag(s);
                }
            }
            return d;
        } else if (eventMatcher.matches()) {
            boolean isDone = eventMatcher.group(1).equals("1");
            String name = eventMatcher.group(3).trim();
            String from = eventMatcher.group(4).trim();
            String to = eventMatcher.group(5).trim();
            int priority = Integer.valueOf(eventMatcher.group(2));
            String tags = eventMatcher.group(6).trim();
            Event e = new Event(name, isDone, priority, from, to);
            if (!tags.isEmpty()) {
                for (String s:tags.split("\\s+")) {
                    e.addTag(s);
                }
            }
            return e;
        } else {
            return null;
            //do nth
        }
    }

    /**
     * Writes tasks from an arraylist of tasks to a file specified by a file path.
     *
     * @param taskList an ArrayList of Tasks
     * @param fp a String containing the file path
     */
    public void writeTasks(ArrayList<Task> taskList, String fp) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fp, false))) {
            for (Task t:taskList) {
                bw.write(t.toSaveFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            handleError(7);
        }
    }

    /**
     * Reads contacts from a file specified by a file path and returns an arraylist of the contacts.
     *
     * @param fp a String containing the file path
     * @return an ArrayList of Contacts
     */
    public ArrayList<Contact> readContacts(String fp) {
        ArrayList<Contact> contactList = new ArrayList<>();
        int response = checkFilePathExistsElseCreate(fp);
        if (response == -1) {
            return contactList;
        } else if (response == 0) {
            handleError(9);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");

                if (parts.length < 14) {
                    continue; // Ignore malformed lines
                }

                String firstName = toNull(parts[0].trim());
                String lastName = toNull(parts[1].trim());
                String middleName = toNull(parts[2].trim());
                String housePhone = toNull(parts[3].trim());
                String housePhoneExt = toNull(parts[4].trim());
                String mobilePhone = toNull(parts[5].trim());
                String mobilePhoneExt = toNull(parts[6].trim());
                String workPhone = toNull(parts[7].trim());
                String workPhoneExt = toNull(parts[8].trim());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthday = parts[9].trim().equals("-") ? null : LocalDate.parse(parts[9].trim(), formatter);
                String bloodType = toNull(parts[10].trim());
                String primaryEmail = toNull(parts[11].trim());
                String secondaryEmail = toNull(parts[12].trim());
                String primaryAddress = toNull(parts[13].trim());
                String secondaryAddress = toNull(parts[14].trim());

                contactList.add(new Contact(firstName, lastName, middleName,
                                            housePhone, housePhoneExt,
                                            mobilePhone, mobilePhoneExt,
                                            workPhone, workPhoneExt,
                                            birthday, bloodType,
                                            primaryEmail, secondaryEmail,
                                            primaryAddress, secondaryAddress));
            }
        } catch (IOException e) {
            handleError(10);
        }
        return contactList;
    }

    /**
     * Checks and handles null/empty value representations of fields in Contact
     *
     * @param value string of a field in a Contact object
     * @return null if the string is the empty representation, else value
     */
    private String toNull(String value) {
        return (value == null || value.equals("-")) ? null : value;
    }

    /**
     * Writes contacts from an arraylist of contacts to a file specified by a file path.
     *
     * @param contactList an ArrayList of Contacts
     * @param fp a String containing the file path
     */
    public void writeContacts(ArrayList<Contact> contactList, String fp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fp, false))) {
            for (Contact c:contactList) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            handleError(11);
        }
    }

    /**
     * Reads locations from a file specified by a file path and returns an arraylist of the locations.
     *
     * @param fp a String containing the file path
     * @return an ArrayList of Locations
     */
    public ArrayList<Location> readLocations(String fp) {
        ArrayList<Location> locationList = new ArrayList<>();
        int response = checkFilePathExistsElseCreate(fp);
        if (response == -1) {
            return locationList;
        } else if (response == 0) {
            handleError(9);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6 && parts[0].trim().equals("Place")) {
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    String address = parts[3].trim();
                    double latitude = Double.parseDouble(parts[4].trim());
                    double longitude = Double.parseDouble(parts[5].trim());

                    locationList.add(new Place(name, description, address, latitude, longitude));
                } else if (parts.length == 4 && parts[0].trim().equals("Website")) {
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    String url = parts[3].trim();

                    locationList.add(new Website(name, description, url));
                }
            }
        } catch (IOException e) {
            handleError(12);
        }
        return locationList;
    }

    /**
     * Writes locations from an arraylist of locations to a file specified by a file path.
     *
     * @param locationList an ArrayList of Locations
     * @param fp a String containing the file path
     */
    public void writeLocations(ArrayList<Location> locationList, String fp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fp, false))) {
            for (Location l:locationList) {
                writer.write(l.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            handleError(13);
        }
    }

    /**
     * Prints the relevant error message given an error code.
     *
     * @param code an int denoting the specific type of error
     */
    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
