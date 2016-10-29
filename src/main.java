//Lab 5
//Joe Todd & Zeke Faison

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException; import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;




public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Just testing out the student constructor
		//Student zeke = new Student("z", "f", 100, 90,99);
		//System.out.println(zeke);
		/*
		 //Problem 2
		ReadStudentClass k = new ReadStudentClass("basic.txt");
		System.out.println(k);
		ReadDataClass po = new ReadDataClass("Soefile.txt");*/
		//Problem 3
		//WritePage i = new WritePage("JackBeanstalk.txt","New.html");
		
		
		 //Prob 4
		 Tree theTree = new Tree();
		theTree.addNode(50);
		theTree.addNode(30);
		theTree.addNode(25);
		theTree.addNode(15);
		theTree.addNode(30);
		theTree.addNode(75);
		theTree.addNode(85);
		theTree.addNode(30);
		theTree.addNode(50);
		theTree.addNode(25);
		theTree.addNode(25);
		//theTree.PrintTree(theTree.root);
		theTree.PrintTree2(theTree.root);
		// Find the Node2 with key 75
		System.out.println("\nNode with the key 75");
		System.out.println(theTree.findNode(75));
		System.out.println("\nNode with the key 30");
		System.out.println(theTree.findNode(30));
		System.out.println("\nNode with the key 15");
		System.out.println(theTree.findNode(15));
		System.out.println("\nNode with the key 12");
		System.out.println(theTree.findNode(12));
		System.out.println("\nNode with the key 82");
		System.out.println(theTree.findNode(82));
		 
	}
	
	
public static class Student{
	String fName;
	String lName;
	int labGrade;
	int projGrade;
	int examGrade;
	int totalGrade;
	
	Student(String first, String sec, int lab, int proj, int exam){
		lName = first;
		fName = sec;
		labGrade = lab;
		projGrade = proj;
		examGrade = exam;
		totalGrade = getTotal(lab, proj, exam);
		
	}
	
	public int getTotal(int fir, int sec, int third){
		return fir + sec + third;
		
	}
	
	//Last name first 
	public String toString(){
		String str = "Student's name: " + lName + " " + fName + "\t"
				+ "Total:" + totalGrade;
		return str;
	}
}

abstract static class ReadandWrite{
	public String readFile;
	public String newFile;
	
	ReadandWrite(String readF, String createFile){
		readFile = readF;
		newFile = createFile;
	}
	
	public abstract void ReadFile(Scanner files);
	
	public abstract String writeString();
	
	public void OpenForReading(){
		File rFile = new File(readFile);
		try{
			Scanner fileRead = new Scanner (rFile);
			ReadFile(fileRead);
			fileRead.close();
		}
		catch(FileNotFoundException e){
			System.out.println("No file found");
					System.exit(0);
		}
		
	}
	// 
	public void OpenForWriting(){
		
		File wFile = new File (newFile);
		if(!wFile.exists()){
			try {
				wFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("File alread exists");
		}
		try{
			PrintStream outfile = new PrintStream(wFile);
			outfile.println(writeString());
			outfile.close();
		}
		catch(IOException io){
			System.out.println(io.getMessage());
		}
		
	}
}

public static class ReadStudentClass extends ReadandWrite{
	private ArrayList<Student> list = new ArrayList<Student>();
	
	public ReadStudentClass(){
		super("ClassData.txt","newfile.txt");
		OpenForReading();
		OpenForWriting();
		
		
	}
	
	public ReadStudentClass(String filena){
		super("ClassData.txt",filena);
		
		OpenForReading();
		OpenForWriting();
		
	}
	//All of this is self explanatory 
	public void ReadFile(Scanner files){
		while(files.hasNext()){
			String first = files.next();
			String last = files.next();
			int lab = files.nextInt();
			int project = files.nextInt();
			int exam = files.nextInt();
			Student stu = new Student(first,last,lab,project,exam);
			list.add(stu);
			if(files.hasNextLine()){
			files.nextLine();
			}
			
		}files.close();
	}
	
