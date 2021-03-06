
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
/**
 *
 * @author alexey
 */
public class Client {
    
    public String mac;//MAC address of the client
    String fseen;// first time seen
    String lseen;//last time seen
    double Power;//Signal level reported by the card as the signal gets higher you get closer tothe AP
    double numofpackets;//The number of data packets sent by the client
    String bssid;//MAC address of the access point
  //  String prob_essid;//The ESSIDs probed by the client. These are the networks the client is trying to connect to if it is not currently connected.
     
    public Client(String m, String fs,String ls, double p, double n, String b ){
        this.mac = m;
        this.fseen = fs;
        this.lseen = ls;
        this.Power = p;
        this.numofpackets = n;
        this.bssid = b;
      //  this.prob_essid = pb;
    }
    
   public Client(Client p){
       this.mac = p.mac;
       this.fseen = p.fseen;
       this.lseen = p.lseen;
       this.Power = p.Power;
       this.numofpackets = p.numofpackets;
       this.bssid = p.bssid;
      // this.prob_essid = p.prob_essid;
   }
    
    @Override
    public String toString() {
        return "MAC.:" + this.mac + ",, "
                + "Date.:" + this.fseen+"-"+this.lseen+ ",, Number of packets.:" + this.numofpackets+",, BSSID.:" + this.bssid;
    }
}
