# Pacman
## Setting up the server and client
1. Open console nd execute the following commands
    ```
    javac ClientRMIInterface.java
    javac ServerRMIInterface.java
    javac PlayerClient.java
    javac GameServer.java
    ```
2. Start the rmi regisrty
    ```
    rmiregisrty
    ```
3.  In a new console, start the server application
    ```
    java GameServer
    ```
4.  In new console/consoles, start the client application/applications
    ```
    java PlayerClient
    ```
5.  Messages sent from client/clients will be handled by the server and the server will update the clients periodically
