### Overview

Create a SQL Server instance running on Docker.  SQL Server is used as a database to test LINQ to SQL and 
Entity Framework (EF) in C#.

### Files

| Filename                     | Description                                                                                |
|------------------------------|--------------------------------------------------------------------------------------------|
| `create-docker-sqlserver.sh` | Bash script to be executed on a local machine for creating a SQL Server Docker env.        |
| `createDB.sql`               | SQL script for creating a database, tables, and populating them with data.                 |
| `Dockerfile`                 | Creates a container that runs SQL Server and populates it with the data in `createDB.sql`. |
| `queries.sql`                | Random SQL Server database queries against the populated tables.                           |
| `setup-db.sh`                | Bash script executed when starting a Docker container for SQL Server.                      |
