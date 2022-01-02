package fan.frozen.infinitycheck.configuration;

import fan.frozen.infinitycheck.api.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class Config implements FileConfig {
    private FileConfiguration configuration;
    public Config(){
        initialize();
    }
    public void initialize(){
        //initialize config file from native file system
        try {
            //check config file existence
            checkFileExistence();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkFileExistence() throws Exception{
        //if config file or root path do not existed, then create them and set default value to config file
        if (!rootPath.exists()){
            boolean success = rootPath.mkdirs();
            if (!success){
                throw new Exception("can't find or create root path, please check your settings.");
            }
        }
        //check existence
        if (!configurationFileLocation.exists()){
            boolean success = configurationFileLocation.createNewFile();
            if (!success){
                throw new Exception("can't find or create config file, please check your settings.");
            }else {
                //read configuration and set default value to it and reload to apply the change
                configuration = YamlConfiguration.loadConfiguration(configurationFileLocation);
                setDefaultValue();
                reload();
            }
        }else {
            configuration = YamlConfiguration.loadConfiguration(configurationFileLocation);
        }
    }
    public void setDefaultValue() throws Exception{
        //now only have one value in the configuration file, in the later version it may well have more value
        configuration.set("userDataLocation",rootPath.getPath()+"/userData.yml");
        configuration.save(configurationFileLocation);
    }
    public void reload(){
        configuration = YamlConfiguration.loadConfiguration(configurationFileLocation);
    }
    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
