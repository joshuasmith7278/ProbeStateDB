import java.util.ArrayList;

/* Joshua Smith
    Oct 21 2021
 */
public class ProbeList {
    private int actualSz;
    private Probe[] probeArray;

    //Constructor and getter methods
    public ProbeList(int maxSize) {
        probeArray = new Probe[maxSize];
        actualSz = 0;
    }

    public int getMaxSize(){
        return probeArray.length;
    }

    public int getActualSz(){
        return actualSz;
    }

    //Takes in a probe object, if the probe list isnt full, add the probe, return the size
    public int insertProbe(Probe probe){
        if(probeArray.length == actualSz){
            return -1;
        }
        probeArray[actualSz] = probe;
        actualSz += 1;
        return actualSz;
    }

    //Takes in a string IP, counts the amount of times that IP is in the Probes of Probe list, returns the count
    public int countProbes(String ip){
        int count = 0;
        for(int i = 0; i < actualSz; i++){
            if(ip.equals(probeArray[i].getOriginIP())){
                count +=1;
            }
        }
        return count;
    }

    //Takes in an int Port, counts the amount of times that the Port is in the Probes of Probe list, returns the count
    public int countProbes(int destPort){
        int count = 0;
        for(int i = 0; i < actualSz; i++){
            if(destPort == probeArray[i].getDestPort()){
                count +=1;
            }
        }
        return count;
    }

    //Takes in an int Port, creates a new List and adds the IP of Probes in Probe List with that Port to the new list, returns the list
    public ArrayList<String> getProbes(int destPort){
        ArrayList<String> srcIPs = new ArrayList<String>();
        for(int i = 0; i < actualSz; i++){
            if(destPort == probeArray[i].getDestPort()){
                srcIPs.add(probeArray[i].getOriginIP());
            }
        }
        return srcIPs;
    }

    //Takes in a string IP, creates a new List and adds Probes in Probe List with that IP to the new List, return the list
    public ArrayList<Probe> getProbes(String ip){
        ArrayList<Probe> srcProbe = new ArrayList<Probe>();
        for(int i = 0; i < actualSz; i++){
            if(ip.equals(probeArray[i].getOriginIP())){
                srcProbe.add(probeArray[i]);
            }
        }
        return srcProbe;
    }




}
