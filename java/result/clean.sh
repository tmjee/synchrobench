#!/bin/bash 

#rm -fr *.txt


echo $(basename $0)
dir=$(dirname $0)

rm -fr "$dir/*.txt"

