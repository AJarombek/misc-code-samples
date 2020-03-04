#!/usr/bin/env bash

# Commands to setup and test with Jest
# Author: Andrew Jarombek
# Date: 3/3/2020

# Setup Repo
npm install
nvm install 13.9.0
nvm use 13.9.0

# Run Tests
npm run test