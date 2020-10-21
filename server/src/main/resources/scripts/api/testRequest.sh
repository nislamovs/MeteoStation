#!/usr/bin/env bash

curl \
  -X POST \
  -H "Content-Type: application/json" \
  --data '{ "query": "{pacani{pogremuha skolkoZonuToptal}}" }' \
  http://localhost:8080/graphql