# triviahhh-api-gateway
OpenShift API Gateway for fictional company Triviahhh.com

## Endpoints
Endpoint | Description 
---|---
`gateway/quotes` | Returns a JSON array of quotes
`gateway/quotes/{id}` | Given an integer quote ID, returns that quote
`gateway/quotes/random` | Returns a random quote


## Create it

`oc new-app --image=quay.io/donschenck/triviahhh-api-gateway:latest --name=triviahhh-api-gateway --labels=app.kubernetes.io/part-of=quotesforu,systemname=quotesforu,tier=apigateway,language=quarkus,quotesforu=apigateway`


`oc expose service/triviahhh-api-gateway`


