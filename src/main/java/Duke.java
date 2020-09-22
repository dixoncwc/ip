import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This is the main class that runs the Duke program
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Duke duke = new Duke("Duke/DukeTaskList.txt");
        duke.dukeStart();
    }

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try{
            this.taskList = new TaskList(storage.loadFile());
        }catch(FileNotFoundException | DukeException e){
            this.taskList = new TaskList();
        }
    }

    /**
     * This method will call the UI class and starts the intro of the program
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public void dukeStart() throws FileNotFoundException, DukeException {
        ui.showIntro();
    }

}
