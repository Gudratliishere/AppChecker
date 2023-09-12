package com.gudratli;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

/**
 * @author : Dunay Gudratli
 * @mailto : d.qudretli@gmail.com
 * @since : 12.09.2023
 **/


public class Main {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        tasks.run();
        tasks.getTaskListEntries();
        Optional<TaskList.TaskListEntry> entry = tasks.getTaskListEntries()
                .stream()
                .filter(p -> p.getImage().equals(ResourceUtil.APP_NAME + ".exe"))
                .findFirst();

        if (entry.isEmpty()) {
            try {
                ProcessBuilder pb =
                        new ProcessBuilder(ResourceUtil.APP_PATH);
                pb.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Application is using from another user",
                    "Access denied", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}