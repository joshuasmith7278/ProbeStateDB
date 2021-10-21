import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


/* Joshua Smith
    Oct 21 2021
 */
public class PSDB {
    public static void main(String args[]) throws FileNotFoundException{
        /*
        Probe p = new Probe(10, "10.10.10.10", 23, "12:35:30");
        Probe p2 = new Probe(18, "110.110.110.110", 22, "13:35:30");
        Probe p3 = new Probe(18, "10.10.10.10", 22, "13:35:30");
        Probe p4 = new Probe(18, "10.10.10.10", 22, "13:35:30");
        ProbeList n = new ProbeList(5);
        System.out.println(n.getMaxSize());
        System.out.println(n.getActualSz());
        n.insertProbe(p);
        System.out.println(n.getMaxSize());
        System.out.println(n.getActualSz());
        System.out.println(n.insertProbe(p2));
        n.insertProbe(p3);
        n.insertProbe(p4);
        System.out.println(n.countProbes(8));
        System.out.println(n.getProbes(18));

         */

        File file = new File("firewall.log.txt").getAbsoluteFile();
        try{
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            int size = 0;
            Vector<String> oIP = new Vector<String>();
            Vector<Integer> oPort = new Vector<Integer>();
            Vector<Integer> dPort = new Vector<Integer>();
            Vector<String> time = new Vector<String>();

            //Used to extract the information from the txt file and put them into vectors, dynamic arrays to hold the information seperatly
            while(scanner.hasNextLine()){
                size+=1;
                String[] arrLine = line.split(" ");
                time.add(arrLine[0].substring(arrLine[0].indexOf("(") + 1, arrLine[0].indexOf(")")));
                String[] srcAdd = arrLine[1].split(":");
                oPort.add(Integer.parseInt(srcAdd[1]));

                oIP.add(srcAdd[0]);
                String[] destAdd = arrLine[2].split(":");
                dPort.add(Integer.parseInt(destAdd[1]));
                line = scanner.nextLine();
            }

            //Takes the information from the arrays and creates objects with them putting them into the list
            ProbeList n = new ProbeList(size);
            for (int i = 0; i < size; i++){
                n.insertProbe(new Probe(dPort.elementAt(i), oIP.elementAt(i), oPort.elementAt(i), time.elementAt(i)));
            }

            System.out.println("Welcome to the Port Scan Database.");
            System.out.println("Enter IP/DP/PL/IL/END (IP address/Destination Port/Port List/IP List/END):");
            Scanner input = new Scanner(System.in);
            String userIn = input.nextLine();

            //These loops are used to build the UI queries and interactions
            while(true) {
                if(userIn.equals("END")) {
                    System.out.println("Goodbye");
                    break;
                }else if (userIn.equals("IP")){
                    while(true) {
                        System.out.println("For which IP do you wish to retrieve statistics?");
                        userIn = input.nextLine();
                        if (userIn.equals("END")) {
                            break;
                        }
                        System.out.println("There were " + n.countProbes(userIn) + " probes from " + userIn);
                    }
                }
                else if (userIn.equals("DP")){
                    while(true){
                        System.out.println("For which port do you wish to retrieve statistics?");
                        userIn = input.nextLine();
                        if (userIn.equals("END") || !userIn.matches("[0-9]+")) {
                            break;
                        }
                        System.out.println("There were " + n.countProbes(Integer.parseInt(userIn)) + " probes from " + userIn);
                    }
                }
                else if (userIn.equals("PL")){
                    while(true){
                        System.out.println("Enter a source IP address to see a list of ports that it scanned");
                        userIn = input.nextLine();
                        if (userIn.equals("END")) {
                            break;
                        }
                        if(n.getProbes(userIn).size() == 0){
                            System.out.println("There are no probes with that IP");
                        }else {
                            for (int i = 0; i < n.getProbes(userIn).size(); i++) {
                                System.out.println("IP " + n.getProbes(userIn).get(i).getOriginIP() + " sent a packet from port " + n.getProbes(userIn).get(i).getOriginPort() + " to port " + n.getProbes(userIn).get(i).getDestPort() + " at " + n.getProbes(userIn).get(i).getProbeTime()) ;
                            }
                        }
                    }
                }
                else if (userIn.equals("IL")){
                    while (true){
                        System.out.println("For which port do you wish to retrieve statistics?");
                        userIn = input.nextLine();
                        if(userIn.equals("END")  || !userIn.matches("[0-9]+")){
                            break;
                        }
                        if(n.getProbes(Integer.parseInt(userIn)).size() == 0){
                            System.out.println("There are no probes with that port");
                        }else{
                            System.out.println("The " + n.getProbes(Integer.parseInt(userIn)).size() + " different IP's who probed port " + userIn + " are as follows:");
                            for (int i = 0; i < n.getProbes(Integer.parseInt(userIn)).size(); i++) {
                                System.out.println(n.getProbes(Integer.parseInt(userIn)).get(i));
                            }
                        }
                    }
                }
                System.out.println("Enter IP/DP/PL/IL/END (IP address/Destination Port/Port List/IP List/END):");
                userIn = input.nextLine();
            }



        }
        catch(FileNotFoundException e){
            e.printStackTrace();

        }



    }
}
