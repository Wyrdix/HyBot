package fr.wydix.hyBot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

//Methods here are called to interact with a saved file
public interface FileUser {
	//This method return the value beetween the balise of a saved file
	public static String read(File file, String balise[]) throws IOException {
		String readfile = readAll(file);
		for(String bal:balise) {
			readfile = readfile.split("\\<"+bal+"\\>")[1].split("\\<\\/"+bal+"\\>")[0];
		}
		return (readfile.equals(""))?null:readfile;
	}
	//This method write value: toWrite, beetween the balise of a saved file
	public static void write(File file, String balise[],String toWrite) throws IOException {
		System.out.println(toWrite);
		String readfile = readAll(file);
		String lastBalise = balise[balise.length-1];
		String Write = readfile.replace("<"+lastBalise+">"+(read(file, balise) == null?"":read(file, balise))+"</"+lastBalise+">","<"+lastBalise+">"+toWrite+"</"+lastBalise+">");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(Write);
		writer.close();

	}
	//This method return a String of all writing thing of a file
	static String readAll(File file) throws IOException {
		return String.join("\n",Files.lines(Paths.get(file.getPath())).collect(Collectors.toList()));
	}
}
