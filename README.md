# SimpleMail
A simple RESTful service for sending mails through Gmail smtp server(SSL).

Example of input JSON data:

{
	"username":"amardeep.bhowmick1992@gmail.com",
	"password":"**********"
	"message":"hello world",
	"recipient":["amardeep.bhowmick92@gmail.com"],
	"subject":"Test"
}
username: maps to the senders's mail ID.
password: user's password.
message: email message body.
recipient:multiple email IDs of receivers.
subject:Email subject line.

Attachments are coming soon! along with a proper AngularJS UI client.


