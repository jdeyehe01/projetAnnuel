package Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

import Model.Guest;

public class PluginManager {
	
	private Path path = getLocalDirectory();
	
	public static ArrayList<IPlugin> plugins;
	
	/**
	 * Load all jar files in MyPlugins directory
	 * @return boolean
	 */
	public boolean loadPlugins() {
		
		plugins = new ArrayList<IPlugin>();
		
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
							Object obj = (Object) cls.newInstance();
							IPlugin myPlugin = (IPlugin) obj;
							plugins.add(myPlugin);
							
							/*Method[] m = obj.getClass().getDeclaredMethods();
							int i = 0;
							for(Method my : m) {
								System.out.println(i + ": " + my.getName());
								i++;
							}
							Method getPath = obj.getClass().getMethod("getPath");
							Method setPath = obj.getClass().getMethod("setPath", String.class);
							Method getListOfGuest= obj.getClass().getMethod("getListOfGuest");

							setPath.invoke(obj, "C:\\Users\\Protek\\Desktop\\file1.csv");
							//System.out.println(getPath.invoke(o));
							myPlugin.runPlugin();
							ArrayList<Guest> array = (ArrayList<Guest>) getListOfGuest.invoke(obj);
							//String retValue=(String)method.invoke(o);
							System.out.println("Number : " + array.size());
							array.forEach(guest -> System.out.println(guest));*/
						}
					}
				}
				catch(Exception e) {
					System.out.println(e.fillInStackTrace());
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