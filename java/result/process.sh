#!/bin/bash

out=extracted-results.txt
while read -r line; do
   echo ${line}
done < "${out}"

