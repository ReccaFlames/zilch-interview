# Transactions API

API for bank transactions.

## Get transactions for account

Returns transactions for specific account with a given id.

Supported filter criteria:
- `page` - page number 
- `size` - number of elements on page
- `fromDate` - value date from each we want to search
- `toDate` - value date to each we want to search

### example request
```
http://localhost:8080/accounts/f47cae72-22be-4d3b-b530-dca92ecfa718/transactions?page=0&size=2&fromDate=2020-10-02T15:32:47Z&toDate=2020-10-07T19:32:45Z
```
### example response
```JSON
[
    {
        "transactionId": "95482e40-b4df-44a5-b1df-816c74c4ec2f",
        "accountId": "f47cae72-22be-4d3b-b530-dca92ecfa718",
        "transactionAmountDto": {
            "currency": "EUR",
            "amount": 25
        },
        "bookingDate": "2020-10-07T14:32:45Z",
        "valueDate": "2020-10-07T14:32:45Z"
    },
    {
        "transactionId": "1b410e5a-edbe-4a95-80e7-d7ae88fae5de",
        "accountId": "f47cae72-22be-4d3b-b530-dca92ecfa718",
        "transactionAmountDto": {
            "currency": "EUR",
            "amount": 100
        },
        "bookingDate": "2020-10-03T14:32:45Z",
        "valueDate": "2020-10-03T14:32:45Z"
    }
]
```