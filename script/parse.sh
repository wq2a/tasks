#!/bin/bash
for i in $(ls temp);do
  if [ ! -d "$1" ];then 
    mv temp/$i 1.html
    ./parsehtml.py
    rm 1.html
  fi
done