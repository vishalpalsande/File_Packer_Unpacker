import java.io.*;				//* use only folder package
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileOutputStream;		// FileOutputStream use for subpackage name write seprate 
import java.io.FileInputStream;

class main
{
	public static void main(String arg[])
	{
		Scanner sobj = new Scanner(System.in);
		String Dir,Filename;
		int choice = 0;
		System.out.println("\n--------------------------------->>> Vishal File Packer Unpacker <<<---------------------------------");
		
		while(true)
		{
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("Choose any one from this..\n1: Packing");
			System.out.println("2: Unpacking");
			System.out.println("3: Exit");
			System.out.println("----------------------------------------------------------------------------");
			System.out.print("\nEnter ur choice: ");
			choice = sobj.nextInt();
			
			switch(choice)
			{
				case 1:
					System.out.print("Enter directory name: ");
					Dir = sobj.next();
					
					System.out.print("Enter file name for packing: ");
					Filename = sobj.next();
					Packer pobj = new Packer(Dir,Filename);
					
					break;	
			
				case 2:
					System.out.print("Enter packed file name for unpacked: ");
					String name = sobj.next();
					Unpacker obj = new Unpacker(name);
					break;
					
				case 3:
					System.out.println("\n_________Thank you for using this application__________\n");
					System.exit(0);
					break;						
				default:
					System.out.println("Invalid Choice");
					break;
			}	
		}				
	}
}




class Packer
{
	//FileOutputStream:- charachristics for Data write 
	public FileOutputStream outstream = null;		//Create Reference for new file

	public Packer(String FolderName, String FileName)		//constructor
	{
		try
		{
			System.out.println("Inside Packer Constructor\n\n");
		
			File outfile = new File(FileName);			//new file creat name
			outstream = new FileOutputStream(FileName);		//new file created successfully here
		
			System.setProperty("user.dir",FolderName);	//Set the current working directory for traversal
			
			TravelDirectory(FolderName);
		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
	}
	
	public void TravelDirectory(String path)		//get File name seprately 
	{
		File directoryPath = new File(path);
		int counter=0;
		
		// Get all file names from directory
		File arr[] = directoryPath.listFiles();	//listFiles use for all files name get in one array
		
		System.out.println("Following Files are packed: ");
		for(File filename : arr)
		{
			//System.out.println(filename.getAbsolutePath());
			if(filename.getName().endsWith(".txt"))
			{
				System.out.println(filename.getName());
				counter++;
				PackFile(filename.getAbsolutePath());
			}
		}
		System.out.println("Successfully packed all files..\nNumber of files packed: "+counter);
	}
	
	public void PackFile(String FilePath)		//file pack
	{
		//System.out.println("File name received: "+FilePath);
		
		byte Header[] = new byte[100];
		byte Buffer[] = new byte[1024];		//buffer use for get data(like bucket)
		int length = 0;
		FileInputStream istream= null;		//for file read
		
		File fobj = new File(FilePath);
		
		String temp = FilePath + " " + fobj.length();	//create header string
		
		//create header for 100 bytes
		for(int i=temp.length() ; i<100 ; i++)
		{
			temp = temp+ " ";
		}
		
		Header = temp.getBytes();		//string convert into bytes
		
		try
		{
			//open file for reading
			istream = new FileInputStream(FilePath);
			
			outstream.write(Header,0,Header.length);
		
			while( (length = istream.read(Buffer)) > 0)	//actual file data write in file in 1024byte
			{
				outstream.write(Buffer,0,length);
			}
			
			istream.close();
		}
		catch(Exception obj)
		{}
		//System.out.println("Header : "+temp.length());
	}	
}


class Unpacker
{
	public FileOutputStream outstream = null; 
	
	public Unpacker(String src)
	{
		System.out.println("Inside Unpacker");
		unpackFile(src);
	}
	
	public void unpackFile(String FilePath)
	{
		try
		{
			FileInputStream instream = new FileInputStream(FilePath);
			
			byte Header[] = new byte[100];
			int lenght= 0;
			int counter=0;
			
			while( (lenght = instream.read(Header,0,100)) > 0)
			{
				String str = new String(Header);		//convert into string
				String ext = str.substring(str.lastIndexOf("/"));		//only for last extension(get filename eg:-demo.txt)
				ext  = ext.substring(1);	//only for ("/") remove,1st letter remove
				
				String words[] = ext.split("\\s");		//string split   "\\":-this is for spcae(give string spcae)
				String name = words[0];			//get file name		
				int size = Integer.parseInt(words[1]);	//get file length
				
				byte arr[] = new byte[size];		//array creat for data read
				instream.read(arr,0,size);
				System.out.println("New file gets created as: "+name);
				
				//new files get created
				FileOutputStream fout = new FileOutputStream(name);
				//write data into newly created file
				fout.write(arr,0,size);	
				counter++;
			}
			System.out.println("Successfully unpacked files... \nNumber Of files are unpacked: "+counter);
		}
		catch(Exception obj)
		{}
	}
				
}		

