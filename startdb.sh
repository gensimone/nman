#!/bin/sh

docker run \
    -it \
    --rm \
    --name mongodb-notedb \
    -p 27017:27017 \
    mongo:8
