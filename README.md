# servercall-on-incoming-sms

This is a demo app that was created to solve a purpose of SMS gateway where client sends SMS and gateway is suppose to call an API of our server


Step 1. Client sends an SMS

Step 2. Phone running this App receives it

Step 3. This App calls an API of the Server with the incoming message details

This is a quick hack to simulate the telcos providing callbacks feature for incoming sms. 

## Make one of your own

Change the Config.java as follows:

```
SMS_RULES.put("KEYWORD","http://yourbaseurl1.com/api/n2/sms/inbound");
```
where,

`keyword` is the starting text that will be appended by the client while sending SMS. This also ensures that non intended message doesnt get post from the phone running this app.

`value` against that keyword is the endpoint of the Server that will be called on receiving of the SMS. 

`P.S: For now, this only supports an unauthenticated GET call. Feel free to change RestclientGetCall.java to change the network call`
