/* Joshua Smith
    Oct 21 2021
 */
//Probe class, contains all the information regarding IP/Port/Time
public class Probe {
    private int destPort;
    private String originIP;
    private int originPort;
    private String probeTime;

    //Constructors and getters for the objects
    public Probe(int desport, String IP, int oriport, String time) {
        this.destPort = desport;
        this.originIP = IP;
        this.originPort = oriport;
        this.probeTime = time;
    }

    public int getDestPort(){
        return destPort;
    }

    public String getOriginIP(){
        return originIP;
    }

    public int getOriginPort(){
        return originPort;
    }

    public String getProbeTime(){
        return probeTime;
    }
}
