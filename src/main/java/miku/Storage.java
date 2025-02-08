package miku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    public int checkFilePathExistsElseCreate(String fp) {
        Path path = Paths.get(fp);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return -1;
                //System.out.println("File created: " + path);
            } else {
                return 1;
                //System.out.println("File already exists: " + path);
            }
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Reads task from a file specified by a file path and returns an arraylist of the tasks.
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

        File f = new File(fp);
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
            Pattern.compile("^E\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s\\|\\s(.+?)\\s\\|\\s(.+?)\\s\\|\\s(.*)\\s\\|$");
        
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
     * Write tasks from an arraylist of tasks to a file specified by a file path.
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

    private void handleError(int code) {
        ui.printErrorMsg(code);
    }
}
