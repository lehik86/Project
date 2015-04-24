#!/bin/sh
tshark -r mac.pcap -T fields -e frame.len -e wlan.sa -e wlan.da -e ip.src -e ip.dst  -e tcp.port -e udp.port > mac.txt
