# Dockerfile running SQL Server with pre-populated tables and data.
# Author: Andrew Jarombek
# Date: 8/27/2019

FROM mcr.microsoft.com/mssql/server:2017-latest

LABEL maintainer="andrew@jarombek.com" \
      version="0.0.1" \
      description="Dockerfile running SQL Server with pre-populated tables and data"
      
ENV ACCEPT_EULA Y
ENV SA_PASSWORD LinqDemo1

COPY . /src
WORKDIR /src

CMD ["bash", "setup-db.sh"]