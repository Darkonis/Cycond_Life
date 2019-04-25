#!/bin/bash
id=$(docker ps --filter expose=8080 -q)
[[ -z ${id} ]] || (docker container kill ${id} && docker container rm ${id})