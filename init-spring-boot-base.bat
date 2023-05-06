#!/bin/bash
#Get git code from spring boot base arch
git init
echo "git initialised"
git remote add origin https://github.com/abdulbaasititz/SpringBootBaseArch.git
git pull origin master
echo "data pulled from git"
exit