#!/usr/bin/expect
#scp_build.sh 10.10.234.95
#ssh.sh 10.10.234.95 install Daily_0.000.0112.tar.gz alphaC n n
set timeout -1
set host [lindex $argv 0]
set username root
set password IlikeEbox

spawn ssh root@$host -p 2223
 expect {
  "(yes/no)?"
    {
	  send "yes\n"
    expect "*assword:" { send "$password\n"}
 }
  "*assword:"
  {
   send "$password\n"
   }
   }

expect "*EBboard:~#" {

send "cansend can0 1038901e#0001 \n"
}
sleep 1
send "exit 0 \n"
expect eof

