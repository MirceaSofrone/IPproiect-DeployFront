FROM mcr.microsoft.com/dotnet/aspnet:5.0 AS base
WORKDIR /app

FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /src
COPY ["Price.csproj", "./"]
RUN dotnet restore "Price.csproj"
WORKDIR "/src/."
COPY . .
RUN dotnet build "Price.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Price.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
CMD ASPNETCORE_URLS=http://*:$PORT dotnet Price.dll
