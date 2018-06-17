package Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginManager {

    private File path = new File("C:\\Users\\Protek\\Desktop\\Projects\\TestPlugin\\MyPlugins");
    private File[] allFiles = path.listFiles();

    public void loadPlugins() {
        for(File file : allFiles) {
            if(file.isFile()) {
                try{
                    JarInputStream jarFile = new JarInputStream(new FileInputStream(file));
                    JarEntry jarEntry;

                    while(true) {
                        jarEntry = jarFile.getNextJarEntry();
                        if(jarEntry == null){
                            break;
                        }
                        if(jarEntry.getName().endsWith (".class")) {
                            URL[] classUrl = new URL[]{path.toURI().toURL()};

                            // We create new URLClassLoader to load the Jar file
                            ClassLoader cl = new URLClassLoader(classUrl);
                            Class loadedClass = cl.loadClass(jarEntry.getName().replace(".class", ""));
                            if(validInterface(loadedClass)) {
                                System.out.println("The plugin : " + file.getName() + " it's a valid plugin.");
                            }
                        }
                    }
                } catch(Exception e){
                    System.out.println(e.fillInStackTrace());
                }
            }
        }
    }

    /**
     * Check if the plugin implement IPlugin interface
     * @param myClass
     * @return boolean
     */
    public boolean validInterface(Class myClass) {
        Class[] myInterfaces = myClass.getInterfaces();

        for(Class myInterface : myInterfaces) {
            if(myInterface.getName().equals("IPlugin")) {
                return true;
            }
        }
        return false;
    }
}
