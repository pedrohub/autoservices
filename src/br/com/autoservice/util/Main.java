package br.com.autoservice.util;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listFiles();

	}
	
	private static void listFiles(){
		
		System.out.println("init");
		File file = new File("C:/Users/pedro.edson.o.lima/Documents/arquivos");
		File afile[] = file.listFiles();
		File fileNew = null;
		
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			
			File iFile = afile[i];
			if(fileNew == null){
				fileNew = afile[i];
			}
			
			if(fileNew != null && fileNew.lastModified() < iFile.lastModified()){
				fileNew = afile[i];
			}
			
			System.out.println(fileNew.getName());
		}

		
	}

}