	public ArrayList<Student> sortList(){
		ArrayList<Student> sortStu = new ArrayList<Student>();
		//A temp array that'll hold the student's total grade
		int [] tempArr = new int [list.size()];
		for(int i = 0; i<list.size();i++){
			tempArr[i] = list.get(i).totalGrade;
		}
		//Let the array sort it since I'm lazy
		Arrays.sort(tempArr);
		for(int y = 0;y<tempArr.length;y++){
			for(int x = 0; x<tempArr.length;x++){
				//no evil twins! 
				if(tempArr[y]==list.get(x).totalGrade && sortStu.contains(list.get(x))==false){
					sortStu.add(list.get(x));
				}
			}
		}
		return sortStu;
	}

	public String writeString() {
		// TODO Auto-generated method stub
		list = sortList();
		String str = "";
		for(int x =0; x<list.size();x++){
			str += list.get(x);
			str +="\n";
			
		}
		
		return str;
	}
}

public static class ReadDataClass extends ReadandWrite{
	private ArrayList<Double>nums = new ArrayList<Double>();
	public ReadDataClass(String fn){
		super("ReadingNums.txt",fn);
		OpenForReading();
		OpenForWriting();
	}
	
	public void ReadFile(Scanner files) {
		
		// TODO Auto-generated method stub
		while(files.hasNext()){
			String text = files.next();
			//Those annoying commas made it difficult to just use nextDouble
			//So i took in a string and replaced the commas with spaces
			text = text.replace(",","");
			//Type cast the string without the commas into a double so the arraylist is
			//happy
			double Num = Double.parseDouble(text);
			//Adds the double to the arraylist
			nums.add(Num);
			
			
			
			
		}files.close();
		
	}
	
	private ArrayList<Double> sortNum(){
		//Yay, sorting!
		ArrayList<Double>sortNums = new ArrayList<Double>();
		//Making a double array that'll carry the doubles in the ArrayList
		double[] tempArr = new double[nums.size()];
		for(int i = 0; i<nums.size();i++){
			tempArr[i] = nums.get(i);
			}
		//I'm super lazy so I just used the sort function 
		Arrays.sort(tempArr);
		for(int x = 0; x<tempArr.length;x++){
			for(int y = 0; y<tempArr.length;y++){
				//Want to make sure we don't have any evil twins
				if(tempArr[x]==nums.get(y) && sortNums.contains(nums.get(y))==false){
					sortNums.add(nums.get(y));
				}
			}
		}return sortNums;
	}
	
	public String writeString() {
		// TODO Auto-generated method stub
		
		nums = sortNum();
		String str = "";
		for (int i = 0; i<nums.size();i++){
			//The commas return!
			str += nums.get(i) +",";
			//I added a new line after every ten doubles to make everything pretty
			if(i%10 == 0 && i !=0){
				str+="\n";
			}
			
		}return str;
	}
}
//Problem 3
public static class WritePage{
	//file objects
	private File input;
	private File outfile;
	

	public WritePage(String inn, String outt){
		//file being read first, written file second
		input = new File(inn);
		outfile = new File(outt);
		//The fun part
		edit();
		try { 
			Desktop. getDesktop ().browse(outfile.toURI()); 
		}
		catch (IOException e) { 
			System. out .println(e.getMessage()); 
			}
		
		
	}
	
	
	public void edit(){
		//used for the first line since its tags are <h1></h1>
		boolean first = true;
		try{
			//trying out the new file
			PrintStream outf = new PrintStream(outfile);
			//Laundry list of things you need to add on top of the file
			outf.println("<!doctype html>"+"\n" +"<html>"+"\n"
					+"<head>"+"\n"
			+"\t"+"<meta charset=\"utf-8\">"+"\n"+"\t"+ "<title>My Web Page</title>"+"\n"+
			"</head>"+"\n"+
			"<body>");
			//This was interesting... I didn't know you could open a file to be read
			// while writing a file
			try{
				Scanner fn = new Scanner(input);
				while(fn.hasNext()){
					//boolean needed to test out if a line has all caps for <h2> tags
					boolean caps = false;
					String line = fn.nextLine();
					for(int x = 0; x<line.length();x++){
						//testing to see if the line has all caps
						if(Character.isUpperCase(line.charAt(x))){
							caps = true;
						}
						//those bothersome period at the end of some of the caps
						// had to be ignored
						else if(line.charAt(x) == '.' && Character.isUpperCase(line.charAt(x-1))){
							caps = true;
						}
						else{
							caps = false;
						}
						
					}
					
					//Any line with all caps has <h2> tags added to it
					if(caps == true){
						String newLine = "<h2>"+line+"</h2>";
						outf.println(newLine);
						caps = false;
					}
					//If the line doesn't have all caps and it's the first line 
					// it will get paragraph tags
					else if (first !=true){
						String nLine = "<p>"+line+"</p>";
						outf.println(nLine);
					}
					if(first == true){
						String newL = "<h1>" + line + "</h1>";
						outf.println(newL);
						first = false;
					}
					
				}
				fn.close();
			}
			
			catch(FileNotFoundException e){
				System.out.println(e.getMessage());
				System.exit(0);
				
			}
			//The bottom of the html file... 
			outf.println("</body>" + "\n"+"</html>");
			outf.close();
			
		}
		catch(IOException io){
			System.out.println(io.getMessage());
		}
		
		
		
	}
	
}
//Problem 4
public static class Tree {
	Node root;
	
