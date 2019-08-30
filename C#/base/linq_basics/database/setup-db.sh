#!/usr/bin/env bash

# Start SQL Server and Populate it with Tables & Data
# Source: https://github.com/twright-msft/mssql-node-docker-demo-app
# Author: Andrew Jarombek
# Date: 8/27/2019

# Turn on monitor mode for job control.
set -m

# Start Microsoft SQL Server and put its task in the background.
/opt/mssql/bin/sqlservr &
jobs

# Wait for the database to start and then populate it with data.
sleep 60s
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P LinqDemo1 -d master -i createDB.sql

# Bring the Microsoft SQL Server task back to the foreground.  This prevents the bash script from exiting, 
# killing the Docker container in the process.
fg %1