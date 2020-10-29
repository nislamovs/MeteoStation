#!/usr/bin/env bash

#mutation{
#  addMeasurement(deviceId: "asdasd"
#    dht11dto:{humidity: "hum1" temperature: "temp1"}
#    bmp085dto:{temperature:"temp2" pressure: "press2" altitude: "alt2" seaLevelPressure: "sea2"})
#}

curl -kvvv \
  -X POST \
  -H "Content-Type: application/json" \
  --data-raw '{"query":"mutation{\n  addMeasurement(deviceId: \"device213\"\n    dht11dto:{humidity: \"50 %\" temperature: \"10 C\"}\n    bmp085dto:{temperature:\"11 C\" pressure: \"100500 Pa\" altitude: \"13 m\" seaLevelPressure: \"101233 Pa\"})\n}","variables":null}' \
  http://localhost:8080/graphql