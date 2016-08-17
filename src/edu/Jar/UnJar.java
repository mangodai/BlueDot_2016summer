package edu.Jar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class UnJar {
	private static final String jarPath=System.getProperty("java.class.path");
	
	public UnJar() {
		super();
		this.testUnJarByJarFile();
		// TODO Auto-generated constructor stub
	}
	public void unJarByJarFile(File src , File desDir) throws FileNotFoundException, IOException{
        JarFile jarFile = new JarFile(src);
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        if(!desDir.exists())desDir.mkdirs(); //建立用户指定存放的目录
        byte[] bytes = new byte[1024];    
        
        while(jarEntrys.hasMoreElements()){
            ZipEntry entryTemp = jarEntrys.nextElement();
            File desTemp = new File(desDir.getAbsoluteFile() + File.separator + entryTemp.getName());
            
            if(entryTemp.isDirectory()){    //jar条目是空目录
                if(!desTemp.exists())desTemp.mkdirs();
            }else{    //jar条目是文件
                //因为manifest的Entry是"META-INF/MANIFEST.MF",写出会报"FileNotFoundException"
                File desTempParent = desTemp.getParentFile();
                if(!desTempParent.exists())desTempParent.mkdirs();
                
                BufferedInputStream in = new BufferedInputStream(jarFile.getInputStream(entryTemp));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(desTemp));
                
                int len = in.read(bytes, 0, bytes.length);
                while(len != -1){
                    out.write(bytes, 0, len);
                    len = in.read(bytes, 0, bytes.length);
                }
                
                in.close();
                out.flush();
                out.close();
                
            }
        }
        
        jarFile.close();
    }
	public void testUnJarByJarFile(){
        File src = new File(jarPath);
        String desFile = "Src";
        File desDir = new File(src.getParent()+File.separator+desFile);
        if(!desDir.exists()){
	        try {
	            unJarByJarFile(src, desDir);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
        }
    }
}

