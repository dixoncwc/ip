import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private static boolean existLoadedFile = true;
    private static int loadIndex = 0;
    public Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method will save the tasklist to a text file stored inside a directory when the user exit the program
     * @param taskList
     * @throws IOException
     */
    public void saveFile(TaskList taskList) throws IOException {
        this.ui = new Ui();
        FileWriter fw = null;
        try{
            fw = new FileWriter(this.filePath);

        }catch(IOException e){
            //throw new IOException();
            System.out.println("Error");
        }

        String newTaskList = "";

        for(int i=0; i < taskList.getTaskSize(); i++){
            Task newTaskItem = taskList.getTask().get(i);
            if(newTaskItem instanceof ToDo){
                newTaskList = ("T " + "| " + taskList.getTask().get(i).getStatusIcon() + " | " + taskList.getTask().get(i).description
                        + System.lineSeparator());
            } else if(newTaskItem instanceof Deadline){
                newTaskList = ("D " + "| " + taskList.getTask().get(i).getStatusIcon() + " | "
                        + taskList.getTask().get(i).description + " | "
                        + newTaskItem.getTaskDetails()[0] + System.lineSeparator());
            } else{
                newTaskList = ("E " +  "| " + taskList.getTask().get(i).getStatusIcon() + " | "
                        + taskList.getTask().get(i).description + " | " + newTaskItem.getTaskDetails()[0]
                        + System.lineSeparator());
            }
            fw.write(newTaskList);
        }
        fw.close();
        ui.endConvo = true;
    }

    /**
     * When the user runs the program, it will check for existing file and load all task into the array
     * @return
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, DukeException{
        File loadFile = new File(this.filePath);
        if(!loadFile.exists()){
            existLoadedFile = false;
            File newFilePath = new File("Duke");
            boolean isNewDirectoryCreated = newFilePath.mkdir();
            if (isNewDirectoryCreated) {
                File newFile = new File("Duke/DukeTaskList.txt");
                try {
                    newFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println("Failed to create file in new directory");
                }
            }
            else {
                System.out.println("Failed to create directory");
            }
            throw new FileNotFoundException();
        }

        ArrayList<Task> loadTaskList = new ArrayList<>();

        Scanner file = new Scanner(loadFile);
        while(file.hasNext()){
            String taskDetails = file.nextLine();
            String[] splitTaskDetails = taskDetails.trim().split(" \\| ");
            Task loadTask;
            // System.out.println(splitTaskDetails[3]);
            if(splitTaskDetails[0].equals("T")){
                loadTask = new ToDo(splitTaskDetails[2]);
            } else if(splitTaskDetails[0].equals("D")){
                //System.out.println("Hi");
                loadTask = new Deadline(splitTaskDetails[2], splitTaskDetails[3]);
            }else if(splitTaskDetails[0].equals("E")){
                loadTask = new Events(splitTaskDetails[2], splitTaskDetails[3]);
            }else{
                throw new DukeException();
            }

            if(splitTaskDetails[1].equals("\u2713")){
                loadTask.markAsDone();
            }
            loadTaskList.add(loadTask);
        }
        if(existLoadedFile == true && loadIndex == 0) {
            System.out.println("File Successfully Loaded");
            loadIndex++;
        }else if(existLoadedFile == false && loadIndex == 0){
            System.out.println("Created directory");
        }

        return loadTaskList;
    }
}
