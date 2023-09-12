package com.gudratli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : Dunay Gudratli
 * @mailto : d.qudretli@gmail.com
 * @since : 12.09.2023
 **/
public class TaskList {
    public static final String PROCESS_NAME = "tasklist";

    public static Process getTaskListProcess() throws IOException {
        return Runtime.getRuntime().exec(PROCESS_NAME);
    }

    List<TaskListEntry> taskListEntries;
    boolean hasRun;

    public TaskList() {
        taskListEntries = new ArrayList<>();
    }

    public void run() {
        if (hasRun) {
            // intentions of running this twice is not clear so throw exception
            String err = "TaskList can only be run once";
            throw new IllegalStateException(err);
        }

        try {
            Process proc = getProcess();
            List<String> lines = Processes.getAllLines(proc);

            String[] separators = lines.get(2).split(" ");
            if (separators.length < 5) {
                throw new IllegalStateException("Incorrect number of separators parsed");
            }

            // Remove header and separators
            lines = lines.subList(4, lines.size() - 1);

            for (String line : lines) {
                taskListEntries.add(new TaskListEntry(separators, line));
            }
        } catch (IOException e) {
            String err = "Error resolving process";
            throw new IllegalStateException(err, e);
        }
    }

    Process getProcess() throws IOException {
        return getTaskListProcess();
    }

    public List<TaskListEntry> getTaskListEntries() {
        return Collections.unmodifiableList(taskListEntries);
    }

    public static class TaskListEntry {

        private final String image;

        private TaskListEntry(String[] separators, String line) {
            try {
                int start = 0;
                int end = start + separators[0].length() + 1;
                this.image = line.substring(start, end).trim();
            } catch (NullPointerException e) {
                throw e;
            } catch (RuntimeException e) {
                String format = "Unable to parse line {%s} with separator {%s}";
                String msg = String.format(format, line, Arrays.toString(separators));
                throw new IllegalArgumentException(msg, e);
            }
        }

        public String getImage() {
            return image;
        }
    }
}
