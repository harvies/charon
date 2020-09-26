#!/bin/bash
 
#execute git command
note=`git status`
git status
git add .
git commit -am "$note"
git pull --rebase
git push --all