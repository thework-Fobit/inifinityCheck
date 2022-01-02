package fan.frozen.infinitycheck.api;

import fan.frozen.infinitycheck.InfinityCheck;

import java.io.File;

public interface FileConfig {
    File rootPath = InfinityCheck.getProvidingPlugin(InfinityCheck.class).getDataFolder();
    File configurationFileLocation = new File(rootPath,"config.yml");
}
