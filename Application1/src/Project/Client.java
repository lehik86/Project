
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;
/**
 *
 * @author alexey
 */
public class Client {
    
    String mac;
    String fseen;
    String lseen;
    int Power;
    int numofpackets;
    String bssid;
    String [] prob_essid;
     
    public Client(String m, String fs,String ls, int p, int n, String b, String [] pb ){
        this.mac = m;
        this.fseen = fs;
        this.lseen = ls;
        this.Power = p;
        this.numofpackets = n;
        this.bssid = b;
        this.prob_essid = pb.clone();
    }
    
   public Client(Client p){
       this.mac = p.mac;
       this.fseen = p.fseen;
       this.lseen = p.lseen;
       this.Power = p.Power;
       this.numofpackets = p.numofpackets;
       this.bssid = p.bssid;
       this.prob_essid = p.prob_essid.clone();
   }
    
    public String toString() {
        return "MAC.:" + this.mac + ",, "
                + "Date.:" + this.fseen+"-"+this.lseen+",, Power.:"
                + this.Power + ",, Number of packets.:" + this.numofpackets+",, BSSID.:" + this.bssid;
    }
}
