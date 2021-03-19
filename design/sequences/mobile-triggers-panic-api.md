


```mermaid
sequenceDiagram
    Mobile-->>Api: panic
    Api->>Mobile: 200 Success
    Api->>ApiGateway/AppSync: something
    ApiGateway/AppSync->>Lambda: something
    Lambda-->>Twilio: something
    Twilio->>Lambda: something 
    Lambda-->>RDS: something
```
