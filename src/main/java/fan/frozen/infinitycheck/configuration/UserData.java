package fan.frozen.infinitycheck.configuration;

import fan.frozen.infinitycheck.InfinityCheck;
import fan.frozen.infinitycheck.api.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class UserData implements FileConfig {
    private FileConfiguration userData;
    private File userDataFile;
    public UserData(){
        initialize();
    }
    public void initialize(){
        //initialize user data
        //everytime we create a new instance of user data, it will load the latest data from yml file, so we don't have to worry about the data is out of date
        try {
            checkFileExistence();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkFileExistence() throws Exception{
        //get userdata location from config file
        //do the same file existence check as class config dose.
        //because this file doesn't have default value, so we just pass the step of putting default value to file.
        String userDataLocation = InfinityCheck.config.getConfiguration().getString("userDataLocation");
        if (userDataLocation!=null){
            userDataFile = new File(userDataLocation);
            if (!userDataFile.exists()){
                boolean success = userDataFile.createNewFile();
                if (!success){
                    throw new Exception("can't find or create userData file, please check your settings.");
                }else {
                    userData = YamlConfiguration.loadConfiguration(userDataFile);
                }
            }else {
                userData = YamlConfiguration.loadConfiguration(userDataFile);
            }
        }else {
            throw new Exception("empty path, please check your config file.");
        }
    }
    public void reload(){
        userData = YamlConfiguration.loadConfiguration(new File(InfinityCheck.config.getConfiguration().getString("userDataLocation")));
    }

    public void setUserData(String code,int amount){
        if (userData!=null){
            userData.set(code,amount);
            try {
                userData.save(userDataFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void removeUserData(String code){
        //the basic way of this method is to set the specific key's value to null, then the key will automatically unregister in the yml file.
        if (userData!=null){
            userData.set(code,null);
            try {
                userData.save(userDataFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        reload();
    }
    public FileConfiguration getUserData() {
        return userData;
    }
}
