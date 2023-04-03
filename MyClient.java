import java.net.*;  

import java.io.*;  

class MyClient{  

public static void main(String args[])throws Exception{  

Socket s=new Socket("localhost",50000);  

BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

DataOutputStream dout=new DataOutputStream(s.getOutputStream());  

BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

  

String str="",str2="";  

String Helo = "HELO\n", Auth= "AUTH Sid\n", Redy="REDY\n", Get="GETS All\n";

dout.write((Helo).getBytes());  
dout.flush();
str2=in.readLine();  
System.out.println("Server says: "+str2);
 
dout.write((Auth).getBytes());  
dout.flush();
str2=in.readLine();  
System.out.println("Server says: "+str2);
 
dout.write((Redy).getBytes());  
dout.flush();
str2=in.readLine();  
System.out.println("Server says: "+str2);

dout.write((Get).getBytes());  
dout.flush();
str2=in.readLine();  
System.out.println("Server says: "+str2);
 
while(!str2.equals(".")){  
str2=br.readLine();  
dout.write(("OK\n").getBytes());  
dout.flush();  
str2=in.readLine();  
System.out.println("Server says: "+str2);  
}  
  
while(!str.equals("stop")){  
str=br.readLine();  
dout.write((str+"\n").getBytes());  
dout.flush();  
str2=in.readLine();  
System.out.println("Server says: "+str2);  
}  

dout.close();  

s.close();  

}}
