#!/bin/bash
for i in $(ls temp);do
  if [ -d "$i" ];then 
    rm -r temp/$i
  else
  	mv temp/$i 1.html
    ./parsehtml.py
    rm -r 1.html
  fi
done