package com.jtodo;

import com.jtodo.command.ICommand;
import com.jtodo.handlers.CommandHandler;
import com.jtodo.handlers.ICommandHandler;
import com.jtodo.toDoObjects.IMainList;
import com.jtodo.view.IViewController;
import com.jtodo.view.ViewController;
import com.jtodo.workWithFiles.DataWorker;
import com.jtodo.workWithFiles.IDataWorker;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static com.jtodo.command.utils.CommandUtils.getCommands;

class Main {

    private static IMainList loadFiles(IDataWorker worker, File dir) {
        IMainList list = null;
        try {
            list = worker.convertDataFromFiles(dir);
        } catch (Exception ignored) {
        }

        return list;
    }

    public static void main(String[] args) {
        final String mainPath = "src/main/resources/ToDoLists";
        final String READ_POINTER = "> ";
        final String HELLO_MSG = "Hello! Welcome to jtodo-list.";
        final String EXIT_MSG = "Thank you, Goodbye!";
        final String LIST_MSG = "Your todo-list:";

        File dir = new File(mainPath);
        if (!dir.exists()) {
            System.out.println("Sorry, but directory \"" + dir.toString() + "\" is not exists.");
            return;
        }

        IDataWorker dWorker = new DataWorker();
        IMainList mainList = loadFiles(dWorker, dir);

        if (mainList == null) {
            System.out.println("Failed to load files.");
            return;
        }

        ViewController viewer = new ViewController();
        viewer.addToViewer(mainList);

        Scanner in = new Scanner(System.in);
        ICommandHandler handler = new CommandHandler(initCommand(viewer));

        Timer timer = new Timer();
        IMainList timerMainList;
        timerMainList = mainList;
        TimerTask autoSave = new TimerTask() {
            public void run() {
                try {
                    dWorker.deleteDifferences(mainList, dir);
                    dWorker.convertDataToFiles(timerMainList, dir);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        timer.schedule(autoSave, 5000, 10000);

        System.out.println(HELLO_MSG);
        System.out.println();
        while (!viewer.empty()) {
            System.out.println(LIST_MSG);
            viewer.display();
            System.out.print(READ_POINTER);
            String command = in.nextLine();
            handler.handleCommand(command, viewer);
        }

        timer.cancel();
        try {
            dWorker.convertDataToFiles(mainList, dir);
        } catch (Exception ex) {
            System.out.println("Failed to save files, last saved version of files was saved.");
        }
        System.out.println(EXIT_MSG);
    }

    private static Map<String, ICommand> initCommand(IViewController viewer) {
        return getCommands(viewer);
    }
}