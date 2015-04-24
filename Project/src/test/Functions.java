/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static test.Project.apps;
import static test.Project.recieveFromIP;
import static test.Project.recieveFromMAC;
import static test.Project.rp;
import static test.Project.rsum;
import static test.Project.sendPacketPorts;
import static test.Project.sendToIP;
import static test.Project.sendToMAC;
import static test.Project.sp;
import static test.Project.ssum;

/**
 *
 * @author sergo
 */
public class Functions {

    public static void statistics(String filename, String mac) {

        runCommand("tshark -r" + filename + ".cap -Y \"wlan.addr==" + mac + "&&wlan.fc.type==2\" -w mac.pcap");
        runCommand("tshark -r mac.pcap -T fields -e frame.len -e wlan.sa -e wlan.da "
                + "-e ip.src -e ip.dst  -e tcp.port -e udp.port > mac.txt");

        try (BufferedReader br = new BufferedReader(new FileReader("mac.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.replaceAll("\\s", ",").split(",");
                temp[1] = temp[1].toUpperCase();
                temp[2] = temp[2].toUpperCase();
                if (temp.length == 3) {
                    if (temp[1].contains(mac)) {
                        ssum = ssum + Integer.parseInt(temp[0]);
                        sp++;
                        if (!sendToMAC.contains(temp[2])) {
                            sendToMAC.add(temp[2]);
                        }
                    } else {
                        rp++;
                        rsum = rsum + Integer.parseInt(temp[0]);
                        if (!recieveFromMAC.contains(temp[1])) {
                            recieveFromMAC.add(temp[1]);
                        }
                    }
                } else {
                    if (temp[1].contains(mac)) {
                        ssum = ssum + Integer.parseInt(temp[0]);
                        sp++;
                        if (!sendToMAC.contains(temp[2])) {
                            sendToMAC.add(temp[2]);
                        }
                        if (!sendToIP.contains(temp[4])) {
                            sendToIP.add(temp[4]);
                        }
                        if (temp.length == 7 && !sendPacketPorts.contains(temp[5] + "-->" + temp[6])) {
                            sendPacketPorts.add(getPortName(temp[5]) + "-->" + getPortName(temp[6]));
                            String name = getPortName(temp[6]);
                            if (!apps.contains(name)) {
                                apps.add(name);
                            }
                        }
                        if (temp.length == 8 && !sendPacketPorts.contains(temp[6] + "-->" + temp[7])) {
                            sendPacketPorts.add(getPortName(temp[6]) + "-->" + getPortName(temp[7]));
                            String name = getPortName(temp[7]);
                            if (!apps.contains(name)) {
                                apps.add(name);
                            }
                        }
                    } else {
                        rp++;
                        rsum = rsum + Integer.parseInt(temp[0]);
                        if (!recieveFromMAC.contains(temp[1])) {
                            recieveFromMAC.add(temp[1]);
                        }
                        if (!recieveFromIP.contains(temp[3])) {
                            recieveFromIP.add(temp[3]);
                        }
                        if (temp.length == 7 && !sendPacketPorts.contains(temp[5] + "<--" + temp[6])) {
                            sendPacketPorts.add(getPortName(temp[5]) + "<--" + getPortName(temp[6]));
                            String name = getPortName(temp[5]);
                            if (!apps.contains(name)) {
                                apps.add(name);
                            }
                        }
                        if (temp.length == 8 && !sendPacketPorts.contains(temp[6] + "<--" + temp[7])) {
                            sendPacketPorts.add(getPortName(temp[6]) + "<--" + getPortName(temp[7]));
                            String name = getPortName(temp[6]);
                            if (!apps.contains(name)) {
                                apps.add(name);
                            }
                        }
                    }

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void runCommand(String command) {
        final File scriptFile = new File("runcommand.sh");
        try (PrintWriter w = new PrintWriter(scriptFile)) {
            w.println("#!/bin/sh");
            w.println(command);
            w.close();

            Process p = Runtime.getRuntime().exec("chmod +x " + scriptFile.getAbsolutePath());
            p.waitFor();

            // execute the script
            p = Runtime.getRuntime().exec(scriptFile.getAbsolutePath());
            p.waitFor();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Client> readClients(String num) {
        ArrayList<Client> C = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("myfile" + num + ".csv")));
            String line = br.readLine();
            while ((line = br.readLine()) != null && !line.contains("Station MAC")) {
            }
            // line = br.readLine();
            while ((line = br.readLine()) != null && line.contains(",")) {
                //System.out.println(line);
                String[] sa = line.split(",");
                Client c = new Client(sa[0], sa[1], sa[2], Double.parseDouble(sa[3]), Double.parseDouble(sa[4]), sa[5]);
                C.add(c);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
        return C;
    }

    public static String getPortName(String port) {
        try (BufferedReader br = new BufferedReader(new FileReader("portDB.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(port)) {
                    return line;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "unknown";
    }

    public static String getMACVendor(String mac) {
        File file = new File("macDB.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            String ans = mac.replaceAll(":", "");
            ans = ans.substring(0, 5);
            while ((line = br.readLine()) != null) {
                if (line.contains((ans))) {
                    return (mac + " - " + line.substring(6, line.length()).replaceAll("\\s", ""));
                }

            }
            br.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "unknown";
    }
}
