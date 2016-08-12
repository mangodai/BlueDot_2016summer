package edu.File;

import java.io.File;

public class MyFile {

	private File file;

	public MyFile(File file) {
		this.file = file;
	}

	public String toString() {
		String name = file.getName().trim();
		if (name.length() == 0)
			name = file.getAbsolutePath().trim();
		return name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

}
