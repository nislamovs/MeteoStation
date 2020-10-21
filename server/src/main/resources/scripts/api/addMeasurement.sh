#!/usr/bin/env bash

#mutation{
#  addMeasurement(deviceId: "asdasd"
#    dht11dto:{humidity: "hum1" temperature: "temp1"}
#    bmp085dto:{temperature:"temp2" pressure: "press2" altitude: "alt2" seaLevelPressure: "sea2"})
#}

curl \
  -X POST \
  -H "Content-Type: application/json" \
  --data-raw '{"query":"mutation{\n  addMeasurement(deviceId: \"asdasd\"\n    dht11dto:{humidity: \"hum1\" temperature: \"temp1\"}\n    bmp085dto:{temperature:\"temp2\" pressure: \"press2\" altitude: \"alt2\" seaLevelPressure: \"sea2\"})\n}","variables":null}' \
  http://localhost:8080/graphql