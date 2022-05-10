#!/bin/bash
app='java-crawler-recruit-1.0.0.jar'
args='-Xms512m -Xmx512m'
cmd=$1
pid=`ps -ef |grep $app | grep -v 'grep'| awk '{print $2}'`

startup(){
  nohup java -jar $args $app > logs/server.log &
}

if [ ! $cmd ]; then
  echo "Please specify args 'start|restart|stop'"
  exit
fi

if [ $cmd == 'start' ]; then
  if [ ! $pid ]; then
    startup
  else
    echo "$app is running! pid=$pid"
  fi
fi

if [ $cmd == 'restart' ]; then
  if [ $pid ]
    then
      echo "$pid will be killed after 2 seconds!"
      sleep 2
      kill -9 $pid
  fi
  startup
fi

if [ $cmd == 'stop' ]; then
  if [ $pid ]; then
    echo "$pid will be killed after 2 seconds!"
    sleep 2
    kill -9 $pid
  fi
  echo "$app is stopped"
fi