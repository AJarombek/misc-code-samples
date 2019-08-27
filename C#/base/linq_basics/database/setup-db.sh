#!/usr/bin/env bash

# Start SQL Server and Populate it with Tables & Data
# Source: https://github.com/twright-msft/mssql-node-docker-demo-app
# Author: Andrew Jarombek
# Date: 8/27/2019

# Start Microsoft SQL Server
/opt/mssql/bin/sqlservr

# Wait for the database to start and then populate it with data.
sleep 90s
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P LinqDemo1 -d master -i createDB.sql