	public void addNode (int key) {
		// Create a new Node and initialize it
		Node newNode = new Node(key);
		// If there is no root this becomes the root
		if (root == null) {
			root = newNode;
		} else {
			// Set  root as the Node we will start with as we traverse the tree
			Node focusNode = root;
			// Future parent for our new Node
			Node parent;
			while (true) {
				// root is the top parent so we start there
				parent = focusNode;
				//check if the new node should go on the lest side of the parent node
				if (key < focusNode.key) {
					//switch focus to the left child
					focusNode = focusNode.leftChild;
					// If the left child has no children
					if (focusNode == null) {
							// then place the new node on the left of it
							parent.leftChild = newNode;
							return; // All Done
						}
					} 
					else if (key > focusNode.key){
					 // If we get here put the node on the right
							focusNode = focusNode.rightChild;
							// If the right child has no children
							if (focusNode == null) {
							// then place the new node on the right of it
								parent.rightChild = newNode;
								return; // All Done
							}
					}
					else { //already in tree
					parent.clone = newNode;
					
					System.out.println(key + " is already in the tree.");
					return;
					
				}
			}
		}
	}

//All nodes are visited in ascending order. Recursion is used to go to
// one node and then go to its child nodes and so forth
public void PrintTree(Node focusNode) {
	if (focusNode != null) {
			// Traverse the left node
			PrintTree(focusNode.leftChild);
			// Visit the currently focused on node
			System.out.print(focusNode + " ");
			// Traverse the right node
			PrintTree(focusNode.rightChild);
	}
}

public void PrintTree2(Node focusNode) {
	if (focusNode != null) {
			// Traverse the left Node
			PrintTree2(focusNode.leftChild);
			// Visit the currently focused on Node2
			System.out.print(focusNode + " ");
			Node tempnode = focusNode;
			while (tempnode.clone != null) {
				System.out.print(tempnode.clone + " ");
				tempnode = tempnode.clone;
			}
			// Traverse the right Node2
			PrintTree2(focusNode.rightChild);
	}
}


public Node findNode(int key) {
	// Start at the top of the tree
	Node focusNode = root;
	// While we haven't found the Node keep looking
	while (focusNode.key != key) {
			// If we should search to the left
			if (key < focusNode.key) {
					// Shift the focus Node to the left child
					focusNode = focusNode.leftChild;
			} else if (key > focusNode.key) {
				// Shift the focus Node to the right child
				focusNode = focusNode.rightChild;
			}
			else if(focusNode.clone != null){
				focusNode = focusNode.clone;
			}
			// The node wasn't found
			if (focusNode == null) {
					System.out.println(key + " not in tree");
					return null;
			}
	}
	System.out.println("Found! "+ focusNode);
	
	return focusNode;
}
}

public static class Node {
	int key;
	Node leftChild;
	Node rightChild;
	Node clone;
			
	public Node(int key) {
		this.key = key;
	}
	public String toString() {
		String str = "";
		str += key + " ";
		return str;
	}
}


}
