import java.net.*;
import java.io.*;

class A2 {

    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 50000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "", str2 = "", serverInfo = "", jobInfo = "";
            String username = System.getProperty("user.name");
            String Helo = "HELO\n", Auth = "AUTH " + username + "\n", Redy = "REDY\n", Get = "GETS All\n";

            dout.write((Helo).getBytes());
            dout.flush();
            str2 = in.readLine();
            System.out.println("Server says: " + str2);

            dout.write((Auth).getBytes());
            dout.flush();
            str2 = in.readLine();
            System.out.println("Server says: " + str2);

            dout.write((Redy).getBytes());
            dout.flush();
            jobInfo = in.readLine();
            System.out.println("Job info: " + jobInfo);

            dout.write((Get).getBytes());
            dout.flush();

            while (!(serverInfo = in.readLine()).equals(".")) {
                dout.write(("OK\n").getBytes());
                dout.flush();
                System.out.println("Server info: " + serverInfo);
		
		// only schedule the jobs on the large server
                if (serverInfo.contains("large")) {
                    String[] jobArr = jobInfo.split(" ");
                    String[] serverDetails = serverInfo.split(" ");
                    String serverType = serverDetails[0];
                    int serverID = Integer.parseInt(serverDetails[1]);
                    int jobID = Integer.parseInt(jobArr[2]);

                    // Scheduling jobs here
                    if (jobInfo.contains("JOBN")) {
                        dout.write(("SCHD " + jobID + " " + serverType + " " + serverID + "\n").getBytes());
                        dout.flush();
                        str2 = in.readLine();
                        System.out.println("Server says: " + str2);
                        System.out.println("Job scheduled on the largest server: " + serverType + " " + serverID);
                    }
            }
       }

            // Send QUIT message after scheduling all jobs
            dout.write(("QUIT\n").getBytes());
            dout.flush();
            str2 = in.readLine();
            System.out.println("Server response: " + str2);

            dout.close();
            s.close();
           
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

