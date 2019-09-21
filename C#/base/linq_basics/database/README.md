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

### Resources

1) [SQL Server Docker Container](https://hub.docker.com/_/microsoft-mssql-server?tab=description)
2) [SQL Server Docker with Initial Data](https://github.com/twright-msft/mssql-node-docker-demo-app)
3) [Remove DOS Line Endings in Bash File](http://wiki.secondlife.com/wiki/How_to_avoid_DOS_line_endings_in_Windows_tools)
4) [Bash Background Jobs](https://kb.iu.edu/d/afnz)
5) [Bash Monitor Mode](http://www.linuxforums.org/forum/linux-programming-scripting/139939-fg-no-job-control-script.html)
6) [SQL Server TOP](https://www.w3schools.com/sql/sql_top.asp)