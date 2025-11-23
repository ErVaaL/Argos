#!/bin/env bash
docker run -d -p 27017:27017 --name argos-mongo -e MONGO_INITDB_DATABASE=argos mongo:8.0
