package miku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * Reads task from a file specified by a file path and returns an arraylist of the tasks.
     *
     * @param fp a String containing the file path
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> readTasks(String fp) {
        ArrayList<Task> taskList = new ArrayList<>();
        //ClassLoader classLoader = getClass().getClassLoader();
        //File f = new File(fp);
        //if (f.isFile()) {
        //try (InputStream is = classLoader.getResourceAsStream(fp)) {
        //    if (is == null) {
        //        handleError(6); // File not found
        //        return taskList;
        //    }
        File f = new File(fp);
        if (f.isFile()) {
            //try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            try (BufferedReader br = new BufferedReader(new FileReader(fp))) {
                String line;
                //DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                Pattern todoPattern = Pattern.compile("^T\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+)$");
                Pattern deadlinePattern = Pattern.compile("^D\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s\\|\\s(.+)$");
                Pattern eventPattern =
                    Pattern.compile("^E\\s\\|\\s(\\d)\\s\\|\\s(\\d)\\s\\|\\s(.+?)\\s\\|\\s(.+?)\\s\\|\\s(.+)$");
                while ((line = br.readLine()) != null) {
                    Matcher todoMatcher = todoPattern.matcher(line);
                    Matcher deadlineMatcher = deadlinePattern.matcher(line);
                    Matcher eventMatcher = eventPattern.matcher(line);
                    if (todoMatcher.matches()) {
                        boolean isDone = todoMatcher.group(1).equals("1");
                        String name = todoMatcher.group(3).trim();
                        int priority = Integer.valueOf(todoMatcher.group(2));
                        taskList.add(new Todo(name, isDone, priority));
                    } else if (deadlineMatcher.matches()) {
                        boolean isDone = deadlineMatcher.group(1).equals("1");
                        String name = deadlineMatcher.group(3).trim();
                        String by = deadlineMatcher.group(4).trim();
                        int priority = Integer.valueOf(deadlineMatcher.group(2));
                        //LocalDateTime by = LocalDateTime.parse(deadlineMatcher.group(3).trim(), DATE_TIME_FORMATTER);
                        taskList.add(new Deadline(name, isDone, priority, by));
                    } else if (eventMatcher.matches()) {
                        boolean isDone = eventMatcher.group(1).equals("1");
                        String name = eventMatcher.group(3).trim();
                        String from = eventMatcher.group(4).trim();
                        String to = eventMatcher.group(5).trim();
                        int priority = Integer.valueOf(eventMatcher.group(2));
                        //LocalDateTime from = LocalDateTime.parse(eventMatcher.group(3).trim(), DATE_TIME_FORMATTER);
                        //LocalDateTime to = LocalDateTime.parse(eventMatcher.group(4).trim(), DATE_TIME_FORMATTER);
                        taskList.add(new Event(name, isDone, priority, from, to));
                    } else {
                        //do nth
                    }
                }
            } catch (IOException e) {
                handleError(6);
            }
        } //catch(IOException e){
        //    handleError(6);
        //}
        return taskList;
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
