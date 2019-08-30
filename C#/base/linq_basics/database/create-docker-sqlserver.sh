#!/usr/bin/env bash

# Steps to create the SQL Server database environment with docker.  These commands should be run from a local machine 
# in the current directory.
# Author: Andrew Jarombek
# Date: 8/28/2019

docker image build -t misc-code-samples-sqlserver:latest .
docker container run -p 1433:1433 --name misc-code-samples-sqlserver misc-code-samples-sqlserver:latest

docker container logs misc-code-samples-sqlserver

docker container stop misc-code-samples-sqlserver
docker container rm misc-code-samples-sqlserver

# Attach a bash shell to the container
docker container exec -it misc-code-samples-sqlserver bash

# Execute these commands inside the container
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P LinqDemo1 -d master -i createDB.sql