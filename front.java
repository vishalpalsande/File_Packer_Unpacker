import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;				//* use only folder package
import java.util.*;
import java.io.File;
import java.io.FileOutputStream;		// FileOutputStream use for subpackage name write seprate 
import java.io.FileInputStream;


class front
{
	public static void main(String arg[])
	{
		
		display dobj = new display();	
			
	}
}



class display
{
	public display()
	{
		JFrame f = new JFrame("Vishal Palsande");		//frame creat
		
		JLabel lobj3 = new JLabel("click on any Button");
		lobj3.setBounds(60,30,250,30);
		lobj3.setFont(new Font("TimesRoman",Font.BOLD,21));
		lobj3.setForeground(Color.orange);
		
		
		JButton bobj1 = new JButton("Packer");				//for submit button
		bobj1.setBounds(100,100,150,40);
		
		JButton bobj2 = new JButton("UnPacker");				//for submit button
		bobj2.setBounds(100,200,150,40);
		
		f.add(bobj1);
		f.add(bobj2);
		f.add(lobj3);
		
		f.setSize(350,350);
		f.getContentPane().setBackground(Color.darkGray);
		f.setLayout(null);							//differnt different layout
		f.setVisible(true);		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bobj1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent eobj)
			{		
				Window obj = new Window();
				f.setVisible(false);
			}
		});
		
		bobj2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent eobj)
			{		
				NewWindow o=new NewWindow();
				f.setVisible(false);
			}
		});
		
	}
}



class Window //implements ActionListener
{
	public Window()
	{
		JFrame f = new JFrame("Vishal Palsande");		//frame creat
		
		JLabel lobj3 = new JLabel("__Packer__");
		lobj3.setBounds(110,15,170,30);
		lobj3.setFont(new Font("TimesRoman",Font.BOLD,25));
		lobj3.setForeground(Color.white);
		
		JButton bobj = new JButton("submit");				//for submit button
		bobj.setBounds(100,200,140,40);
		
		JLabel lobj1 = new JLabel("Enter Folder Name: ");		//for Folder name enter
		lobj1.setBounds(20,40,150,100);
		lobj1.setForeground(Color.CYAN);
		
		JTextField tf1 = new JTextField();					//for text
		tf1.setBounds(170,75,130,30);
		
		//----------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lobj2 = new JLabel("Enter File Name: ");			//for file name enter
		lobj2.setBounds(20,105,150,100);
		lobj2.setForeground(Color.CYAN);

		JTextField tf2 = new JTextField();					//for text
		tf2.setBounds(170,140,130,30);
		
		
		f.add(lobj1);
		f.add(bobj);
		f.add(tf1);
		f.add(lobj2);
		f.add(tf2);
		f.add(lobj3);
		
		f.setSize(350,350);
		f.getContentPane().setBackground(Color.darkGray);
		f.setLayout(null);							//differnt different layout
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bobj.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent eobj)
			{
			System.out.println("Folder name entered: "+tf1.getText());
			System.out.println("File name entered: "+tf2.getText());
			Packer pobj = new Packer(tf1.getText(), tf2.getText());
			f.setVisible(false);
			display dobj = new display();
			}
		});
		
		
	}
}



class NewWindow
{
	public NewWindow()
	{
		JFrame fobj = new JFrame("Vishal Palsande");
		
		JLabel lobj3 = new JLabel("___UnPacker___");
		lobj3.setBounds(75,30,230,30);
		lobj3.setFont(new Font("TimesRoman",Font.BOLD,25));
		lobj3.setForeground(Color.white);
		
		JButton bobj = new JButton("Submit");
		bobj.setBounds(100,200,140,40);
		
		JLabel lobj1 = new JLabel("Enter File Name: ");			//for file name enter
		lobj1.setBounds(20,85,150,100);
		lobj1.setForeground(Color.CYAN);

		
		JTextField tf1 = new JTextField();
		tf1.setBounds(170,120,130,30);
		
		fobj.add(bobj);
		fobj.add(lobj1);
		fobj.add(tf1);
		fobj.add(lobj3);
		
		fobj.setSize(350,350);
		fobj.getContentPane().setBackground(Color.darkGray);
		fobj.setLayout(null);		
		fobj.setVisible(true);
		fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		bobj.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent eobj)
			{
				System.out.println("Entered File Name: "+tf1.getText());
				Unpacker uobj = new Unpacker(tf1.getText());
				fobj.setVisible(false);
				display dobj = new display();
			}
		});
				
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


