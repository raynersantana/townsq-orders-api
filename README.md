To run the app, follow these steps:
    1 - Clone the rep
    2 - Start mysql with docker: 
            docker run --name townsq-mysql -e MYSQL_ROOT_PASSWORD=your-pass -e MYSQL_DATABASE=townsq -p 3306:3306 -d mysql:latest
        2.1 - Make sure to update the application.properties with your mysql config (url, user and pass)
    3 - Run the app
    4 - Access http://localhost:8080/swagger-ui/index.html to navigate through the apis

Done.
