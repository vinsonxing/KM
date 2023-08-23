#!/usr/bin/expect -f

set SHORT_PORT [lindex $argv 0]

if {$argc != 1} {
  puts "Wrong number of arguments provided"
  puts "ldev <console port short num, for example, 41 of 2041>"
  exit 1
}

spawn telnet 10.74.68.8
expect "Router#"  { send "clear line ${SHORT_PORT}\r\r" }
expect "Router#"  { send "exit\r\r" }
sleep 0.2
spawn telnet 10.74.68.8 "20${SHORT_PORT}"
expect "Escape character is '^]'." {send "\r\r"}
interact