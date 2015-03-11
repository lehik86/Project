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
public class AccessPoint {
    
    String bssid; //MAC address of the access point
    String fseen;// first time seen
    String lseen;//last time seen
    int channel;//Channel number
    int speed;//Maximum speed supported by the AP
    String privacy;//Encryption algorithm in use
    String cipher;//The cipher detected. One of CCMP, WRAP, TKIP, WEP, WEP40, or WEP104
    String Authentication;//The authentication protocol used
    int power; //Signal level reported by the card as the signal gets higher you get closer tothe station
    int beacons;//Number of announcements packets sent by the AP
    int iv;//unique IV count if used WEP
    String lan_ip;//address you would enter into your browser's address bar to access the router configuration page
    int id_lenght;
    String essid;//Shows the wireless network name
     
    public AccessPoint(String bssid, String fseen, String lseen, int channel, int speed, String privacy, String cipher, String Authentication,
            int power, int beacons, int iv, String lan_ip, int id_lenght, String essid ){
        this.bssid = bssid;
        this.fseen = fseen;
        this.lseen = lseen;
        this.channel = channel;
        this.speed = speed;
        this.privacy = privacy;
        this.cipher = cipher;
        this.Authentication = Authentication;
        this.power = power;
        this.beacons = beacons; 
        this.iv = iv;
        this.lan_ip = lan_ip;
        this.id_lenght = id_lenght;
        this.essid = essid;
    }
    
   public AccessPoint(AccessPoint p){
       this.bssid = p.bssid;
        this.fseen = p.fseen;
        this.lseen = p.lseen;
        this.channel = p.channel;
        this.speed = p.speed;
        this.privacy = p.privacy;
        this.cipher = p.cipher;
        this.Authentication = p.Authentication;
        this.power = p.power;
        this.beacons = p.beacons; 
        this.iv = p.iv;
        this.lan_ip = p.lan_ip;
        this.id_lenght = p.id_lenght;
        this.essid = p.essid;
   }
    
    @Override
    public String toString() {
        return "BSSID.:" + this.bssid + ",, "
                + "Date.:" + this.fseen+"-"+this.lseen+",, channel.:"
                + this.channel + ",, Beacons.:" + this.beacons+",, ESSID.:" + this.essid;
    }
}

