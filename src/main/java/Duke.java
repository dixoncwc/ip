import java.io.IOException;
import java.io.FileNotFoundException;


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

    public void dukeStart() throws FileNotFoundException, DukeException {
        ui.showIntro();
    }

}
