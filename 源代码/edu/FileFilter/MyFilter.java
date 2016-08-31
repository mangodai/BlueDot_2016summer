package edu.FileFilter;

import java.io.File;
import java.io.FileFilter;

public class MyFilter implements FileFilter {
	private String suffix;
	
	public MyFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyFilter(String suffix) {
		super();
		this.suffix = suffix;
	}
	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return (pathname.isDirectory()||pathname.getName().endsWith(suffix))&&(!pathname.isHidden());
	}

}
