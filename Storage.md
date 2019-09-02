## Setup the Data Source 

`#!/bin/bash
 
 docker run --name weedfs \
  -p 9333:9333  \
  -p 8188:8080 \
  -p 18080:8080 \
  -v /local/drive/weedfs:/data \
  -d chrislusf/seaweedfs:1.42 server -dir="/data"
`