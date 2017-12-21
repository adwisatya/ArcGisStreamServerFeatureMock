# ArcGIS Stream Server Feature Mock (ASSFM)

ASSFM is java-maven based application for mocking ArcGIS Stream Server Feature for showing data on map by pushing data to client using WebSocket protocol.

# Features 

  - Customable WebSocket endpoint for Stream Server
  - Customable attributes definition

# How to Use
  - Clone / download repository
  - Custom your stream server configuration at "src/main/webapp/StreamServerInfo"
    - Change streamUrls.urls to your own WebSocket endpoint
    - Change attribute list and type on fields as you need
  - Build project using maven-cli / IDE
  - Run on IDE / Tomcat
  - Test your Stream Server Mock on
  

# Important URL
    # /arcgis/rest/info
        Defining your rest information
    # /arcgis/rest/services/.*/StreamServer
        Defining your Stream Server endpoint and attributes

# Testing
After run the project on your local, try to access it using ArcGIS Online.
> http://www.arcgis.com/home/webmap/viewer.html?url=http://localhost:8080/ArcGISMock/arcgis/rest/services/testStream/StreamServer

# Disclaimer
Feel free to use and fork this repository. Open for discussion on **a.dwisaty4[at]yahoo.com**