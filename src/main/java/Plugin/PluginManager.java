package Plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class PluginManager {
	
	private Path path = getLocalDirectory();
	
	/**
	 * Load all jar files in MyPlugins directory
	 * @return boolean
	 */
	public boolean loadPlugins() {
		
		boolean result = true;
		
		try(Stream<Path> listOfPath = Files.list(path)) {
			
			Iterator<Path> it = listOfPath.iterator();
			
			while(it.hasNext()) {
				try {
					Path path = (Path) it.next();
					File file = path.toFile();
					
					if(file.getName().endsWith(".jar")) {
						
						URL url = file.toURI().toURL();
						URL[] urls = new URL[] {url};
						ClassLoader cl = new URLClassLoader(urls);
						Class<?> cls = cl.loadClass("Plugin.Plugin");
						
						if(validInterface(cls)) {
							IPlugin plugin = (IPlugin) cls.newInstance();
						}
					}
				}
				catch(Exception e) {
					result = false;
				}
			}
        }
        catch(Exception e) { 
        	result = false; 
        }
		return result;
	}
	

    /**
     * Check if the plugin implement IPlugin interface
     * @param myClass
     * @return boolean
     */
    public boolean validInterface(Class myClass) {
        Class[] myInterfaces = myClass.getInterfaces();

        for(Class myInterface : myInterfaces) {
            if(myInterface.getName().equals("Plugin.IPlugin")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check and create the folder MyPlugins if not exist
     * @return Path
     */
    public Path getLocalDirectory() {
    	Path path = Paths.get("");
    	path = Paths.get(path.toAbsolutePath() + "\\MyPlugins");
    	
    	try {
			Files.createDirectories(path);
		} catch (IOException e) {
			//System.out.println(e.getMessage());
		}
        return path;
    }
}