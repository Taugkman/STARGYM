package Repository;

import java.io.*;

/**
 * @author Chuxing, Fang
 * @version 1.0
 * Util class used for data processing
 */
public class DataRepository {
    private String filePath;

    public DataRepository(String filePath) {
        // This path contains the project root path
        this.filePath = System.getProperty("user.dir")+filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Add a data object as a serial file
     * @param obj The data object
     * @param fileName The corresponding file name of the data object
     * @return true if success and false if fail
     */
    public boolean add(Object obj, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.getFilePath() + "/" + fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get a data object from a serial file
     * @param id The id of the data object
     * @return The data object
     */
    public Object getById(String id){

        Object ret = null;
        try
        {
            String fileName = id + ".ser";
            FileInputStream fileIn = new FileInputStream(this.filePath+"/"+fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ret = in.readObject();
            in.close();
            fileIn.close();
            return ret;
        }catch(
                IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException ce)
        {
            System.out.println("Employee class not found");
            ce.printStackTrace();
            return null;
        }
    }
}