This is a test project.
This application exposes RESTful API services.

1.To run this project you need to install servlet container Apache Tomcat (tested on version 9.0).
2.Copy file beatdev.war into {tomcat installation folder}/webapp/ folder.
3.Go to {tomcat installation folder}/bin/ folder and run startup.bat file with admin rights.
4.Application is accessable on {ip address}:{port}/beatdev address, for example: localhost:8080/beatdev.

List of requests:
1.
REQUEST: {ip address}:{port}/beatdev/user/{id}.
Method: GET.
RESPONSE: user data or http status 404 if user with provided id was not found.
EXAMPLE: localhost:8080/beatdev/1.
2.
REQUEST: {ip address}:{port}/beatdev/user?name={user_name}&avatarURL={url}&email={user_email}.
Required parameters: name.
Optional parameters: avatarURL, email.
Method: POST.
RESPONSE: assigned id.
EXAMPLE: localhost:8080/beatdev/user?name=Alex&avatarURL=url&email=alex@gmail.com.
3.
REQUEST: {ip address}:{port}/beatdev/status/{id}?status={ONLINE or OFFLINE, case insensitive}.
Method: POST.
RESPONSE: id, previous status, new status or http status 404 if user with provided id was not found.
EXAMPLE: localhost:8080/beatdev/status/1?status=ONLINE.