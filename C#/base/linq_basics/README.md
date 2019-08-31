### Overview

Demonstrate the basic concepts of LINQ in the .NET Framework.  Work with local queries over collections and 
interpreted queries over remote data sources (in my case SQL Server).

### Files

| Filename                     | Description                                                                                |
|------------------------------|--------------------------------------------------------------------------------------------|
| `database/`                  | Files used to setup a data-populated SQL Server database on a Docker container.            |
| `Program.cs`                 | Entrypoint to the C# application for testing Linq queries.                                 |
| `Queries.cs`                 | Demonstrate how LINQ queries work.                                                         |
| `InterpretedQueries.cs`      | Demonstrate how LINQ interpreted queries work.                                             |