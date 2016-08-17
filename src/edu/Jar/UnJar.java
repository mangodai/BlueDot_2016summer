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
        if(!desDir.exists())desDir.mkdirs(); //�����û�ָ����ŵ�Ŀ¼
        byte[] bytes = new byte[1024];    
        
        while(jarEntrys.hasMoreElements()){
            ZipEntry entryTemp = jarEntrys.nextElement();
            File desTemp = new File(desDir.getAbsoluteFile() + File.separator + entryTemp.getName());
            
            if(entryTemp.isDirectory()){    //jar��Ŀ�ǿ�Ŀ¼
                if(!desTemp.exists())desTemp.mkdirs();
            }else{    //jar��Ŀ���ļ�
                //��Ϊmanifest��Entry��"META-INF/MANIFEST.MF",д���ᱨ"FileNotFoundException"
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

