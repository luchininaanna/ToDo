package com.workWithFiles;

import com.jtodo.status.Completed;
import com.jtodo.status.IStatus;
import com.jtodo.toDoObjects.IMainList;
import com.jtodo.toDoObjects.MainList;
import com.jtodo.workWithFiles.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DataWorkerTest {
    private DataWorker dataWorker;
    private IMainList mainList;
    private File dir;

    @Before
    public void init() {
        dataWorker = new DataWorker();
        mainList = new MainList();
        dir = new File("src/main/resources/test");
    }

    @Test
    public void canDefineStatus() {
        String status = " Completed";
        IStatus iStatus = new Completed();
        assertEquals(dataWorker.defineStatus(status).toString(), iStatus.toString());
    }
    @Test
    public void cantDefineOtherStatus() {
        String deferredStatus = "deferred";
        assertNull(dataWorker.defineStatus(deferredStatus));
    }

    @Test
    public void canReadAndWriteListToFiles() {
        try {
            mainList.createList("New list");
            mainList.getLists().get(0).createDeal("deal");
            dataWorker.convertDataToFiles(mainList, dir);
            IMainList testList = dataWorker.convertDataFromFiles(dir);
            assertTrue(testList.getLists().size() > 0);
            File outFile = new File(dir + "/" + mainList.getLists().get(0).getName() +".txt");
            assertTrue(outFile.exists());
            outFile.deleteOnExit();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void cantReadFilesIfDirectoryEmpty() {
        File emptyDir = new File(dir.getPath() + "/empty");
        if (!emptyDir.exists()) {
            boolean mkdir = emptyDir.mkdir();
            assert mkdir;
        }
        try {
           IMainList testList = dataWorker.convertDataFromFiles(emptyDir);
           assertEquals(testList, mainList);
        } catch (Exception ignored) {

        }
        emptyDir.deleteOnExit();
    }
}
